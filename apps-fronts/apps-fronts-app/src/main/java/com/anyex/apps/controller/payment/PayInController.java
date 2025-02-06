package com.anyex.apps.controller.payment;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.model.AssetDepositApplyResultModel;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.payment.req.ReqDeposit;
import com.anyex.apps.controller.payment.req.ReqDepositNotify;
import com.anyex.apps.controller.payment.req.ReqDepositQuery;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisLock;
import com.anyex.apps.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
@Api(tags = "代收业务")
@Slf4j
public class PayInController extends GenericController {

    @Autowired(required = false)
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Value("${com.anyex.whitelist.payment.wivpay}")
    String wivPayWhiteList;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    SysParameterService sysParameterService;

    @PostMapping(value = "/in/getPayUrl")
    @ApiOperation(value = "代收业务-获取支付链接", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 5, isLogin = true) // 登录情况下限制5秒内最多请求1次
    public JsonMessage<AssetDepositApplyResultModel> getPayUrl(@Validated @RequestBody ReqDeposit deposit) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        if(StringUtils.equalsIgnoreCase(sysParameterService.getParameterByName("SystemTradeSwitch").getValue(),"OFF"))
        {
            log.error("系统开关已关闭");
            throw new BusinessException(CommonEnums.RISK_TRADE_OFF);
        }
        log.info("调用充值获取支付链接：amount:{}", deposit.getAmount());
       /* log.info("调用充值获取支付链接：realname:{}", deposit.getRealname());
        log.info("调用充值获取支付链接：email:{}", deposit.getEmail());
        log.info("调用充值获取支付链接：trxChannel:{}", deposit.getTrxChannel());*/
        if (deposit.getAmount().doubleValue() <= 0 || deposit.getAmount().doubleValue() >= 99999999) {
            throw new BusinessException(CommonEnums.ERROR_DEPOSIT_AMOUNT_ERR);
        }
        //
        if( StringUtils.startsWith(deposit.getMobile(), "0") ){
            deposit.setMobile(deposit.getMobile().substring(1, deposit.getMobile().length()));
            log.info("手机号输入是0开头的 进行去除处理:{}", deposit.getMobile());
        }
        //
        return this.getJsonMessage(
                CommonEnums.SUCCESS,
                walletAssetTransactionsService.depositApply(
                        GlobalConst.PAYMENT_CHANNEL_WIVPAY,
                        deposit.getWalletType(),
                        deposit.getCnic(),
                        "",//deposit.getRealname(),
                        deposit.getMobile(),
                        "",//deposit.getEmail(),
                        deposit.getAmount(),
                        principal.getId())
        );
    }

    @GetMapping(value = "/wivpay/in/notify")
    @ApiOperation(value = "代收业务-结果回调", httpMethod = "GET")
    public ResponseEntity<Object> notify(@Validated ReqDepositNotify notify, HttpServletRequest request){
        String ip = NetworkUtils.getIpAddr(request);
        log.info("代收业务回调 notify----whitelist={} ip={}" ,wivPayWhiteList, ip);
//        Boolean bool = NetworkUtils.validIp(wivPayWhiteList,ip);
//        if(!bool)
//        {
//            throw new BusinessException(CommonEnums.ERROR_BLACK_WHITE_IP_LIST);
//        }
        log.info("代收业务回调 notify---- payStatus=" + notify.getPayStatus() + "    tradeNo=" + notify.getTradeNo() + "    payAmount=" + notify.getPayAmount());
        WalletAssetTransactions transactions = walletAssetTransactionsService.findByTrxNo(notify.getTradeNo());
        if (null == transactions) {
            log.error("代收业务回调:未查到记录 id={}", notify.getTradeNo());
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.equalsIgnoreCase("deposit", transactions.getTrxType())) {
            log.error("代收业务回调:业务类型不正确 id={}", notify.getTradeNo());
            throw new BusinessException("业务类型不正确");
        }
        if (StringUtils.equalsIgnoreCase(GlobalConst.STATUS_SUCCESS, transactions.getTrxStatus())) {
            log.error("代收业务回调:状态不正确不予处理 id={} status={}", notify.getTradeNo(),transactions.getTrxStatus());
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(transactions.getAccountId());
        //  分布式锁 锁用户ID和钱币类型
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                String ret = walletAssetTransactionsService.depositNotify(notify.getTradeNo(), notify.getPayStatus(), JSONObject.toJSONString(notify));
                if (StringUtils.isNotBlank(ret)) {
                    return new ResponseEntity<>(ret, HttpStatus.OK);
                }
                else
                {
                    log.error("支付回调 后端返回空值");
                    return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("支付回调 操作失败：{}", e.getMessage());
                new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("支付回调 分布式锁失败");
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/wivpay/in/redirect")
    @ApiOperation(value = "代收业务-结果redirect", httpMethod = "GET")
    public ResponseEntity<Object> redirect(@Validated ReqDepositNotify notify, HttpServletRequest request){
        log.info("代收业务回调 redirect---- payStatus=" + notify.getPayStatus() + "    tradeNo=" + notify.getTradeNo() + "    payAmount=" + notify.getPayAmount());
        return new ResponseEntity<>("未实现", HttpStatus.BAD_REQUEST);
    }

   /* @PostMapping(value = "/in/query")
    @ApiOperation(value = "代收业务-结果查询", httpMethod = "POST")*/
    public JsonMessage<String> query(@Validated @RequestBody ReqDepositQuery query) {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        log.info("代收业务结果查询：depositRecordId:{}", query.getDepositRecordId());
        WalletAssetTransactions transactions = walletAssetTransactionsService.selectByPrimaryKey(query.getDepositRecordId());
        if(null == transactions)
        {
            log.error("代收业务回调:未查到记录 id={}",query.getDepositRecordId());
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        if(transactions.getAccountId().longValue() != principal.getId().longValue())
        {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        if(!StringUtils.equalsIgnoreCase("deposit",transactions.getTrxType()))
        {
            log.error("代收业务回调:业务类型不正确 id={}",query.getDepositRecordId());
            throw new BusinessException(CommonEnums.ERROR_BUSINESS);
        }
        if(!StringUtils.equalsIgnoreCase("pending",transactions.getTrxStatus()))
        {
            log.error("代收业务回调:状态不正确不予处理 id={}",query.getDepositRecordId());
            throw new BusinessException(CommonEnums.ERROR_STATUS);
        }
        //  分布式锁 锁用户ID和钱币类型
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(transactions.getAccountId());
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        // 分布式锁
        if (redisLock.lock()) {
            try {
                transactions = walletAssetTransactionsService.selectByPrimaryKey(query.getDepositRecordId());
                if(!StringUtils.equalsIgnoreCase("pending",transactions.getTrxStatus()))
                {
                    log.error("代收业务回调:状态不正确不予处理 id={}",query.getDepositRecordId());
                    throw new BusinessException(CommonEnums.ERROR_STATUS);
                }
                walletAssetTransactionsService.depositQueryAndUpdate(query.getDepositRecordId(), transactions.getAccountId());
            } catch (BusinessException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("代收业务结果查询异常：{}", e.getLocalizedMessage());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            } finally {
                redisLock.unlock();
            }
        } else {
            // 触发分布式锁
            log.error("代收业务结果查询触发分布式锁:depositRecordId={}", query.getDepositRecordId());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,"操作成功");
    }
}
