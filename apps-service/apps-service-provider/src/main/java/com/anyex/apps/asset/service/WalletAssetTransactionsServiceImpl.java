/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetAdjust;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.model.AssetDepositApplyResultModel;
import com.anyex.apps.asset.model.AssetWithdrawApplyResultModel;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysParameter;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.DateConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.DateUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.globalpay.api.GlobalPayApi;
import com.anyex.globalpay.api.GlobalPayApiV2;
import com.anyex.globalpay.config.GlobalPayConfig;
import com.anyex.globalpay.util.GlobalPayUtil;
import com.anyex.wivpay.api.WivPayApi;
import com.anyex.wivpay.config.WivPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.asset.mapper.WalletAssetTransactionsMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 钱包资产转账记录表 服务实现类
 * <p>File：WalletAssetTransactionsServiceImpl.java </p>
 * <p>Title: WalletAssetTransactionsServiceImpl </p>
 * <p>Description:WalletAssetTransactionsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class WalletAssetTransactionsServiceImpl extends GenericServiceImpl<WalletAssetTransactions> implements WalletAssetTransactionsService {
    protected WalletAssetTransactionsMapper walletAssetTransactionsMapper;

    @Autowired
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Autowired
    WalletAssetFlowsService walletAssetFlowsService;

    @Autowired
    WalletAssetService walletAssetService;

    @Autowired
    WalletAssetAdjustService walletAssetAdjustService;

    @Autowired
    AccountReceivingBankService accountReceivingBankService;

    @Autowired
    WivPayConfig wivPayConfig;

    @Autowired
    GlobalPayConfig globalPayConfig;

    @Autowired
    SysParameterService sysParameterService;

    @Autowired
    AccountService accountService;

    @Autowired
    RedisTemplate redisTemplate;

    @Value("${com.anyex.env}")
    private String env;

    @Value("${com.anyex.withDrawInterfaceSwitch}")
    private Boolean withDrawInterfaceSwitch;

    BigDecimal depositFeeRate = BigDecimal.valueOf(0.045);

    @Autowired(required = false)
    public WalletAssetTransactionsServiceImpl(WalletAssetTransactionsMapper walletAssetTransactionsMapper) {
        super(walletAssetTransactionsMapper);
        this.walletAssetTransactionsMapper = walletAssetTransactionsMapper;
    }

    /**
     * 交易编码查询记录
     *
     * @param trxNo 交易编码
     * @return
     */
    @Override
    public WalletAssetTransactions findByTrxNo(String trxNo) {
        return walletAssetTransactionsMapper.findByTrxNo(trxNo);
    }

    /**
     * 充值申请
     *
     * @param cnic      身份证号码
     * @param realName  真实姓名
     * @param mobile    手机号
     * @param email     邮箱
     * @param amount    金额
     * @param accountId 账户id
     * @return
     * @throws BusinessException
     * @Param trxChannel 支付通道
     * @Param accountType  账户类型(BANK、WALLET)
     */
    @Override
    public AssetDepositApplyResultModel depositApply(String trxChannel, String walletType, String cnic, String realName, String mobile, String email, Double amount, Long accountId) throws BusinessException {
        String trxNo = SerialnoUtils.getOrderNum();
        Long depositId = SerialnoUtils.buildPrimaryKey();
        log.info("调用充值获取支付链接：trxNo:{}", trxNo);
        log.info("调用充值获取支付链接：cnic:{}" , cnic);
        log.info("调用充值获取支付链接：mobile:{}" , mobile);
         /*log.info("调用充值获取支付链接：realName:{}" , realName);
        log.info("调用充值获取支付链接：email:{}" , email);*/
        log.info("调用充值获取支付链接：amount:{}", amount);
        log.info("调用充值获取支付链接：accountId:{}", accountId);
        try {
            // 充值记录ID
            WalletAssetTransactions trans = new WalletAssetTransactions();
            trans.setAccountId(accountId);
            trans.setCurrency(GlobalConst.CURRENCY_PKR);
            trans.setTrxType("deposit");
            trans.setTrxAmount(BigDecimal.valueOf(amount));
            trans.setTrxFee(BigDecimal.ZERO);
            trans.setTrxActAmount(BigDecimal.ZERO);
            trans.setTrxTime(System.currentTimeMillis());
            trans.setTrxNo(trxNo);
            trans.setTrxStatus(GlobalConst.STATUS_PENDING);
            trans.setTrxDesc("");
            trans.setTrxChannel(trxChannel);

            // 以后这个地方需要根据业务确定是否填入
            trans.setTrxAccountType("");
            trans.setTrxAccountNo("");
            trans.setTrxAccountName("");
            trans.setTrxCnic(cnic);
            trans.setTrxEmail("");
            trans.setTrxMobile(mobile);
            trans.setTrxBankName(walletType.toUpperCase());
            //
            trans.setCreateTime(System.currentTimeMillis());
            trans.setUpdateTime(System.currentTimeMillis());
            trans.setId(depositId);
            walletAssetTransactionsService.insert(trans);
            log.info("调用充值获取支付链接插入记录成功:{}", JSONObject.toJSONString(trans));
            String channel = (StringUtils.equalsIgnoreCase(walletType,"jazzcash")?"LuckyboxKingJC":"LuckyboxKingEP");
            log.info("充值通道选择:{}={}", walletType,channel);
            // 调用接口拉起支付链接
            JSONObject object = WivPayApi.payIn(wivPayConfig, channel, trxNo, cnic, "", mobile, "", amount,accountId);
            log.info("调用充值获取支付链接应答：response:{}", object.toJSONString());
            if (200 == object.getInteger("status")) {
                // 调取支付链接成功
                object = object.getJSONObject("result");
                log.info("调用充值获取支付链接成功:{}", object.toJSONString());
                String url = object.getString("payUrl");
                return new AssetDepositApplyResultModel(depositId, trxNo, url, trxChannel, amount, "", mobile, "", "", cnic);
            } else {
                // 调取支付链接失败
                trans.setTrxStatus(GlobalConst.STATUS_FAILED);
                walletAssetTransactionsService.updateByPrimaryKey(trans);
                log.error("调用充值获取支付链接失败:{}", object.toJSONString());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            }

        }
        // 捕捉业务异常
        catch (BusinessException e) {
            throw e;
        }
        // 捕捉业务异常之外的异常
        catch (Exception e) {
            e.printStackTrace();
            log.error("调用充值获取支付链接异常：{}", e.getLocalizedMessage());
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 充值回调
     *
     * @param trxNo  交易编码
     * @param status 状态
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String depositNotify(String trxNo, String status,String reqJsonStr) throws BusinessException {
        WalletAssetTransactions  transactions = walletAssetTransactionsService.findByTrxNo(trxNo);
        if (null == transactions) {
            log.error("代收业务回调:未查到记录 id={}", trxNo);
            return "fail";
        }
        if (!StringUtils.equalsIgnoreCase("deposit", transactions.getTrxType())) {
            log.error("代收业务回调:业务类型不正确 id={}", trxNo);
            return "fail";
        }
        if (StringUtils.equalsIgnoreCase(GlobalConst.STATUS_SUCCESS, transactions.getTrxStatus())) {
            log.error("代收业务回调:状态不正确不予处理 id={} status={}", trxNo,transactions.getTrxStatus());
            return "fail";
        }
        Long accountId = transactions.getAccountId();
        // 回调成功 1- 代表⽀付成功 0-失败
        if ("1".equals(status)) {
            WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
            if (null == asset) {
                log.error("账户{} PKR 资产不存在", accountId);
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }
            transactions.setTrxStatus("success");
            transactions.setTrxFee(BigDecimal.ZERO);
            transactions.setTrxActAmount(transactions.getTrxAmount());
            transactions.setQueryDesc(reqJsonStr);
            log.info("代收回调：transactions ={}", transactions);
            walletAssetTransactionsService.updateByPrimaryKey(transactions);
            // 更新资产
            // 充值成功 账户资产增加
            BigDecimal oldBalance = asset.getBalance();
            asset.setBalance(asset.getBalance().add(transactions.getTrxAmount()));
            asset.setUpdateTime(System.currentTimeMillis());
            log.info("代收回调：asset ={}", asset);
            walletAssetService.updateByPrimaryKey(asset);
            // 资金流水
            // 账户资产增加资金流水  方向+  业务：充值 业务类型：充值 状态：成功
            WalletAssetFlows flows = new WalletAssetFlows();
            flows.setAccountId(accountId);
            flows.setCurrency(GlobalConst.CURRENCY_PKR);
            flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
            flows.setBusinessType(GlobalConst.BUSINESS_TYPE_DEPOSIT);
            flows.setBeforeBalance(oldBalance);
            flows.setDirection("+");
            flows.setBalance(transactions.getTrxAmount());
            flows.setAfterBalance(asset.getBalance());
            flows.setOrgBusinessId(transactions.getId());
            flows.setOrgBusinessNo(trxNo);
            flows.setStatus(true);
            flows.setCreateTime(System.currentTimeMillis());
            flows.setUpdateTime(System.currentTimeMillis());
            flows.setFee(BigDecimal.ZERO);
            flows.setRemark("Top Up");
            log.info("代收回调：flows ={}", flows);
            walletAssetFlowsService.insert(flows);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

        } else {
            // 回调 返回充值失败
            transactions.setTrxStatus("failed");
            transactions.setTrxFee(BigDecimal.ZERO);
            transactions.setQueryDesc(reqJsonStr);
            walletAssetTransactionsService.updateByPrimaryKey(transactions);
        }
        return "success";
    }

    /**
     * 查询状态并更新
     *
     * @param id        记录id
     * @param accountId 账户id
     * @throws BusinessException
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depositQueryAndUpdate(Long id, Long accountId) throws BusinessException {
        WalletAssetTransactions transactions = walletAssetTransactionsService.selectByPrimaryKey(id);
        if (null == transactions) {
            log.error("代收业务回调:未查到记录 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        if (!StringUtils.equalsIgnoreCase("deposit", transactions.getTrxType())) {
            log.error("代收业务回调:业务类型不正确 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_BUSINESS);
        }
        if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, transactions.getTrxStatus())) {
            log.error("代收业务回调:状态不正确不予处理 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_STATUS);
        }
        JSONObject object = WivPayApi.payInQuery(wivPayConfig, transactions.getTrxNo());
        log.info("代收业务结果查询结果：responce:{}", object.toJSONString());

        if (200 == object.getInteger("status")) {
            // 调取支付链接成功
            object = object.getJSONObject("result");
            Integer state = object.getInteger("orderStatus");
            if (state == null) {
                log.error("代收业务回调失败:{}", object.toJSONString());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            }
            // 6.2.0 ─orderStatus string YES 1：接单成功，3:接单失败4：⽀付成功，5：超时取消 6：⽀付失败
            // 接口返回支付失败 1：接单成功，3:接单失败4：⽀付成功，5：超时取消 6：⽀付失败
            if (state.intValue() == 3 || state.intValue() == 5 || state.intValue() == 6) {
                transactions.setTrxStatus(GlobalConst.STATUS_FAILED);
                transactions.setQueryDesc(object.toJSONString());
                walletAssetTransactionsService.updateByPrimaryKey(transactions);
            }
            // 接口返回支付成功 1：接单成功，3:接单失败4：⽀付成功，5：超时取消 6：⽀付失败
            if (state.intValue() == 4) {
                // 资产处理 新增资产
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
                if (null == asset) {
                    log.error("账户{} PKR 资产不存在", accountId);
                    throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                }
                // 充值记录
                transactions.setTrxStatus(GlobalConst.STATUS_SUCCESS);
                transactions.setTrxFee(BigDecimal.ZERO);
                transactions.setTrxActAmount(transactions.getTrxAmount());
                transactions.setQueryDesc(object.toJSONString());
                log.info("代收查询：transactions ={}", transactions);
                walletAssetTransactionsService.updateByPrimaryKey(transactions);

                // 更新资产
                BigDecimal oldBalance = asset.getBalance();
                asset.setBalance(asset.getBalance().add(transactions.getTrxAmount()));
                asset.setUpdateTime(System.currentTimeMillis());
                log.info("代收查询：asset ={}", asset);
                walletAssetService.updateByPrimaryKey(asset);
                // 资金流水
                // 类型：充值 发生方向+ 业务类型：充值
                WalletAssetFlows flows = new WalletAssetFlows();
                flows.setAccountId(accountId);
                flows.setCurrency(GlobalConst.CURRENCY_PKR);
                flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
                flows.setBusinessType(GlobalConst.BUSINESS_TYPE_DEPOSIT);
                flows.setBeforeBalance(oldBalance);
                flows.setDirection("+");
                flows.setBalance(transactions.getTrxAmount());
                flows.setFee(BigDecimal.ZERO);
                flows.setAfterBalance(asset.getBalance());
                flows.setOrgBusinessId(transactions.getId());
                flows.setOrgBusinessNo(transactions.getTrxNo());
                flows.setStatus(true);
                flows.setCreateTime(System.currentTimeMillis());
                flows.setUpdateTime(System.currentTimeMillis());
                flows.setRemark("Top Up");
                log.info("代收查询：flows ={}", flows);
                walletAssetFlowsService.insert(flows);

                // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
                AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());
            }
            log.info("代收业务结果查询结果业务处理成功：responce:{}", object.toJSONString());
        } else {
            log.error("代收业务结果查询结果业务未处理：responce:{}", object.toJSONString());
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AssetWithdrawApplyResultModel withdrawApply(AccountReceivingBank bank, BigDecimal amount, String trxChannel) throws BusinessException {
        if(RedisUtils.getObject(GlobalConst.PAYMENT_CHANNEL_GLOBALPAY_SYS_ERROR)!=null)
        {
            log.error("[触发支付通道异常风控 20分钟后解除]"+RedisUtils.getObject(GlobalConst.PAYMENT_CHANNEL_GLOBALPAY_SYS_ERROR).toString());
            throw new BusinessException(CommonEnums.ERROR_WITHDRAW_INTERFACE);
        }
        Long withdrawId = SerialnoUtils.buildPrimaryKey();
        String trxNo = SerialnoUtils.getOrderNum();
        log.info("提现业务：bankInfo:{}", JSONObject.toJSONString(bank));
        log.info("提现业务：amount:{}", amount);
        log.info("提现业务：trxChannel:{}", trxChannel);
        BigDecimal minAmt = BigDecimal.ZERO;
        BigDecimal feeRate = BigDecimal.ZERO;
        BigDecimal globalFeeRate = BigDecimal.ZERO;
        SysParameter parameter = sysParameterService.getParameterByName("withDrawMinAmount");
        if (null == parameter) {
            log.error("提现业务：请在系统参数中配置钱包提现最小金额：withDrawMinAmount");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        minAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现最小金额:{}", minAmt);

        parameter = sysParameterService.getParameterByName("withDrawFeeRate");
        if (null == parameter) {
            log.error("提现业务：请在系统参数中配置钱包提现费率：withDrawFeeRate");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        feeRate = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现费率:{}", feeRate);

        parameter = sysParameterService.getParameterByName("withDrawGlobalPayFeeRate");
        if (null == parameter) {
            log.error("提现业务：请在系统参数中配置GlobalPay转账费率：withDrawGlobalPayFeeRate");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        globalFeeRate = new BigDecimal(parameter.getValue());
        log.info("提现业务：globalpay转账费率:{}", globalFeeRate);

        parameter = sysParameterService.getParameterByName("withDrawMaxAmount");
        if (null == parameter) {
            log.error("提现业务：请在系统参数中配置钱包提现单次最大金额：withDrawMaxAmount");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        BigDecimal withDrawMaxAmount = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现单次最大金额:{}", withDrawMaxAmount);


        parameter = sysParameterService.getParameterByName("withDrawDayMaxAmount");
        if (null == parameter) {
            log.error("提现业务：日累计限额：withDrawDayMaxAmount");
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
        BigDecimal withDrawDayMaxAmount = new BigDecimal(parameter.getValue());
        log.info("提现业务：日累计限额:{}", withDrawDayMaxAmount);

        Account account = accountService.selectByPrimaryKey(bank.getAccountId());

        // 账户当日累计提现
        BigDecimal actAmt = amount.multiply((BigDecimal.ONE.subtract(feeRate))).setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal accountTotalWithDrawCurrDate = walletAssetTransactionsMapper.getTotalWithDrawCurrDay(bank.getAccountId(),null);
        if((accountTotalWithDrawCurrDate.add(actAmt)).compareTo(withDrawDayMaxAmount)>0)
        {
            String msg = String.format("平台账户累计提现超过限额，当日累计已提款金额：%s,本次提现金额:%s,本次预估到账金额：%s,已超过当日限额：%s",
                    accountTotalWithDrawCurrDate.setScale(0,BigDecimal.ROUND_DOWN),
                    amount.setScale(0,BigDecimal.ROUND_DOWN),
                    actAmt.setScale(0,BigDecimal.ROUND_DOWN),
                    withDrawDayMaxAmount.setScale(0,BigDecimal.ROUND_DOWN)
            );
            log.error(msg);
            throw new BusinessException(CommonEnums.ERROR_WITHDRAW_LIMIT_ACCOUNTID.code, String.format(CommonEnums.ERROR_WITHDRAW_LIMIT_ACCOUNTID.message, account.getEmail(),withDrawDayMaxAmount.setScale(2,BigDecimal.ROUND_DOWN).toPlainString()));
        }

        // 对应收款账户累计提现
        BigDecimal accountNoTotalWithDrawCurrDate = walletAssetTransactionsMapper.getTotalWithDrawCurrDay(null,bank.getAccountNo());
        if((accountNoTotalWithDrawCurrDate.add(actAmt)).compareTo(withDrawDayMaxAmount)>0)
        {
            String msg = String.format("收款账户累计提现超过限额，当日累计已提款金额：%s,本次提现金额:%s,本次预估到账金额：%s,已超过当日限额：%s",
                    accountNoTotalWithDrawCurrDate.setScale(0,BigDecimal.ROUND_DOWN),
                    amount.setScale(0,BigDecimal.ROUND_DOWN),
                    actAmt.setScale(0,BigDecimal.ROUND_DOWN),
                    withDrawDayMaxAmount.setScale(0,BigDecimal.ROUND_DOWN)
            );
            log.error(msg);
            throw new BusinessException(CommonEnums.ERROR_WITHDRAW_LIMIT_ACCOUNTNO.code, String.format(CommonEnums.ERROR_WITHDRAW_LIMIT_ACCOUNTID.message,bank.getAccountNo(),accountTotalWithDrawCurrDate.setScale(2,BigDecimal.ROUND_DOWN).toPlainString()));
        }

        if (amount.compareTo(minAmt) < 0) {
            log.error("提现业务：钱包提现最小金额:{},实际提现金额：{}", minAmt, amount);
            throw new BusinessException(CommonEnums.ERROR_AMOUNT_RANGE.code, String.format(CommonEnums.ERROR_AMOUNT_RANGE.message, minAmt.toPlainString(), withDrawMaxAmount.toPlainString()));
        }

        if (amount.compareTo(withDrawMaxAmount) > 0) {
            log.error("提现业务：钱包提现最大金额:{},实际提现金额：{}", withDrawMaxAmount, amount);
            throw new BusinessException(CommonEnums.ERROR_AMOUNT_RANGE.code, String.format(CommonEnums.ERROR_AMOUNT_RANGE.message, minAmt.toPlainString(), withDrawMaxAmount.toPlainString()));
        }
        // 可用余额
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(bank.getAccountId(), GlobalConst.CURRENCY_PKR);
        if (null == asset) {
            log.error("提现业务：账户表不存在");
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }
        if ((asset.getBalance().subtract(asset.getFrozenBal())).compareTo(amount) < 0) {
            log.error("提现业务：可用余额不足，支付金额:{},可用余额：{}", amount, asset.getBalance());
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        String status = GlobalConst.STATUS_PENDING;
        WalletAssetTransactions trans = new WalletAssetTransactions();
        trans.setAccountId(bank.getAccountId());
        trans.setCurrency(GlobalConst.CURRENCY_PKR);
        trans.setTrxType(GlobalConst.BUSINESS_TYPE_WITHDRAW);
        trans.setTrxAmount(amount);
        actAmt = amount.multiply((BigDecimal.ONE.subtract(feeRate))).setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal fee = amount.subtract(actAmt);
        trans.setTrxFee(amount.subtract(actAmt));
        trans.setTrxActAmount(actAmt);
        trans.setTrxTime(System.currentTimeMillis());
        trans.setTrxNo(trxNo);
        trans.setTrxStatus(status);
        trans.setTrxDesc("");
        trans.setTrxChannel(trxChannel);
        trans.setTrxAccountType(bank.getAccountType());
        trans.setTrxAccountNo(bank.getAccountNo());
        trans.setTrxAccountName(bank.getAccountName());
        trans.setTrxBankName(bank.getBankName());
        trans.setTrxIban(bank.getIban());
        trans.setTrxCnic(bank.getCnic());
        trans.setTrxEmail(bank.getEmail());
        trans.setTrxMobile(bank.getMobile());
        trans.setCreateTime(System.currentTimeMillis());
        trans.setUpdateTime(System.currentTimeMillis());
        trans.setId(withdrawId);
        walletAssetTransactionsService.insert(trans);
        trans.setId(withdrawId);

        // 资金流水
        // 资金减少 类型：提现 发生方向- 业务：提现
        WalletAssetFlows flows = new WalletAssetFlows();
        flows.setAccountId(bank.getAccountId());
        flows.setCurrency(GlobalConst.CURRENCY_PKR);
        flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
        flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW);
        flows.setBeforeBalance(asset.getBalance());
        flows.setDirection("-");
        flows.setBalance(amount);
        flows.setFee(fee);
        flows.setAfterBalance(asset.getBalance().subtract(amount));
        flows.setOrgBusinessId(withdrawId);
        flows.setOrgBusinessNo(trxNo);
        flows.setStatus(true);
        flows.setCreateTime(System.currentTimeMillis());
        flows.setUpdateTime(System.currentTimeMillis());
        flows.setRemark("Withdraw");
        walletAssetFlowsService.insert(flows);
        log.info(String.format("提现业务:提现金额=%s,用户手续费=%s,实际打款金额=%s", amount, fee, actAmt));

        // 更新资产 提现资金减少
        asset.setBalance(asset.getBalance().subtract(amount));
        asset.setUpdateTime(System.currentTimeMillis());
        walletAssetService.updateByPrimaryKey(asset);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

        if (null == withDrawInterfaceSwitch || !withDrawInterfaceSwitch){
            log.error("提现接口开关未打开withDrawInterfaceSwitch");
            trans.setId(withdrawId);
            trans.setTrxDesc("提现接口关闭");
            walletAssetTransactionsMapper.updateByPrimaryKey(trans);
            return new AssetWithdrawApplyResultModel(withdrawId, trxNo, status, trxChannel);
        }
        // 参数=ON
        else {
            BigDecimal finalActAmt = actAmt;
            new Thread(() -> {
                try {
                    // withdrawInterfaceOld(trxChannel,trxNo,withdrawId, finalActAmt,bank,trans);
                    withdrawInterface(trxChannel,trxNo,withdrawId, finalActAmt,bank,trans);
                    log.info("调用接口提现申请成功：提现ID{},trxNo{}",withdrawId,trxNo);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("调用接口提现申请异常：{}",e.getLocalizedMessage());
                }
            }).start();
            return new AssetWithdrawApplyResultModel(withdrawId, trxNo, status, trxChannel);
        }
    }

    protected void withdrawInterface(String trxChannel,String trxNo,Long withdrawId,BigDecimal actAmt,AccountReceivingBank bank,WalletAssetTransactions trans)
    {
        log.info("提现申请调用接口 start: {}",JSONObject.toJSONString(trans));
        Map<String, Object> payOutMap = new HashMap<>();
        payOutMap.put("appId", globalPayConfig.appid);
        payOutMap.put("mchOrderId", trxNo);
        payOutMap.put("amount", actAmt.multiply(BigDecimal.valueOf(100)).longValue());
        payOutMap.put("notifyUrl", globalPayConfig.payOutNotifyUrl);
        payOutMap.put("accountNo", bank.getAccountNo());
        payOutMap.put("accountType", bank.getAccountType());
        payOutMap.put("accountProvider", bank.getBankName());
        payOutMap.put("customerName", bank.getAccountName());
        payOutMap.put("customerCert", bank.getCnic());
        payOutMap.put("customerEmail", bank.getEmail());
        payOutMap.put("customerPhone", bank.getMobile());
        payOutMap.put("customerIBAN", bank.getIban());
        payOutMap.put("sign",com.anyex.globalpay.util.GlobalPayUtil.getSign(payOutMap,globalPayConfig.key));

        try {
            log.info("提现业务：request:{}", JSONObject.toJSONString(payOutMap));
            JSONObject object = GlobalPayApiV2.post(globalPayConfig.rootUrl+globalPayConfig.rootUrlTransferorder,payOutMap);
            log.info("提现业务应答：response:{}", object.toJSONString());
            if (1 == object.getInteger("code")) {
                log.info("提现业务成功:{}", object.toJSONString());
                // 调取支付链接成功
                Integer state = object.getInteger("state");
                String orderId = object.getString("orderId");
                // 订单状态（1=成功，2=失败，0=支付中，99=待支付）
                String status = (state == 1 ? GlobalConst.STATUS_SUCCESS: GlobalConst.STATUS_PENDING);
                trans.setPlatTrxNo(orderId);
                trans.setId(withdrawId);
                trans.setTrxDesc(object.toJSONString());
                trans.setTrxStatus(status);
                walletAssetTransactionsMapper.updateByPrimaryKey(trans);
            } else {
                // 调取支付链接失败
                log.info("提现业务失败:{}", object.toJSONString());
                trans.setId(withdrawId);
                trans.setTrxDesc(object.toJSONString());
                trans.setTrxStatus(GlobalConst.STATUS_PENDING);
                walletAssetTransactionsMapper.updateByPrimaryKey(trans);
            }
            log.info("提现申请调用接口 end: {}",JSONObject.toJSONString(trans));
        }
        // GP 通道异常 捕获返回信息不能转化为JSONObject的情况
        catch (Exception e)
        {
            RedisUtils.putObject(GlobalConst.PAYMENT_CHANNEL_GLOBALPAY_SYS_ERROR, "发生时间："+DateUtils.formatDate(new Date(), DateConst.DATE_FORMAT_YMDHMS) +" 异常：" + e.getLocalizedMessage(),GlobalConst.PAYMENT_CHANNEL_GLOBALPAY_SYS_ERROR_LOCKTIME);
        }
    }

    protected void withdrawInterfaceOld(String trxChannel,String trxNo,Long withdrawId,BigDecimal actAmt,AccountReceivingBank bank,WalletAssetTransactions trans)
    {
        log.info("提现申请调用接口 start: {}",JSONObject.toJSONString(trans));
        Map<String, Object> map = new HashMap<>();
        // 组装请求参数
        map.put("mchOrderNo", trxNo);
        map.put("amount", actAmt.multiply(BigDecimal.valueOf(100)).longValue());
        map.put("currency", GlobalConst.CURRENCY_PKR);
        map.put("accountNo", bank.getAccountNo());
        map.put("accountName", bank.getAccountName());
        map.put("accountType", bank.getAccountType());
        if (StringUtils.isNotBlank(bank.getBankName())) {
            map.put("bankName", bank.getBankName());
        }
        // -- 巴基斯坦 start
        map.put("customerCert", bank.getCnic());
        map.put("customerEmail", bank.getEmail());
        map.put("customerIBAN", bank.getIban());
        map.put("phoneNumber", bank.getMobile());
        // -- 巴基斯坦 end
        // map.put("accountType", bank.getAccountType().toUpperCase());//String BANK or WALLET
        map.put("transferDesc", "提现业务");

        // map.put("ifCode", "ACardpay");
        map.put("notifyUrl", globalPayConfig.payOutNotifyUrl);
        map.put("mchNo", globalPayConfig.mchno);
        map.put("appId", globalPayConfig.appid);
        map.put("reqTime", System.currentTimeMillis());
        map.put("version", "1.0");
        map.put("signType", "MD5");
        map.put("sign", GlobalPayUtil.getSign(map, globalPayConfig.key));

        log.info("提现业务：request:{}", JSONObject.toJSONString(map));
        JSONObject object = GlobalPayApi.transaction(globalPayConfig, map);
        log.info("提现业务应答：response:{}", object.toJSONString());
        if (0 == object.getInteger("code")) {
            log.info("提现业务成功:{}", object.toJSONString());
            // 调取支付链接成功
            object = object.getJSONObject("data");
            Integer state = object.getInteger("state");
            String status = state.intValue() == 2 ? GlobalConst.STATUS_SUCCESS : GlobalConst.STATUS_PENDING;
            trans.setId(withdrawId);
            trans.setTrxDesc(object.toJSONString());
            trans.setTrxStatus(status);
            walletAssetTransactionsMapper.updateByPrimaryKey(trans);
        } else {
            // 调取支付链接失败
            log.info("提现业务失败:{}", object.toJSONString());
            trans.setId(withdrawId);
            trans.setTrxDesc(object.toJSONString());
            trans.setTrxStatus(GlobalConst.STATUS_PENDING);
            walletAssetTransactionsMapper.updateByPrimaryKey(trans);
        }
        log.info("提现申请调用接口 end: {}",JSONObject.toJSONString(trans));
    }

    /**
     * 提现结果回调
     *
     * @param trxNo  交易编码
     * @param status 状态
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String withdrawNotify(String trxNo, String status ,String reqJsonStr) throws BusinessException {
        WalletAssetTransactions record = walletAssetTransactionsService.findByTrxNo(trxNo);
        if (null == record) {
            log.error("代付业务回调:未查到记录 trxNo={}", trxNo);
            return "fail";
        }
        if (!StringUtils.equalsIgnoreCase("withDraw", record.getTrxType())) {
            log.error("代付业务回调:业务类型不正确 trxNo={}", trxNo);
            return "fail";
        }
        if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, record.getTrxStatus())) {
            log.error("代付业务回调:状态不正确不予处理 trxNo={}", trxNo);
            return "fail";
        }
        if(StringUtils.isEmpty(status)){
            log.error("代付业务回调:状态为空不予处理  trxNo={} status={}", trxNo,status);
            return "fail";
        }

        Long accountId = record.getAccountId();
        record = walletAssetTransactionsService.findByTrxNo(trxNo);
        if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, record.getTrxStatus())) {
            log.error("代付业务回调:状态不正确不予处理 trxNo={}", trxNo);
            return "fail";
        }

        // 支付成功 更新状态
        if (StringUtils.equalsIgnoreCase("1", status))
        {
            // 更新记录 支付成功
            record.setTrxStatus(GlobalConst.STATUS_SUCCESS);
            record.setQueryDesc(reqJsonStr);
            walletAssetTransactionsService.updateByPrimaryKey(record);
            // 检查并更新银行卡等信息
            checkAccountReceivingBank(record, 1);
        }

        // 支付失败 返回金额
        else
        {
            // 充值记录
            record.setTrxStatus(GlobalConst.STATUS_FAILED);
            record.setQueryDesc(reqJsonStr);
            walletAssetTransactionsService.updateByPrimaryKey(record);
            // 资产处理 退还
            WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
            if (null == asset) {
                log.error("账户{} PKR 资产不存在", accountId);
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }

            // 更新资产
            BigDecimal oldBalance = asset.getBalance();
            asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
            asset.setUpdateTime(System.currentTimeMillis());
            walletAssetService.updateByPrimaryKey(asset);
            // 资金流水
            // 类型：提现 业务：提现退还 发生方向+
            WalletAssetFlows flows = new WalletAssetFlows();
            flows.setAccountId(accountId);
            flows.setCurrency(GlobalConst.CURRENCY_PKR);
            flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
            flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
            flows.setBeforeBalance(oldBalance);
            flows.setDirection("+");
            flows.setBalance(record.getTrxAmount());
            flows.setFee(record.getTrxFee());
            flows.setAfterBalance(asset.getBalance());
            flows.setOrgBusinessId(record.getId());
            flows.setOrgBusinessNo(trxNo);
            flows.setStatus(true);
            flows.setCreateTime(System.currentTimeMillis());
            flows.setUpdateTime(System.currentTimeMillis());
            flows.setRemark("Withdraw Back");
            walletAssetFlowsService.insert(flows);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

            // 检查并更新银行卡等信息
            checkAccountReceivingBank(record, 2);
        }

        return "success";
    }

    public String withdrawNotifyOld(String trxNo, String status) throws BusinessException {
        WalletAssetTransactions record = walletAssetTransactionsService.findByTrxNo(trxNo);
        if (null == record) {
            log.error("代付业务回调:未查到记录 trxNo={}", trxNo);
            return "FAIL";
        }
        if (!StringUtils.equalsIgnoreCase("withDraw", record.getTrxType())) {
            log.error("代付业务回调:业务类型不正确 trxNo={}", trxNo);
            return "FAIL";
        }
        if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, record.getTrxStatus())) {
            log.error("代付业务回调:状态不正确不予处理 trxNo={}", trxNo);
            return "FAIL";
        }
        // state	是	int	订单状态 0-订单生成 1-转账中 2-转账成功 3-转账失败 4-转账关闭
        // 避免不该有的操作 从严处理 只判断 2 3
        if (StringUtils.equalsIgnoreCase("2", status) || StringUtils.equalsIgnoreCase("3", status)) {
            Long accountId = record.getAccountId();
            record = walletAssetTransactionsService.findByTrxNo(trxNo);
            if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, record.getTrxStatus())) {
                log.error("代付业务回调:状态不正确不予处理 trxNo={}", trxNo);
                return "FAIL";
            }
            // 支付成功 更新状态
            if (StringUtils.equalsIgnoreCase("2", status)) {
                // 更新记录 支付成功
                record.setTrxStatus(GlobalConst.STATUS_SUCCESS);
                record.setQueryDesc("回调成功，回调结果成功");
                walletAssetTransactionsService.updateByPrimaryKey(record);
                // 检查并更新银行卡等信息
                checkAccountReceivingBank(record, 1);
            }

            // 支付失败 返回金额
            if (StringUtils.equalsIgnoreCase("3", status)) {
                // 充值记录
                record.setTrxStatus(GlobalConst.STATUS_FAILED);
                record.setQueryDesc("回调成功，回调结果失败");
                walletAssetTransactionsService.updateByPrimaryKey(record);
                // 资产处理 退还
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
                if (null == asset) {
                    log.error("账户{} PKR 资产不存在", accountId);
                    throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                }

                // 更新资产
                BigDecimal oldBalance = asset.getBalance();
                asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
                asset.setUpdateTime(System.currentTimeMillis());
                walletAssetService.updateByPrimaryKey(asset);
                // 资金流水
                // 类型：提现 业务：提现退还 发生方向+
                WalletAssetFlows flows = new WalletAssetFlows();
                flows.setAccountId(accountId);
                flows.setCurrency(GlobalConst.CURRENCY_PKR);
                flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
                flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
                flows.setBeforeBalance(oldBalance);
                flows.setDirection("+");
                flows.setBalance(record.getTrxAmount());
                flows.setFee(record.getTrxFee());
                flows.setAfterBalance(asset.getBalance());
                flows.setOrgBusinessId(record.getId());
                flows.setOrgBusinessNo(trxNo);
                flows.setStatus(true);
                flows.setCreateTime(System.currentTimeMillis());
                flows.setUpdateTime(System.currentTimeMillis());
                flows.setRemark("Withdraw Back");
                walletAssetFlowsService.insert(flows);

                // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
                AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

                // 检查并更新银行卡等信息
                checkAccountReceivingBank(record, 2);
            }
            return "SUCCESS";
        }
        return "SUCCESS";
    }

    /**
     * 查询状态并更新
     *
     * @param id        记录id
     * @param accountId 账户id
     * @throws BusinessException
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void withdrawQueryAndUpdate(Long id, Long accountId) throws BusinessException {
        Long clossTime = 86400L;
        SysParameter parameter = sysParameterService.getParameterByName("withdrawTrxNoNotExsitCloseTime");
        if (null == parameter) {
            log.error("代付业务回调:未查到参数 withdrawTrxNoNotExsitCloseTime");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        clossTime = Long.parseLong(parameter.getValue()) * 1000;
        WalletAssetTransactions record = walletAssetTransactionsService.selectByPrimaryKey(id);
        if (null == record) {
            log.error("代付业务回调:未查到记录 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        if (!StringUtils.equalsIgnoreCase("withDraw", record.getTrxType())) {
            log.error("代付业务回调:业务类型不正确 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_BUSINESS);
        }
        if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, record.getTrxStatus())) {
            log.error("代付业务回调:状态不正确不予处理 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_STATUS);
        }

        // 如果没有平台的订单号，则下单失败，由于异步下单，故处理失败在查询中进行。
        if(StringUtils.isEmpty(record.getPlatTrxNo()))
        {
            log.info("代付业务结果查询结果订单不存在，{}下单失败无法查询。 start",record.getTrxNo());
            // 充值记录
            record.setTrxStatus(GlobalConst.STATUS_FAILED);
            record.setRemark("查询代付业务订单不存在，返回资产。");
            record.setQueryDesc("下单失败的订单进行处理");
            walletAssetTransactionsService.updateByPrimaryKey(record);
            // 资产处理 退还
            WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
            if (null == asset) {
                log.error("账户{} PKR 资产不存在", accountId);
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }
            // 更新资产
            BigDecimal oldBalance = asset.getBalance();
            asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
            asset.setUpdateTime(System.currentTimeMillis());
            walletAssetService.updateByPrimaryKey(asset);
            // 资金流水
            // 返回金额 类型：提现 业务：提现返回 发生方向+
            WalletAssetFlows flows = new WalletAssetFlows();
            flows.setAccountId(accountId);
            flows.setCurrency(GlobalConst.CURRENCY_PKR);
            flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
            flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
            flows.setBeforeBalance(oldBalance);
            flows.setDirection("+");
            flows.setBalance(record.getTrxAmount());
            flows.setFee(record.getTrxFee());
            flows.setAfterBalance(asset.getBalance());
            flows.setOrgBusinessId(record.getId());
            flows.setOrgBusinessNo(record.getTrxNo());
            flows.setStatus(true);
            flows.setCreateTime(System.currentTimeMillis());
            flows.setUpdateTime(System.currentTimeMillis());
            flows.setRemark("Withdraw Back");
            walletAssetFlowsService.insert(flows);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

            // 检查并更新银行卡等信息
            checkAccountReceivingBank(record, 2);
            log.info("代付业务结果查询结果订单不存在，{}下单失败无法查询。 end",record.getTrxNo());
        }
        else
        {
            Map<String, Object> payOutQueryMap = new HashMap<>();
            payOutQueryMap.put("appId", globalPayConfig.appid);
            payOutQueryMap.put("orderId", record.getPlatTrxNo());
            payOutQueryMap.put("sign",GlobalPayUtil.getSign(payOutQueryMap,globalPayConfig.key));
            JSONObject object = GlobalPayApiV2.get(globalPayConfig.rootUrl+globalPayConfig.getRootUrlTransferorderQuery(), payOutQueryMap);
            log.info("代付业务结果查询结果：responce:{}", object.toJSONString());
            if (object.containsKey("code") && 1 == object.getInteger("code")) {
                // 调取支付链接成功 订单状态（1=成功，2=失败，0=支付中，99=待支付）
                Integer state = object.getInteger("state");
                if (state != null && state == 1) {
                    // 更新记录 支付成功
                    record.setTrxStatus(GlobalConst.STATUS_SUCCESS);
                    record.setQueryDesc(object.toJSONString());
                    walletAssetTransactionsService.updateByPrimaryKey(record);
                    // 检查并更新银行卡等信息
                    checkAccountReceivingBank(record, 1);
                }
                // 支付失败
                else if (state == 2) {
                    // 记录
                    record.setTrxStatus(GlobalConst.STATUS_FAILED);
                    record.setQueryDesc(object.toJSONString());
                    walletAssetTransactionsService.updateByPrimaryKey(record);
                    // 资产处理 退还
                    WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
                    if (null == asset) {
                        log.error("账户{} PKR 资产不存在", accountId);
                        throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                    }

                    // 更新资产
                    BigDecimal oldBalance = asset.getBalance();
                    asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
                    asset.setUpdateTime(System.currentTimeMillis());
                    walletAssetService.updateByPrimaryKey(asset);
                    // 资金流水
                    // 返回金额 类型：提现 业务：提现返回 发生方向+
                    WalletAssetFlows flows = new WalletAssetFlows();
                    flows.setAccountId(accountId);
                    flows.setCurrency(GlobalConst.CURRENCY_PKR);
                    flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
                    flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
                    flows.setBeforeBalance(oldBalance);
                    flows.setDirection("+");
                    flows.setBalance(record.getTrxAmount());
                    flows.setFee(record.getTrxFee());
                    flows.setAfterBalance(asset.getBalance());
                    flows.setOrgBusinessId(record.getId());
                    flows.setOrgBusinessNo(record.getTrxNo());
                    flows.setStatus(true);
                    flows.setCreateTime(System.currentTimeMillis());
                    flows.setUpdateTime(System.currentTimeMillis());
                    flows.setRemark("Withdraw Back");
                    walletAssetFlowsService.insert(flows);

                    // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
                    AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

                    // 检查并更新银行卡等信息
                    checkAccountReceivingBank(record, 2);
                }
                log.info("代付业务结果查询结果业务处理成功：responce:{}", object.toJSONString());
            } else if (0 == object.getInteger("code")
                    && StringUtils.equalsIgnoreCase(object.getString("msg"), "无效单号")
                    && ((System.currentTimeMillis() - record.getCreateTime().longValue()) > clossTime)
            ) {
                log.info("代付业务结果查询结果订单不存在，返回资产开始：trxNo:{}，responce:{}", record.getTrxNo(), object.toJSONString());
                // 充值记录
                record.setTrxStatus(GlobalConst.STATUS_FAILED);
                record.setRemark("查询代付业务订单不存在，返回资产。");
                record.setQueryDesc(object.toJSONString());
                walletAssetTransactionsService.updateByPrimaryKey(record);
                // 资产处理 退还
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
                if (null == asset) {
                    log.error("账户{} PKR 资产不存在", accountId);
                    throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                }

                // 更新资产
                BigDecimal oldBalance = asset.getBalance();
                asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
                asset.setUpdateTime(System.currentTimeMillis());
                walletAssetService.updateByPrimaryKey(asset);
                // 资金流水
                // 返回金额 类型：提现 业务：提现返回 发生方向+
                WalletAssetFlows flows = new WalletAssetFlows();
                flows.setAccountId(accountId);
                flows.setCurrency(GlobalConst.CURRENCY_PKR);
                flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
                flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
                flows.setBeforeBalance(oldBalance);
                flows.setDirection("+");
                flows.setBalance(record.getTrxAmount());
                flows.setFee(record.getTrxFee());
                flows.setAfterBalance(asset.getBalance());
                flows.setOrgBusinessId(record.getId());
                flows.setOrgBusinessNo(record.getTrxNo());
                flows.setStatus(true);
                flows.setCreateTime(System.currentTimeMillis());
                flows.setUpdateTime(System.currentTimeMillis());
                flows.setRemark("Withdraw Back");
                walletAssetFlowsService.insert(flows);

                // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
                AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

                // 检查并更新银行卡等信息
                checkAccountReceivingBank(record, 2);
                log.error("代付业务结果查询结果订单不存在，返回资产完成：trxNo:{}，responce:{}", record.getTrxNo(), object.toJSONString());
            } else {
                log.error("代付业务结果查询结果业务未处理：responce:{}", object.toJSONString());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void withdrawQueryAndUpdateOld(Long id, Long accountId) throws BusinessException {
        Long clossTime = 86400L;
        SysParameter parameter = sysParameterService.getParameterByName("withdrawTrxNoNotExsitCloseTime");
        if (null == parameter) {
            log.error("代付业务回调:未查到参数 withdrawTrxNoNotExsitCloseTime");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        clossTime = Long.parseLong(parameter.getValue()) * 1000;
        WalletAssetTransactions record = walletAssetTransactionsService.selectByPrimaryKey(id);
        if (null == record) {
            log.error("代付业务回调:未查到记录 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_EXIST);
        }
        if (!StringUtils.equalsIgnoreCase("withDraw", record.getTrxType())) {
            log.error("代付业务回调:业务类型不正确 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_BUSINESS);
        }
        if (!StringUtils.equalsIgnoreCase(GlobalConst.STATUS_PENDING, record.getTrxStatus())) {
            log.error("代付业务回调:状态不正确不予处理 id={}", id);
            throw new BusinessException(CommonEnums.ERROR_STATUS);
        }

        TreeMap<String, Object> map = new TreeMap<String, Object>();
        map.put("mchOrderNo", record.getTrxNo());

        JSONObject object = GlobalPayApi.transactionOrderQuery(globalPayConfig, map);
        log.info("代付业务结果查询结果：responce:{}", object.toJSONString());
        if (object.containsKey("code") && 0 == object.getInteger("code")) {
            // 调取支付链接成功
            object = object.getJSONObject("data");
            Integer state = object.getInteger("state");
            // 订单状态 0-订单生成 1-支付中 2-支付成功 3-支付失败 4-已撤销 5-已退款 6-订单关闭
            if (state != null && state == 2) {
                // 更新记录 支付成功
                record.setTrxStatus(GlobalConst.STATUS_SUCCESS);
                record.setQueryDesc(object.toJSONString());
                walletAssetTransactionsService.updateByPrimaryKey(record);
                // 检查并更新银行卡等信息
                checkAccountReceivingBank(record, 1);
            }
            // ||  state == 6 订单关闭暂从严 需要根据接口情况判断是否加入
            else if (state != null && (state == 3 || state == 4 || state == 5)) {
                // 记录
                record.setTrxStatus(GlobalConst.STATUS_FAILED);
                record.setQueryDesc(object.toJSONString());
                walletAssetTransactionsService.updateByPrimaryKey(record);
                // 资产处理 退还
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
                if (null == asset) {
                    log.error("账户{} PKR 资产不存在", accountId);
                    throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                }

                // 更新资产
                BigDecimal oldBalance = asset.getBalance();
                asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
                asset.setUpdateTime(System.currentTimeMillis());
                walletAssetService.updateByPrimaryKey(asset);
                // 资金流水
                // 返回金额 类型：提现 业务：提现返回 发生方向+
                WalletAssetFlows flows = new WalletAssetFlows();
                flows.setAccountId(accountId);
                flows.setCurrency(GlobalConst.CURRENCY_PKR);
                flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
                flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
                flows.setBeforeBalance(oldBalance);
                flows.setDirection("+");
                flows.setBalance(record.getTrxAmount());
                flows.setFee(record.getTrxFee());
                flows.setAfterBalance(asset.getBalance());
                flows.setOrgBusinessId(record.getId());
                flows.setOrgBusinessNo(record.getTrxNo());
                flows.setStatus(true);
                flows.setCreateTime(System.currentTimeMillis());
                flows.setUpdateTime(System.currentTimeMillis());
                flows.setRemark("Withdraw Back");
                walletAssetFlowsService.insert(flows);

                // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
                AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

                // 检查并更新银行卡等信息
                checkAccountReceivingBank(record, 2);
            }
            log.info("代付业务结果查询结果业务处理成功：responce:{}", object.toJSONString());
        } else if (9999 == object.getInteger("code")
                && StringUtils.equalsIgnoreCase(object.getString("msg"), "订单不存在")
                && ((System.currentTimeMillis() - record.getCreateTime().longValue()) > clossTime)
        ) {
            log.info("代付业务结果查询结果订单不存在，返回资产开始：trxNo:{}，responce:{}", record.getTrxNo(), object.toJSONString());
            // 充值记录
            record.setTrxStatus(GlobalConst.STATUS_FAILED);
            record.setRemark("查询代付业务订单不存在，返回资产。");
            record.setQueryDesc(object.toJSONString());
            walletAssetTransactionsService.updateByPrimaryKey(record);
            // 资产处理 退还
            WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
            if (null == asset) {
                log.error("账户{} PKR 资产不存在", accountId);
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }

            // 更新资产
            BigDecimal oldBalance = asset.getBalance();
            asset.setBalance(asset.getBalance().add(record.getTrxAmount()));
            asset.setUpdateTime(System.currentTimeMillis());
            walletAssetService.updateByPrimaryKey(asset);
            // 资金流水
            // 返回金额 类型：提现 业务：提现返回 发生方向+
            WalletAssetFlows flows = new WalletAssetFlows();
            flows.setAccountId(accountId);
            flows.setCurrency(GlobalConst.CURRENCY_PKR);
            flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
            flows.setBusinessType(GlobalConst.BUSINESS_TYPE_WITHDRAW_ROLLBACK);
            flows.setBeforeBalance(oldBalance);
            flows.setDirection("+");
            flows.setBalance(record.getTrxAmount());
            flows.setFee(record.getTrxFee());
            flows.setAfterBalance(asset.getBalance());
            flows.setOrgBusinessId(record.getId());
            flows.setOrgBusinessNo(record.getTrxNo());
            flows.setStatus(true);
            flows.setCreateTime(System.currentTimeMillis());
            flows.setUpdateTime(System.currentTimeMillis());
            flows.setRemark("Withdraw Back");
            walletAssetFlowsService.insert(flows);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

            // 检查并更新银行卡等信息
            checkAccountReceivingBank(record, 2);
            log.error("代付业务结果查询结果订单不存在，返回资产完成：trxNo:{}，responce:{}", record.getTrxNo(), object.toJSONString());
        } else {
            // 调取支付链接失败
            map.clear();
            map.put("status", "0");
            map.put("message", object.getString("message"));
            log.error("代付业务结果查询结果业务未处理：responce:{}", object.toJSONString());
            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void queryDepositStatusTask() {
        WalletAssetTransactions search = new WalletAssetTransactions();
        search.setTrxStatus(GlobalConst.STATUS_PENDING);
        search.setTrxType("deposit");
        List<WalletAssetTransactions> list = walletAssetTransactionsService.findList(search);
        for (WalletAssetTransactions entity : list) {
            try {
                walletAssetTransactionsService.depositQueryAndUpdate(entity.getId(), entity.getAccountId());
            } catch (Exception e) {
                log.error("充值状态查询调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), e.getMessage());
            }
        }
    }

    @Override
    public void queryWithdrawStatusTask() {
        WalletAssetTransactions search = new WalletAssetTransactions();
        search.setTrxStatus(GlobalConst.STATUS_PENDING);
        search.setTrxType("withDraw");
        List<WalletAssetTransactions> list = walletAssetTransactionsService.findList(search);
        for (WalletAssetTransactions entity : list) {
            try {
                walletAssetTransactionsService.depositQueryAndUpdate(entity.getId(), entity.getAccountId());
            } catch (Exception e) {
                log.error("提现状态查询调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), e.getMessage());
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void walletAssetAdjust(Long accountId, Integer direction, BigDecimal amount, String attachment, String remark) throws BusinessException {
        // 强增资产
        if (direction == 1)
        {
            WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
            if (null == asset) {
                log.error("账户{} PKR 资产不存在", accountId);
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }

            Long bizId = SerialnoUtils.buildPrimaryKey();
            String trxNo = SerialnoUtils.getOrderNum();
            // 新增记录
            WalletAssetAdjust entity = new WalletAssetAdjust();
            entity.setAccountId(accountId);
            entity.setCurrency(GlobalConst.CURRENCY_PKR);
            entity.setAdjustTrxNo(trxNo);
            entity.setAdjustType(GlobalConst.BUSINESS_TYPE_ASSET_ADJUSTADD);
            entity.setAdjustBalance(amount);
            entity.setAttachment(attachment);
            entity.setStatus(true);
            entity.setRemark(remark);
            entity.setCreateTime(System.currentTimeMillis());
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setId(bizId);
            walletAssetAdjustService.insert(entity);

            // 更新资产
            BigDecimal oldBalance = asset.getBalance();
            asset.setBalance(asset.getBalance().add(amount));
            asset.setUpdateTime(System.currentTimeMillis());
            walletAssetService.updateByPrimaryKey(asset);
            // 资金流水
            WalletAssetFlows flows = new WalletAssetFlows();
            flows.setAccountId(accountId);
            flows.setCurrency(GlobalConst.CURRENCY_PKR);
            flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
            flows.setBusinessType(GlobalConst.BUSINESS_TYPE_ASSET_ADJUSTADD);
            flows.setBeforeBalance(oldBalance);
            flows.setDirection("+");
            flows.setBalance(amount);
            flows.setFee(BigDecimal.ZERO);
            flows.setAfterBalance(asset.getBalance());
            flows.setOrgBusinessId(bizId);
            flows.setOrgBusinessNo(trxNo);
            flows.setStatus(true);
            flows.setCreateTime(System.currentTimeMillis());
            flows.setUpdateTime(System.currentTimeMillis());
            flows.setRemark("Asset-AdjustAdd");
            walletAssetFlowsService.insert(flows);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());
        }
        // 强减资产
        else if (direction == -1)
        {
            WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
            if (null == asset) {
                log.error("账户{} PKR 资产不存在", accountId);
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }

            Long bizId = SerialnoUtils.buildPrimaryKey();
            String trxNo = SerialnoUtils.getOrderNum();
            // 新增记录
            WalletAssetAdjust entity = new WalletAssetAdjust();
            entity.setAccountId(accountId);
            entity.setCurrency(GlobalConst.CURRENCY_PKR);
            entity.setAdjustTrxNo(trxNo);
            entity.setAdjustType(GlobalConst.BUSINESS_TYPE_ASSET_ADJUSTSUB);
            entity.setAdjustBalance(amount);
            entity.setAttachment(attachment);
            entity.setStatus(true);
            entity.setRemark(remark);
            entity.setCreateTime(System.currentTimeMillis());
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setId(bizId);
            walletAssetAdjustService.insert(entity);

            // 更新资产
            BigDecimal oldBalance = asset.getBalance();
            asset.setBalance(asset.getBalance().subtract(amount));
            asset.setUpdateTime(System.currentTimeMillis());
            walletAssetService.updateByPrimaryKey(asset);
            if(asset.getBalance().compareTo(BigDecimal.ZERO)<0)
            {
                log.error("资产强减会造成余额为负数");
                throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
            }
            // 资金流水
            WalletAssetFlows flows = new WalletAssetFlows();
            flows.setAccountId(accountId);
            flows.setCurrency(GlobalConst.CURRENCY_PKR);
            flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
            flows.setBusinessType(GlobalConst.BUSINESS_TYPE_ASSET_ADJUSTSUB);
            flows.setBeforeBalance(oldBalance);
            flows.setDirection("-");
            flows.setBalance(amount);
            flows.setFee(BigDecimal.ZERO);
            flows.setAfterBalance(asset.getBalance());
            flows.setOrgBusinessId(bizId);
            flows.setOrgBusinessNo(trxNo);
            flows.setStatus(true);
            flows.setCreateTime(System.currentTimeMillis());
            flows.setUpdateTime(System.currentTimeMillis());
            flows.setRemark("Asset-AdjustSub");
            walletAssetFlowsService.insert(flows);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());
        }
        else {
            log.error("调整类型错误");
            throw new BusinessException("调整类型错误");
        }
    }

    @Override
    public BigDecimal getTotalWithDrawCurrDay(Long accountId, String accountNo) {
        return walletAssetTransactionsMapper.getTotalWithDrawCurrDay(accountId, accountNo);
    }

    /**
     * 检查银行账户是否存在，不存在则插入一条
     *
     * @param transactions
     */
    void checkAccountReceivingBank(WalletAssetTransactions transactions, Integer status) {
        try {
            AccountReceivingBank bank = accountReceivingBankService.findByBankName(transactions.getAccountId(), transactions.getTrxAccountType(), transactions.getTrxBankName());
            if (null != bank) {
                if (
                        StringUtils.equalsIgnoreCase(transactions.getTrxAccountNo(), bank.getAccountNo())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxAccountName(), bank.getAccountName())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxIban(), bank.getIban())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxCnic(), bank.getCnic())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxEmail(), bank.getEmail())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxMobile(), bank.getMobile())
                ) {
                    bank.setStatus(status);
                    bank.setUpdateTime(System.currentTimeMillis());
                    accountReceivingBankService.updateByPrimaryKeySelective(bank);
                    log.info("提现ID={},更新银行卡信息成功，要素匹配，提现记录={}，银行卡记录={}", transactions.getId(), JSONObject.toJSONString(transactions), JSONObject.toJSONString(bank));
                } else {
                    log.info("提现ID={},更新银行卡信息失败，要素不匹配，提现记录={}，银行卡记录={}", transactions.getId(), JSONObject.toJSONString(transactions), JSONObject.toJSONString(bank));
                }
            }
        } catch (Exception e) {
            log.error("提现确认后添加银行账号信息失败：" + e.getMessage());
        }
    }

}
