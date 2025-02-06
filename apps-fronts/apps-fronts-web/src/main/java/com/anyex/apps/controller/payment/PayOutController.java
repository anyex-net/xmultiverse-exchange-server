package com.anyex.apps.controller.payment;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.model.AssetWithdrawApplyResultModel;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysParameter;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.payment.req.ReqWithdraw;
import com.anyex.apps.controller.payment.req.ReqWithdrawQuery;
import com.anyex.apps.controller.payment.req.ReqWithdrawWithInfo;
import com.anyex.apps.controller.payment.resp.RespWithdrawAsset;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisLock;
import com.anyex.apps.utils.StringUtils;
import com.anyex.globalpay.util.GlobalPayUrlParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@Api(tags = "代付业务")
@Slf4j
public class PayOutController extends GenericController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    AccountReceivingBankService accountReceivingBankService;

    @Autowired(required = false)
    WalletAssetService walletAssetService;

    @Autowired(required = false)
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Autowired(required = false)
    SysParameterService sysParameterService;

    @Value("${com.anyex.whitelist.payment.globalpay}")
    String globalPayWhiteList;

    @PostMapping(value = "/out/withdraw/asset")
    @ApiOperation(value = "代付业务-提现资产详情", httpMethod = "POST")
    public JsonMessage<RespWithdrawAsset> withdrawAsset() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        BigDecimal minAmt = BigDecimal.ZERO;
        BigDecimal feeRate = BigDecimal.ZERO;
        SysParameter parameter = sysParameterService.getParameterByName("withDrawMinAmount");
        if(null == parameter)
        {
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        minAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现最小金额:{}" , minAmt);

        parameter = sysParameterService.getParameterByName("withDrawFeeRate");
        if(null == parameter)
        {
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        feeRate = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现费率:{}" , feeRate);

        parameter = sysParameterService.getParameterByName("withDrawMaxAmount");
        if(null == parameter)
        {
            log.error("提现业务：请在系统参数中配置钱包提现单次最大金额：withDrawMaxAmount");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        BigDecimal maxAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现单次最大金额:{}" , maxAmt);
        RespWithdrawAsset ret = null;

        // 资产处理
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(principal.getId(), GlobalConst.CURRENCY_PKR);
        if(null == asset)
        {
            ret = new RespWithdrawAsset(
                    principal.getId(),
                    GlobalConst.CURRENCY_PKR,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    false,
                    maxAmt,
                    minAmt,
                    feeRate);
            return this.getJsonMessage(CommonEnums.SUCCESS,ret);
        }
        // 账户余额
        BigDecimal balance = asset.getBalance();
        // 冻结余额
        BigDecimal frozenBalance = asset.getFrozenBal();
        // 可用余额
        BigDecimal enableBalance = asset.getBalance().subtract(asset.getFrozenBal()).setScale(2,BigDecimal.ROUND_DOWN);

        // 最大可提
        BigDecimal maxCanWithdrawAmount = enableBalance;
        // 最小可提
        BigDecimal minCanWithdrawAmount = enableBalance;
        if(enableBalance.compareTo(minAmt)<0)
        {
            minCanWithdrawAmount = BigDecimal.ZERO;
            maxCanWithdrawAmount = BigDecimal.ZERO;
        }
        else if(enableBalance.compareTo(maxAmt)>0)
        {
            minCanWithdrawAmount = minAmt;
            maxCanWithdrawAmount = maxAmt;
        }
        else {
            minCanWithdrawAmount = minAmt;
            maxCanWithdrawAmount = enableBalance;
        }

        // 是否可以提现
        Boolean canWithdraw = true;
        if(enableBalance.compareTo(minAmt)<0)
        {
            canWithdraw = false;
        }

        ret = new RespWithdrawAsset(
                principal.getId(),
                GlobalConst.CURRENCY_PKR,
                balance.setScale(2,BigDecimal.ROUND_DOWN),
                frozenBalance.setScale(2,BigDecimal.ROUND_DOWN),
                enableBalance.setScale(2,BigDecimal.ROUND_DOWN),
                maxCanWithdrawAmount.setScale(2,BigDecimal.ROUND_DOWN),
                minCanWithdrawAmount.setScale(2,BigDecimal.ROUND_DOWN),
                canWithdraw,
                maxAmt.setScale(2,BigDecimal.ROUND_DOWN),
                minAmt.setScale(2,BigDecimal.ROUND_DOWN),
                feeRate);

        return this.getJsonMessage(CommonEnums.SUCCESS,ret);
    }

    @PostMapping(value = "/out/withdraw")
    @ApiOperation(value = "代付业务-提现", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 120, isLogin = true) // 登录情况下限制120秒内最多请求1次
    public JsonMessage<AssetWithdrawApplyResultModel> withdraw(@Validated @RequestBody ReqWithdraw withdraw) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        if (withdraw.getAmount().doubleValue() <= 0 || withdraw.getAmount().doubleValue() >= 99999999) {
            throw new BusinessException("请输入合法的金额");
        }
        if(StringUtils.equalsIgnoreCase(sysParameterService.getParameterByName("SystemTradeSwitch").getValue(),"OFF"))
        {
            log.error("系统开关已关闭");
            throw new BusinessException(CommonEnums.RISK_TRADE_OFF);
        }
        log.info("提现业务：amount:{}" , withdraw.getAmount());
        log.info("提现业务：bankRecordId:{}" , withdraw.getBankRecordId());
        AccountReceivingBank bank = accountReceivingBankService.selectByPrimaryKey(withdraw.getBankRecordId());
        if(null == bank)
        {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        if(bank.getAccountId().longValue() != principal.getId().longValue())
        {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        BigDecimal minAmt = BigDecimal.ZERO;
        BigDecimal feeRate = BigDecimal.ZERO;
        SysParameter parameter = sysParameterService.getParameterByName("withDrawMinAmount");
        if(null == parameter)
        {
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        minAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现最小金额:{}" , minAmt);

        parameter = sysParameterService.getParameterByName("withDrawFeeRate");
        if(null == parameter)
        {
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        feeRate = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现费率:{}" , feeRate);

        parameter = sysParameterService.getParameterByName("withDrawMaxAmount");
        if(null == parameter)
        {
            log.error("提现业务：请在系统参数中配置钱包提现单次最大金额：withDrawMaxAmount");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        BigDecimal withDrawMaxAmount = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现单次最大金额:{}" , withDrawMaxAmount);

        if(BigDecimal.valueOf(withdraw.getAmount()).compareTo(minAmt)<0)
        {
            log.error("提现业务：钱包提现最小金额:{},实际提现金额：{}",minAmt,withdraw.getAmount());
            throw new BusinessException(CommonEnums.ERROR_AMOUNT_RANGE.code, String.format(CommonEnums.ERROR_AMOUNT_RANGE.message, minAmt.toPlainString(),withDrawMaxAmount.toPlainString()));
        }

        if(BigDecimal.valueOf(withdraw.getAmount()).compareTo(withDrawMaxAmount)>0)
        {
            log.error("提现业务：钱包提现最大金额:{},实际提现金额：{}",withDrawMaxAmount,withdraw.getAmount());
            throw new BusinessException(CommonEnums.ERROR_AMOUNT_RANGE.code, String.format(CommonEnums.ERROR_AMOUNT_RANGE.message, minAmt.toPlainString(),withDrawMaxAmount.toPlainString()));
        }
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(bank.getAccountId());
        //  分布式锁 锁用户ID和钱币类型
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        // 分布式锁
        if (redisLock.lock()) {
            try {
                // 资产处理
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(principal.getId(), GlobalConst.CURRENCY_PKR);
                if(null == asset || (asset.getBalance().subtract(asset.getFrozenBal())).compareTo(BigDecimal.valueOf(withdraw.getAmount())) < 0)
                {
                    log.error("账户{} PKR 资产不存在或可以可用余额不足",principal.getId());
                    throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                }
                return this.getJsonMessage(CommonEnums.SUCCESS,
                        walletAssetTransactionsService.withdrawApply(bank,BigDecimal.valueOf(withdraw.getAmount()),GlobalConst.PAYMENT_CHANNEL_GLOBALPAY)
                );
            } catch (BusinessException ex) {
                log.error("提现业务异常ex：{}", ex.getLocalizedMessage());
                throw ex;
            } catch (Exception e) {
                // 业务出现异常
                e.printStackTrace();
                log.error("提现业务异常：{}", e.getLocalizedMessage());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            } finally {
                redisLock.unlock();
            }
        } else {
            // 触发分布式锁
            log.error("提现业务触发分布式锁:{}", bank.getAccountId());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }
    }

    /*@PostMapping(value = "/out/withdrawWithReceivingBank")
    @ApiOperation(value = "代付业务-提现(携带收款银行信息)", httpMethod = "POST")*/
    public JsonMessage<AssetWithdrawApplyResultModel> withdrawWithReceivingBank(@Validated @RequestBody ReqWithdrawWithInfo withdraw) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        if (withdraw.getAmount().doubleValue() <= 0 || withdraw.getAmount().doubleValue() >= 99999999) {
            throw new BusinessException("请输入合法的金额");
        }
        log.info("提现业务：amount:{}" , withdraw.getAmount());
        log.info("提现业务：info:{}" , JSONObject.toJSONString(withdraw));
        AccountReceivingBank bank = new AccountReceivingBank();
        BeanUtils.copyProperties(withdraw,bank);
        bank.setAccountId(principal.getId());

        BigDecimal minAmt = BigDecimal.ZERO;
        BigDecimal feeRate = BigDecimal.ZERO;
        SysParameter parameter = sysParameterService.getParameterByName("withDrawMinAmount");
        if(null == parameter)
        {
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        minAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现最小金额:{}" , minAmt);

        parameter = sysParameterService.getParameterByName("withDrawFeeRate");
        if(null == parameter)
        {
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        feeRate = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现费率:{}" , feeRate);

        parameter = sysParameterService.getParameterByName("withDrawMaxAmount");
        if(null == parameter)
        {
            log.error("提现业务：请在系统参数中配置钱包提现单次最大金额：withDrawMaxAmount");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        BigDecimal withDrawMaxAmount = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现单次最大金额:{}" , withDrawMaxAmount);

        if(BigDecimal.valueOf(withdraw.getAmount()).compareTo(minAmt)<0)
        {
            log.error("提现业务：钱包提现最小金额:{},实际提现金额：{}",minAmt,withdraw.getAmount());
            throw new BusinessException(CommonEnums.ERROR_AMOUNT_RANGE.code, String.format(CommonEnums.ERROR_AMOUNT_RANGE.message, minAmt.toPlainString(),withDrawMaxAmount.toPlainString()));
        }

        if(BigDecimal.valueOf(withdraw.getAmount()).compareTo(withDrawMaxAmount)>0)
        {
            log.error("提现业务：钱包提现最大金额:{},实际提现金额：{}",withDrawMaxAmount,withdraw.getAmount());
            throw new BusinessException(CommonEnums.ERROR_AMOUNT_RANGE.code, String.format(CommonEnums.ERROR_AMOUNT_RANGE.message, minAmt.toPlainString(),withDrawMaxAmount.toPlainString()));
        }

        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(bank.getAccountId());
        //  分布式锁 锁用户ID和钱币类型
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        // 分布式锁
        if (redisLock.lock()) {
            try {
                // 资产处理
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(principal.getId(), GlobalConst.CURRENCY_PKR);
                if(null == asset || (asset.getBalance().subtract(asset.getFrozenBal())).compareTo(BigDecimal.valueOf(withdraw.getAmount())) < 0)
                {
                    log.error("账户{} PKR 资产不存在或可以可用余额不足",principal.getId());
                    throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                }
                return this.getJsonMessage(CommonEnums.SUCCESS,
                        walletAssetTransactionsService.withdrawApply(bank,BigDecimal.valueOf(withdraw.getAmount()),GlobalConst.PAYMENT_CHANNEL_GLOBALPAY)
                );
            } catch (BusinessException e) {
                throw e;
            } catch (Exception e) {
                // 业务出现异常
                e.printStackTrace();
                log.error("提现业务异常：{}", e.getLocalizedMessage());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            } finally {
                redisLock.unlock();
            }
        } else {
            // 触发分布式锁
            log.error("提现业务触发分布式锁:{}", bank.getAccountId());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }

    }

    @PostMapping(value = "/globalpay/out/notify")
    @ApiOperation(value = "代付业务-结果回调", httpMethod = "POST")
    public String notify(@Validated @RequestBody String body, HttpServletRequest request) throws Exception
    {
        String ip = NetworkUtils.getIpAddr(request);
        log.info("代付业务回调 notify----whitelist={} ip={}" ,globalPayWhiteList, ip);
        /*Boolean bool = NetworkUtils.validIp(globalPayWhiteList,ip);
        if(!bool)
        {
            throw new BusinessException("非白名单地址");
        }*/
        log.info("代付业务回调 notify---- body=" + body);
        JSONObject params = JSONObject.parseObject(body);
        log.info("代付业务回调 notify---- params=" + params);
        // 现行获取商户订单编号
        if(params.get("mchOrderId") == null)
        {
            log.error("代付业务回调:未传输订单号 params={}",params);
            return "fail";
        }
        String trxNo = params.getString("mchOrderId");
        WalletAssetTransactions record = walletAssetTransactionsService.findByTrxNo(trxNo);
        if(null == record)
        {
            log.error("代付业务回调:未查到记录 trxNo={}",trxNo);
            return "success"; // 这类数据 不需要要再次回调
        }
        if(!StringUtils.equalsIgnoreCase("withDraw",record.getTrxType()))
        {
            log.error("代付业务回调:业务类型不正确 id={}",record.getId());
            return "success" ;// 这类数据 不需要要再次回调
        }
        //  分布式锁 锁用户ID和钱币类型
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(record.getAccountId());
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                if(!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING,record.getTrxStatus()))
                {
                    log.error("代付业务回调:状态不正确不予处理 id={}",record.getId());
                    return "success";// 这类数据 不需要要再次回调
                }
                String ret = walletAssetTransactionsService.withdrawNotify(record.getTrxNo(),params.getString("state"),body);
                return ret;
            } catch (Exception e) {
                log.error("支付回调 操作失败：{}", e.getMessage());
                return "fail";
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("代付业务回调 分布式锁失败");
            return "fail";
        }
    }

    private String notifyOld(@Validated @RequestBody String body, HttpServletRequest request) throws Exception
    {
        String ip = NetworkUtils.getIpAddr(request);
        log.info("代付业务回调 notify----whitelist={} ip={}" ,globalPayWhiteList, ip);
        /*Boolean bool = NetworkUtils.validIp(globalPayWhiteList,ip);
        if(!bool)
        {
            throw new BusinessException("非白名单地址");
        }*/
        log.info("代付业务回调 notify---- body=" + body);
        Map<String,String> params = GlobalPayUrlParamUtil.getParams(body);
        log.info("代付业务回调 notify---- params=" + params);
        if(params.get("mchOrderNo") == null)
        {
            log.error("代付业务回调:未传输订单号 params={}",params);
            return "FAIL";
        }
        String trxNo = params.get("mchOrderNo");
        WalletAssetTransactions record = walletAssetTransactionsService.findByTrxNo(trxNo);
        if(null == record)
        {
            log.error("代付业务回调:未查到记录 trxNo={}",trxNo);
            return "SUCCESS"; // 这类数据 不需要要再次回调
        }
        if(!StringUtils.equalsIgnoreCase("withDraw",record.getTrxType()))
        {
            log.error("代付业务回调:业务类型不正确 id={}",record.getId());
            return "SUCCESS" ;// 这类数据 不需要要再次回调
        }
        //  分布式锁 锁用户ID和钱币类型
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(record.getAccountId());
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                if(!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING,record.getTrxStatus()))
                {
                    log.error("代付业务回调:状态不正确不予处理 id={}",record.getId());
                    return "SUCCESS";// 这类数据 不需要要再次回调
                }
                String ret = walletAssetTransactionsService.withdrawNotify(record.getTrxNo(),params.get("state"),body);
                return ret;
            } catch (Exception e) {
                log.error("支付回调 操作失败：{}", e.getMessage());
                return "FAIL";
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("代付业务回调 分布式锁失败");
            return "FAIL";
        }
    }

    /*@PostMapping(value = "/out/query")
    @ApiOperation(value = "代付业务-结果查询", httpMethod = "POST")*/
    public JsonMessage<String> query(@Validated @RequestBody ReqWithdrawQuery query) {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        log.info("代付业务结果查询：withdrawRecordId:{}", query.getWithdrawRecordId());

        WalletAssetTransactions record = walletAssetTransactionsService.selectByPrimaryKey(query.getWithdrawRecordId());
        if(null == record)
        {
            log.error("代付业务回调:未查到记录 id={}",query.getWithdrawRecordId());
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        if(!StringUtils.equalsIgnoreCase("withDraw",record.getTrxType()))
        {
            log.error("代付业务回调:业务类型不正确 id={}",query.getWithdrawRecordId());
            throw new BusinessException(CommonEnums.ERROR_BUSINESS);
        }
        if(!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING,record.getTrxStatus()))
        {
            log.error("代付业务回调:状态不正确不予处理 id={}",query.getWithdrawRecordId());
            throw new BusinessException(CommonEnums.ERROR_STATUS);
        }
        if(record.getAccountId().longValue() != principal.getId().longValue())
        {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //  分布式锁 锁用户ID和钱币类型
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(record.getAccountId());
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        // 分布式锁
        if (redisLock.lock()) {
            try {
                record = walletAssetTransactionsService.selectByPrimaryKey(query.getWithdrawRecordId());
                if(!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING,record.getTrxStatus()))
                {
                    log.error("代付业务回调:状态不正确不予处理 id={}",query.getWithdrawRecordId());
                    throw new BusinessException(CommonEnums.ERROR_STATUS);
                }
                walletAssetTransactionsService.withdrawQueryAndUpdate(query.getWithdrawRecordId(),record.getAccountId());
                return this.getJsonMessage(CommonEnums.SUCCESS,"操作成功");
            } catch (BusinessException e) {
                throw e;
            } catch (Exception e) {
                // 业务出现异常
                e.printStackTrace();
                log.error("代付业务结果查询异常：{}", e.getLocalizedMessage());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            } finally {
                redisLock.unlock();
            }

        } else {
            log.error("代付业务结果查询触发分布式锁:withdrawRecordId={}", query.getWithdrawRecordId());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }

    }
}
