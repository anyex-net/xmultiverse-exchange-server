/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.service;

import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.order.mapper.Order4ActivityMapper;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 活动订单记录表 服务实现类
 * <p>File：Order4ActivityServiceImpl.java </p>
 * <p>Title: Order4ActivityServiceImpl </p>
 * <p>Description:Order4ActivityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class Order4ActivityServiceImpl extends GenericServiceImpl<Order4Activity> implements Order4ActivityService
{
    protected Order4ActivityMapper order4ActivityMapper;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @Autowired(required = false)
    private WalletAssetFlowsService walletAssetFlowsService;

    @Autowired(required = false)
    public Order4ActivityServiceImpl(Order4ActivityMapper order4ActivityMapper)
    {
        super(order4ActivityMapper);
        this.order4ActivityMapper = order4ActivityMapper;
    }

    @Override
    public Long getOrder4ActivityAccountNum(String activityType) throws BusinessException
    {
        return order4ActivityMapper.getOrder4ActivityAccountNum(activityType);
    }

    @Override
    public Long getOrder4ActivityIsWinningAccountNum(String activityType) throws BusinessException
    {
        return order4ActivityMapper.getOrder4ActivityIsWinningAccountNum(activityType);
    }

    @Override
    public void treasureHuntOrder4ActivityClaimLottery() throws BusinessException
    {
        Order4Activity order4ActivitySearch = new Order4Activity();
        order4ActivitySearch.setActivityType(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT); //一元夺宝
        order4ActivitySearch.setIsLotteryDrawn(true);
        order4ActivitySearch.setIsWinning(true);
        order4ActivitySearch.setIsClaimLottery(false);
        List<Order4Activity> listOrder4Activity = order4ActivityMapper.findList(order4ActivitySearch);
        listOrder4Activity.stream().forEach(order4ActivityWinning ->
        {
            //
            //
            StringBuilder redisLockAssetName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
            redisLockAssetName.append(order4ActivityWinning.getAccountId());
            log.info("redisLockAssetName:{}", redisLockAssetName.toString());
            // 分布式redis锁判断
            RedisLock redisLockAsset = new RedisLock(redisTemplate, redisLockAssetName.toString(), 3);
            if (redisLockAsset.lock())
            {
                log.info("拿到分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                try {
                    //
                    // 中奖人员领奖
                    this.treasureHuntOrder4ActivityClaimLottery4SingalAccount(order4ActivityWinning);
                    //
                } catch (BusinessException e) {
                    log.error("一元夺宝活动订单中奖未领取 进行领取中奖 异常:{}", e.getLocalizedMessage());
                    log.error("继续下一条进行领奖处理");
                } finally {
                    log.info("释放分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                    redisLockAsset.unlock();
                }
            } else {
                log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
                log.error("继续下一条进行领奖处理");
            }
            //
        });
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void treasureHuntOrder4ActivityClaimLottery4SingalAccount(Order4Activity order4ActivityWinning) throws BusinessException
    {
        log.info("treasureHuntOrder4ActivityClaimLottery4SingalAccount中奖订单order4ActivityWinning:{}", order4ActivityWinning.toString());
        if(order4ActivityWinning.getIsClaimLottery()){
            log.error("order4ActivityWinning已中奖领取，不能再重复领取");
            return;
        }
        // 获取中奖订单对应账户的钱包资产信息
        WalletAsset walletAssetWinningDB = walletAssetService.findByAccountIdAndCurrency(order4ActivityWinning.getAccountId(), GlobalConst.CURRENCY_PKR);
        log.info("获取中奖订单对应账户的钱包资产信息walletAssetWinningDB:{}", walletAssetWinningDB.toString());
        // 需要 等值金额增加 的金额  =  商品活动原价 * 订单购买数量
        // BigDecimal equalAmountIncreaseNeedBalance = order4ActivityWinning.getActivitySkuPrice().multiply(BigDecimal.valueOf(order4ActivityWinning.getOrderActPurchaseNum()));
        BigDecimal equalAmountIncreaseNeedBalance = order4ActivityWinning.getActivitySkuPrice();
        // 增加账户资产流水
        WalletAssetFlows walletAssetFlowsAdd = new WalletAssetFlows();
        walletAssetFlowsAdd.setAccountId(walletAssetWinningDB.getAccountId());
        walletAssetFlowsAdd.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsAdd.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        walletAssetFlowsAdd.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_TREASUREHUNT_REWARD);
        walletAssetFlowsAdd.setBeforeBalance(walletAssetWinningDB.getBalance());
        walletAssetFlowsAdd.setBalance(equalAmountIncreaseNeedBalance);
        walletAssetFlowsAdd.setFee(BigDecimal.ZERO);
        walletAssetFlowsAdd.setDirection("+");
        walletAssetFlowsAdd.setAfterBalance(walletAssetWinningDB.getBalance().add(equalAmountIncreaseNeedBalance));
        walletAssetFlowsAdd.setOrgBusinessId(order4ActivityWinning.getId());
        walletAssetFlowsAdd.setOrgBusinessNo(order4ActivityWinning.getOrderTxNo());
        walletAssetFlowsAdd.setStatus(true);
        walletAssetFlowsAdd.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsAdd.setRemark("Bonus-Lucky");
        log.info("增加账户资产流水 walletAssetFlowsAdd:{}", walletAssetFlowsAdd);
        walletAssetFlowsService.insert(walletAssetFlowsAdd);

        // 增加账户资产
        walletAssetWinningDB.setBalance(walletAssetWinningDB.getBalance().add(equalAmountIncreaseNeedBalance));
        walletAssetWinningDB.setUpdateTime(System.currentTimeMillis());
        log.info("增加账户资产 walletAssetWinningDB:{}", walletAssetWinningDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetWinningDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetWinningDB.getBalance(), walletAssetFlowsAdd.getAfterBalance());

        //
        order4ActivityWinning.setOrderStatus(9); // 9已缺货等值金额充抵
        order4ActivityWinning.setIsClaimLottery(true); // 已领取 核心属性
        order4ActivityWinning.setUpdateTime(System.currentTimeMillis());
        log.info("中奖订单更新order4ActivityWinning:{}", order4ActivityWinning);
        order4ActivityMapper.updateByPrimaryKeySelective(order4ActivityWinning);
    }
}
