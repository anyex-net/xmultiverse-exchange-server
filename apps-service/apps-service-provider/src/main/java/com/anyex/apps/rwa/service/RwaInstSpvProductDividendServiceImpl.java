/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.rwa.entity.*;
import com.anyex.apps.rwa.mapper.RwaBalancesMapper;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.exchange.contract.api.ContractDepositApi;
import com.anyex.exchange.contract.api.ContractDividendApi;
import com.anyex.exchange.contract.config.ContractConfig;
import com.anyex.exchange.contract.req.ReqDeposit;
import com.anyex.exchange.contract.req.ReqDividend;
import com.anyex.wallet.XMWalletApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductDividendMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * RWA机构SPV产品分红记录 服务实现类
 * <p>File：RwaInstSpvProductDividendServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductDividendServiceImpl </p>
 * <p>Description:RwaInstSpvProductDividendServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class RwaInstSpvProductDividendServiceImpl extends GenericServiceImpl<RwaInstSpvProductDividend> implements RwaInstSpvProductDividendService
{
    protected RwaInstSpvProductDividendMapper rwaInstSpvProductDividendMapper;

    private final RwaBalancesMapper rwaBalancesMapper;

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private RwaInstSpvProductService rwaInstSpvProductService;

    @Autowired(required = false)
    private RwaInstSpvProductPurchaseService rwaInstSpvProductPurchaseService;

    @Autowired(required = false)
    private RwaInstSpvProductDividendSnapshotService  rwaInstSpvProductDividendSnapshotService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @Autowired(required = false)
    @Qualifier("mintTaskExecutor")
    private Executor dividendTaskExecutor;

    @Autowired(required = false)
    public RwaInstSpvProductDividendServiceImpl(RwaInstSpvProductDividendMapper rwaInstSpvProductDividendMapper, RwaBalancesMapper rwaBalancesMapper)
    {
        super(rwaInstSpvProductDividendMapper);
        this.rwaInstSpvProductDividendMapper = rwaInstSpvProductDividendMapper;
        this.rwaBalancesMapper = rwaBalancesMapper;
    }

    @Override
    public BigDecimal selectDividendAmount(Long instSpvProductId) throws BusinessException
    {
        return rwaInstSpvProductDividendMapper.selectDividendAmount(instSpvProductId);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executedRwaInstSpvProductDividend(RwaInstSpvProductDividend rwaInstSpvProductDividend) throws BusinessException
    {
        //给项目方管理地址转分红金额 项目方地址0x104fE772a9c1269b57272eF42be1B27A8dAA9064
        String walletAddress = ContractConfig.project_management_wallet_address;
        User userDB = userService.selectByPrimaryKey(rwaInstSpvProductDividend.getUserId());
        String userNo = userDB.getRemark();
        //如果userNo为空，则跳出用户的Remark为空
        if (userNo == null) {
            throw new BusinessException("用户未进入充值界面，无法进行转账到管理地址");
        }
        //转钱给项目方管理地址
        JSONObject jsonObjectResp = XMWalletApi.create_transaction(userNo, rwaInstSpvProductDividend.getDividendCurrency(), "Ethereum",
                String.valueOf(rwaInstSpvProductDividend.getDividendAmount()), walletAddress);
        log.info("create_transaction jsonObjectResp:{}", jsonObjectResp);
        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
            JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
            log.info("create_transaction jsonObjectData request_no: {}", jsonObjectData.getString("request_no"));
            //交易确认序号 写入分红表中
            rwaInstSpvProductDividend.setRemark(jsonObjectData.getString("request_no"));
            rwaInstSpvProductDividendMapper.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
             //
            RwaBalances rwaBalances = new RwaBalances();
            rwaBalances.setUserId(rwaInstSpvProductDividend.getUserId());
            rwaBalances.setCurrency(rwaInstSpvProductDividend.getDividendCurrency());
            RwaBalances  rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
            //余额足够支付分红金额，可用余额减少，冻结余额增加
            rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().subtract(rwaInstSpvProductDividend.getDividendAmount()));
            rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().add(rwaInstSpvProductDividend.getDividendAmount()));
            rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);
            //
            //异步执行
            if (dividendTaskExecutor == null) {
                log.error("dividendTaskExecutor is not initialized.");
                throw new BusinessException("dividendTaskExecutor is not initialized.");
            } else {
                CompletableFuture.runAsync(() -> {
                    try {
                        rwaInstSpvProductDividend.setState("processing");
                        rwaInstSpvProductDividendMapper.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
                        asyncExecutedRwaInstSpvProductDividend(rwaInstSpvProductDividend,  walletAddress);
                    } catch (Exception e) {
                        log.error("Error during asyncMint execution", e);
                    }
                }, dividendTaskExecutor);
            }
        } else {
            log.error("create_transaction error: {}", jsonObjectResp);
            throw new BusinessException("create_transaction error");
        }
    }

    /**
     * 异步执行分红相关逻辑
     */
    @Async
    public void asyncExecutedRwaInstSpvProductDividend(RwaInstSpvProductDividend rwaInstSpvProductDividend,String walletAddress) {
        try {
            //
            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductDividend.getInstSpvProductId());

            // 存入金额
            ReqDeposit reqDeposit = new ReqDeposit();
            reqDeposit.setContract_address(rwaInstSpvProduct.getShareContractAddress());
            reqDeposit.setDeposited_total(rwaInstSpvProductDividend.getDividendAmount().intValue());
            JSONObject jsonDeposit = ContractDepositApi.deposit(reqDeposit);

            if (jsonDeposit.getInteger("code") != 200) {
                log.error("存入金额失败");
                rwaInstSpvProductDividend.setState("failed");
                rwaInstSpvProductDividend.setUpdateTime(System.currentTimeMillis());
                rwaInstSpvProductDividendMapper.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
                //执行失败 退分红冻结
                rwaBalancesService.unDividendFrozenBal(rwaInstSpvProductDividend);
                return;
            }

            // 分红逻辑
            ReqDividend reqDividend = new ReqDividend();
            reqDividend.setContract_address(rwaInstSpvProduct.getShareContractAddress());
            JSONObject jsonObject = ContractDividendApi.getDividend(reqDividend);
            BigDecimal totalBalance = null;

            if (jsonObject.getInteger("code") == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                BigDecimal depositedBD = new BigDecimal(new BigInteger(data.getString("deposited")), 18);
                BigDecimal distributedBD = new BigDecimal(new BigInteger(data.getString("distributed")), 18);

                if ("0".equals(data.getString("deposited")) || depositedBD.subtract(distributedBD).compareTo(rwaInstSpvProductDividend.getDividendAmount()) < 0) {
                    log.error("分红合约地址存款不足");
                }

                reqDividend.setProject_address(rwaInstSpvProduct.getTokenContractAddress());
                reqDividend.setAmount(rwaInstSpvProductDividend.getDividendAmount().intValue());
                JSONObject jsonDividend = ContractDividendApi.dividend(reqDividend);

                if (jsonDividend.getInteger("code") == 200) {
                    rwaInstSpvProductDividend.setState("success");
                    JSONObject dataDividend = jsonDividend.getJSONObject("data");
                    totalBalance = new BigDecimal(new BigInteger(dataDividend.getString("totalBalance")), 18);

                    RwaInstSpvProductPurchase rwaInstSpvProductPurchase = new RwaInstSpvProductPurchase();
                    rwaInstSpvProductPurchase.setInstSpvProductId(rwaInstSpvProductDividend.getInstSpvProductId());
                    List<RwaInstSpvProductPurchase> rwaInstSpvProductPurchases = rwaInstSpvProductPurchaseService.findList(rwaInstSpvProductPurchase);

                    List<RwaInstSpvProductDividendSnapshot> snapshotList = new ArrayList<>();

                    for (RwaInstSpvProductPurchase purchase : rwaInstSpvProductPurchases) {
                        RwaInstSpvProductDividendSnapshot productDividendSnapshot = new RwaInstSpvProductDividendSnapshot();
                        productDividendSnapshot.setId(SerialnoUtils.buildPrimaryKey());
                        productDividendSnapshot.setUserId(purchase.getUserId());
                        productDividendSnapshot.setInstInvestorId(purchase.getInstInvestorId());
                        productDividendSnapshot.setInstSpvProductId(purchase.getInstSpvProductId());
                        productDividendSnapshot.setInstSpvProductDividendNo(String.valueOf(rwaInstSpvProductDividend.getId()));
                        productDividendSnapshot.setWalletAddress(walletAddress);
                        productDividendSnapshot.setChainHoldAmount(totalBalance);
                        productDividendSnapshot.setChainDividendAmount(rwaInstSpvProductDividend.getDividendAmount());
                        BigDecimal holdAmount = purchase.getPurchaseAmount();
                        productDividendSnapshot.setHoldAmount(holdAmount);
                        BigDecimal dividendAmount = holdAmount.divide(totalBalance, 8, RoundingMode.HALF_UP)
                                .multiply(rwaInstSpvProductDividend.getDividendAmount())
                                .setScale(8, RoundingMode.HALF_UP);
                        productDividendSnapshot.setDividendAmount(dividendAmount);
                        productDividendSnapshot.setCreateTime(System.currentTimeMillis());
                        snapshotList.add(productDividendSnapshot);
                    }
                    rwaInstSpvProductDividendSnapshotService.insertBatch(snapshotList);
                    //
                    List<RwaBalancesTransHistory> rwaBalancesTransHistoryList = new ArrayList<>();
                    List<RwaBalances> rwaBalancesUpdateList = new ArrayList<>();
                    RwaBalances rwaBalances = new RwaBalances();
                    rwaBalances.setCurrency(rwaInstSpvProductDividend.getDividendCurrency());
                    List<RwaBalances> rwaBalancesList = rwaBalancesService.findList(rwaBalances);
                    for (RwaInstSpvProductPurchase productPurchase : rwaInstSpvProductPurchases){
                        for (RwaBalances rwaBalance : rwaBalancesList) {
                            if (rwaBalance.getUserId().equals(productPurchase.getUserId())){
                                RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
                                rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                                rwaBalancesTransHistory.setUserId(productPurchase.getUserId());
                                rwaBalancesTransHistory.setCurrency(productPurchase.getPurchaseCurrency());
                                rwaBalancesTransHistory.setType("dividend");
                                rwaBalancesTransHistory.setBeforeBal(rwaBalance.getAvailBal());
                                BigDecimal holdAmount = productPurchase.getPurchaseAmount();
                                BigDecimal dividendAmount = holdAmount.divide(totalBalance, 8, RoundingMode.HALF_UP)
                                        .multiply(rwaInstSpvProductDividend.getDividendAmount())
                                        .setScale(8, RoundingMode.HALF_UP);
                                rwaBalancesTransHistory.setChangeAmt(dividendAmount);
                                rwaBalancesTransHistory.setAfterBal(rwaBalance.getAvailBal().add(dividendAmount));
                                rwaBalancesTransHistory.setState("success");
                                rwaBalancesTransHistory.setTransDesc("分红");
                                rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
                                rwaBalancesTransHistoryList.add(rwaBalancesTransHistory);

                                // 更新余额
                                rwaBalance.setAvailBal(rwaBalance.getAvailBal().add(dividendAmount));
                                rwaBalance.setBalance(rwaBalance.getBalance().add(dividendAmount));
                                rwaBalancesUpdateList.add(rwaBalance); // 加入更新列表
                            }
                        }
                    }
                    rwaBalancesTransHistoryService.insertBatch(rwaBalancesTransHistoryList);
                    rwaBalancesService.updateBatch(rwaBalancesUpdateList);
                } else {
                    rwaInstSpvProductDividend.setState("failed");
                    //执行失败 退分红冻结
                    rwaBalancesService.unDividendFrozenBal(rwaInstSpvProductDividend);
                }
            }
            rwaInstSpvProductDividend.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductDividendMapper.updateByPrimaryKeySelective(rwaInstSpvProductDividend);

        } catch (Exception e) {
            log.error("异步分红处理异常", e);
            rwaInstSpvProductDividend.setState("failed");
            rwaInstSpvProductDividend.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductDividendMapper.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
            //执行失败 退分红冻结
            rwaBalancesService.unDividendFrozenBal(rwaInstSpvProductDividend);
        }
    }

}
