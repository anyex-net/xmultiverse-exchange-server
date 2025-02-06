/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.service;

import com.anyex.apps.business.luckybox.activity.entity.ActivityTreasureHunt;
import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.activity.mapper.ActivityTreasureHuntMapper;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSku;
import com.anyex.apps.business.luckybox.goods.service.GoodsSkuService;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import com.anyex.apps.business.luckybox.order.service.Order4ActivityService;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 活动一元夺宝表 服务实现类
 * <p>File：ActivityTreasureHuntServiceImpl.java </p>
 * <p>Title: ActivityTreasureHuntServiceImpl </p>
 * <p>Description:ActivityTreasureHuntServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class ActivityTreasureHuntServiceImpl extends GenericServiceImpl<ActivityTreasureHunt> implements ActivityTreasureHuntService
{
    protected ActivityTreasureHuntMapper activityTreasureHuntMapper;

    @Autowired(required = false)
    private GoodsSkuService goodsSkuService;

    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @Autowired(required = false)
    private WalletAssetFlowsService walletAssetFlowsService;

    @Autowired(required = false)
    private Order4ActivityService order4ActivityService;

    @Autowired(required = false)
    public ActivityTreasureHuntServiceImpl(ActivityTreasureHuntMapper activityTreasureHuntMapper)
    {
        super(activityTreasureHuntMapper);
        this.activityTreasureHuntMapper = activityTreasureHuntMapper;
    }

    /**
     * 参加活动 满足条件即可开奖
     *
     * @param accountId
     * @param activityId
     * @param activityPurchaseNum
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void attendActivityTreasureHunt(Long accountId, Long activityId, Integer activityPurchaseNum) throws BusinessException
    {
        //
        //
        // 参与活动核心逻辑
        //
        // 判断活动是否存在
        ActivityTreasureHunt activityTreasureHuntDB = activityTreasureHuntMapper.selectByPrimaryKey(activityId);
        if(null == activityTreasureHuntDB || !activityTreasureHuntDB.getStatus()) {
            log.error("判断活动是否存在异常");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        // 判断商品SKU是否存在
        GoodsSku goodsSkuDB = goodsSkuService.selectByPrimaryKey(activityTreasureHuntDB.getSkuId());
        if(null == goodsSkuDB) {
            log.error("判断商品SKU是否存在异常");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        // 判断购买数量 是否大于 活动当前轮剩下还可以购买的数量
        log.info("购买数量:{}, 活动当前轮剩下还可以购买的数量:{}",
                activityPurchaseNum, activityTreasureHuntDB.getTreasureSumNum() - activityTreasureHuntDB.getActCurrentPurchasedNum() - activityTreasureHuntDB.getTreasureRobotNum());
        if(activityPurchaseNum > (activityTreasureHuntDB.getTreasureSumNum() - activityTreasureHuntDB.getActCurrentPurchasedNum() - activityTreasureHuntDB.getTreasureRobotNum() )){
            log.error("判断购买数量 是否大于 活动当前轮剩下还可以购买的数量异常,可用数量余额不足");
            throw new BusinessException(CommonEnums.RISK_ENABLE_QUANTITY_NOTAVAILABLE);
        }
        // 判断账户资产是否足够
        // 购买金额 = 购买价格*购买数量
        BigDecimal purchaseAmountNeedBalance = activityTreasureHuntDB.getTreasurePrice().multiply(BigDecimal.valueOf(activityPurchaseNum));
        log.info("购买金额 purchaseAmountNeedBalance:{}", purchaseAmountNeedBalance);
        WalletAsset walletAssetDB = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        log.info("判断账户资产是否足够walletAssetDB:{}", walletAssetDB.toString());
        if(null == walletAssetDB || (walletAssetDB.getBalance().subtract(walletAssetDB.getFrozenBal())).compareTo(purchaseAmountNeedBalance) < 0){
            log.error("判断账户资产是否足够异常,可用资金余额不足");
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        // 插入活动订单记录
        Order4Activity order4ActivityNew = new Order4Activity();
        order4ActivityNew.setId(SerialnoUtils.buildPrimaryKey());
        order4ActivityNew.setOrderTxNo(SerialnoUtils.getOrderNum()); // 订单编号
        order4ActivityNew.setActivityType(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT);
        order4ActivityNew.setActivityId(activityId);
        order4ActivityNew.setSkuId(activityTreasureHuntDB.getSkuId());
        order4ActivityNew.setSpuId(activityTreasureHuntDB.getSpuId());
        order4ActivityNew.setActivitySkuPrice(goodsSkuDB.getPrice()); // 商品活动原价
        order4ActivityNew.setAccountId(accountId);
        order4ActivityNew.setOrderActPrice(activityTreasureHuntDB.getTreasurePrice());
        order4ActivityNew.setOrderActBalancePayment(BigDecimal.ZERO); // 尾款支付为0
        order4ActivityNew.setOrderActPurchaseNum(activityPurchaseNum);
        order4ActivityNew.setOrderSumBalance(purchaseAmountNeedBalance);
        order4ActivityNew.setOrderStatus(0); // 未开奖
        order4ActivityNew.setActivitySumNum(activityTreasureHuntDB.getTreasureSumNum());
        order4ActivityNew.setActivitySumRound(activityTreasureHuntDB.getTreasureSumRound());
        order4ActivityNew.setActivityCurrentRound(activityTreasureHuntDB.getActCurrentRound());
        order4ActivityNew.setIsLotteryDrawn(false); // 未开奖
        order4ActivityNew.setIsWinning(false); // 未中奖
        order4ActivityNew.setIsClaimLottery(false); // 未领取
        order4ActivityNew.setRemark("Lucky");
        order4ActivityNew.setCreateTime(System.currentTimeMillis());
        log.info("order4Activity:{}", order4ActivityNew);
        order4ActivityService.insert(order4ActivityNew);

        // 扣减账户资产流水
        WalletAssetFlows walletAssetFlowsSub = new WalletAssetFlows();
        walletAssetFlowsSub.setAccountId(accountId);
        walletAssetFlowsSub.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsSub.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
        walletAssetFlowsSub.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_TREASUREHUNT);
        walletAssetFlowsSub.setBeforeBalance(walletAssetDB.getBalance());
        walletAssetFlowsSub.setBalance(purchaseAmountNeedBalance);
        walletAssetFlowsSub.setFee(BigDecimal.ZERO);
        walletAssetFlowsSub.setDirection("-");
        walletAssetFlowsSub.setAfterBalance(walletAssetDB.getBalance().subtract(purchaseAmountNeedBalance));
        walletAssetFlowsSub.setOrgBusinessId(order4ActivityNew.getId());
        walletAssetFlowsSub.setOrgBusinessNo(order4ActivityNew.getOrderTxNo());
        walletAssetFlowsSub.setStatus(true);
        walletAssetFlowsSub.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsSub.setRemark("Lucky");
        log.info("扣减账户资产流水 walletAssetFlowsSub:{}", walletAssetFlowsSub);
        walletAssetFlowsService.insert(walletAssetFlowsSub);

        // 扣减账户资产
        walletAssetDB.setBalance(walletAssetDB.getBalance().subtract(purchaseAmountNeedBalance));
        walletAssetDB.setUpdateTime(System.currentTimeMillis());
        log.info("扣减账户资产 walletAssetDB:{}", walletAssetDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsSub.getAfterBalance());

        //
        // 更新activityTreasureHunt相关信息
        activityTreasureHuntDB.setActCurrentPurchasedNum(activityTreasureHuntDB.getActCurrentPurchasedNum() + activityPurchaseNum);
        activityTreasureHuntDB.setUpdateTime(System.currentTimeMillis());
        log.info("更新activityTreasureHunt相关信息 activityTreasureHuntDB:{}", activityTreasureHuntDB);
        activityTreasureHuntMapper.updateByPrimaryKeySelective(activityTreasureHuntDB);

        //
        // 判断是否满足条件开奖并且启动新一轮     当前轮购买量 = 一轮总份数 - 一轮机器人份数
        log.info("当前轮购买量 = 一轮总份数 - 一轮机器人份数:{}", activityTreasureHuntDB.getActCurrentPurchasedNum().equals( activityTreasureHuntDB.getTreasureSumNum() - activityTreasureHuntDB.getTreasureRobotNum() ));
        if(activityTreasureHuntDB.getActCurrentPurchasedNum().equals( activityTreasureHuntDB.getTreasureSumNum() - activityTreasureHuntDB.getTreasureRobotNum() ))
        {
            // 抽奖开始
            Order4Activity order4ActivitySearch = new Order4Activity();
            order4ActivitySearch.setActivityId(activityId);
            order4ActivitySearch.setIsLotteryDrawn(false);
            List<Order4Activity> listOrder4Activity = order4ActivityService.findList(order4ActivitySearch);
            List<Long> listOrder4ActivityId = new ArrayList<Long>();
            for(Order4Activity order4Activity : listOrder4Activity)
            {
                for(int i = 0; i < order4Activity.getOrderActPurchaseNum(); i++) {
                    listOrder4ActivityId.add(order4Activity.getId());
                }
                // 更新已开奖
                order4Activity.setOrderStatus(1); // 1未中奖
                order4Activity.setIsLotteryDrawn(true); // 已开奖
                order4Activity.setIsWinning(false); // 未中奖
                order4Activity.setIsClaimLottery(false); // 未领取
                order4Activity.setUpdateTime(System.currentTimeMillis());
                //
                log.info("抽奖更新order4Activity:{}", order4Activity);
                order4ActivityService.updateByPrimaryKeySelective(order4Activity);
            }
            // 达标了进行随机开奖
            log.info("达标了进行随机开奖 listOrder4ActivityId size:{}", listOrder4ActivityId.size());
            Collections.shuffle(listOrder4ActivityId);
            int index = new Random().nextInt(listOrder4ActivityId.size());
            long order4ActivityId = listOrder4ActivityId.get(index);
            Order4Activity order4ActivityWinning = order4ActivityService.selectByPrimaryKey(order4ActivityId);
            order4ActivityWinning.setOrderStatus(2); // 2已中奖无需支付尾款待发货
            order4ActivityWinning.setIsLotteryDrawn(true); // 已开奖
            order4ActivityWinning.setIsWinning(true); // 已中奖
            order4ActivityWinning.setIsClaimLottery(false); // 未领取 核心属性
            order4ActivityWinning.setUpdateTime(System.currentTimeMillis());
            log.info("中奖订单更新order4ActivityWinning:{}", order4ActivityWinning);
            order4ActivityService.updateByPrimaryKeySelective(order4ActivityWinning);
            // 抽奖结束

//            // 获取中奖订单对应账户的钱包资产信息
//            WalletAsset walletAssetWinningDB = walletAssetService.findByAccountIdAndCurrency(order4ActivityWinning.getAccountId(), GlobalConst.CURRENCY_PKR);
//            log.info("获取中奖订单对应账户的钱包资产信息walletAssetWinningDB:{}", walletAssetWinningDB.toString());
//            // 需要 等值金额增加 的金额  =  商品活动原价 * 订单购买数量
//            // BigDecimal equalAmountIncreaseNeedBalance = order4ActivityWinning.getActivitySkuPrice().multiply(BigDecimal.valueOf(order4ActivityWinning.getOrderActPurchaseNum()));
//            BigDecimal equalAmountIncreaseNeedBalance = order4ActivityWinning.getActivitySkuPrice();
//            // 增加账户资产流水
//            WalletAssetFlows walletAssetFlowsAdd = new WalletAssetFlows();
//            walletAssetFlowsAdd.setAccountId(walletAssetWinningDB.getAccountId());
//            walletAssetFlowsAdd.setCurrency(GlobalConst.CURRENCY_PKR);
//            walletAssetFlowsAdd.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
//            walletAssetFlowsAdd.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_TREASUREHUNT_REWARD);
//            walletAssetFlowsAdd.setBeforeBalance(walletAssetWinningDB.getBalance());
//            walletAssetFlowsAdd.setBalance(equalAmountIncreaseNeedBalance);
//            walletAssetFlowsAdd.setFee(BigDecimal.ZERO);
//            walletAssetFlowsAdd.setDirection("+");
//            walletAssetFlowsAdd.setAfterBalance(walletAssetWinningDB.getBalance().add(equalAmountIncreaseNeedBalance));
//            walletAssetFlowsAdd.setOrgBusinessId(order4ActivityWinning.getId());
//            walletAssetFlowsAdd.setOrgBusinessNo(order4ActivityWinning.getOrdersTxNo());
//            walletAssetFlowsAdd.setStatus(true);
//            walletAssetFlowsAdd.setCreateTime(System.currentTimeMillis());
//            walletAssetFlowsAdd.setRemark("Bonus-Lucky");
//            log.info("增加账户资产流水 walletAssetFlowsAdd:{}", walletAssetFlowsAdd);
//            walletAssetFlowsService.insert(walletAssetFlowsAdd);
//
//            // 增加账户资产
//            walletAssetWinningDB.setBalance(walletAssetWinningDB.getBalance().add(equalAmountIncreaseNeedBalance));
//            walletAssetWinningDB.setUpdateTime(System.currentTimeMillis());
//            log.info("增加账户资产 walletAssetWinningDB:{}", walletAssetWinningDB);
//            walletAssetService.updateByPrimaryKeySelective(walletAssetWinningDB);
//
//            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
//            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetWinningDB.getBalance(), walletAssetFlowsAdd.getAfterBalance());

            //
            // 更新activityTreasureHunt相关信息
            activityTreasureHuntDB.setActCurrentRound(activityTreasureHuntDB.getActCurrentRound()+1); // 当前轮数+1
            activityTreasureHuntDB.setActCurrentPurchasedNum(0); // 当前轮购买量重置为0
            activityTreasureHuntDB.setUpdateTime(System.currentTimeMillis());
            log.info("满足条件开奖并且启动新一轮 更新activityTreasureHunt相关信息 activityTreasureHuntDB:{}", activityTreasureHuntDB);
            activityTreasureHuntMapper.updateByPrimaryKeySelective(activityTreasureHuntDB);
        }
        //
    }
}
