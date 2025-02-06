/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.service;

import com.anyex.apps.business.luckybox.activity.entity.ActivityHotDeals;
import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.activity.mapper.ActivityHotDealsMapper;
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
 * 活动半价购买表 服务实现类
 * <p>File：ActivityHotDealsServiceImpl.java </p>
 * <p>Title: ActivityHotDealsServiceImpl </p>
 * <p>Description:ActivityHotDealsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class ActivityHotDealsServiceImpl extends GenericServiceImpl<ActivityHotDeals> implements ActivityHotDealsService
{
    protected ActivityHotDealsMapper activityHotDealsMapper;

    @Autowired(required = false)
    private GoodsSkuService goodsSkuService;

    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @Autowired(required = false)
    private WalletAssetFlowsService walletAssetFlowsService;

    @Autowired(required = false)
    private Order4ActivityService order4ActivityService;

    @Autowired(required = false)
    public ActivityHotDealsServiceImpl(ActivityHotDealsMapper activityHotDealsMapper)
    {
        super(activityHotDealsMapper);
        this.activityHotDealsMapper = activityHotDealsMapper;
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
    public void attendActivityHotDeals(Long accountId, Long activityId, Integer activityPurchaseNum) throws BusinessException
    {
        //
        // 参与活动核心逻辑
        //
        // 判断活动是否存在
        ActivityHotDeals activityHotDealsDB = activityHotDealsMapper.selectByPrimaryKey(activityId);
        if(null == activityHotDealsDB || !activityHotDealsDB.getStatus()) {
            log.error("判断活动是否存在异常");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        // 判断商品SKU是否存在
        GoodsSku goodsSkuDB = goodsSkuService.selectByPrimaryKey(activityHotDealsDB.getSkuId());
        if(null == goodsSkuDB) {
            log.error("判断商品SKU是否存在异常");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //

        // 判断购买数量 是否大于 活动当前轮剩下还可以购买的数量
        log.info("购买数量:{}, 活动当前轮剩下还可以购买的数量:{}",
                activityPurchaseNum, activityHotDealsDB.getActivitySumNum() - activityHotDealsDB.getActCurrentPurchasedNum() - activityHotDealsDB.getActivityRobotNum());
        if(activityPurchaseNum > (activityHotDealsDB.getActivitySumNum() - activityHotDealsDB.getActCurrentPurchasedNum() - activityHotDealsDB.getActivityRobotNum() )){
            log.error("判断购买数量 是否大于 活动当前轮剩下还可以购买的数量异常,可用数量余额不足");
            throw new BusinessException(CommonEnums.RISK_ENABLE_QUANTITY_NOTAVAILABLE);
        }
        // 判断账户资产是否足够
        // 购买金额当前 = 购买价格*购买数量
        BigDecimal purchaseAmountNeedBalanceCurrent = activityHotDealsDB.getActivityPrice().multiply(BigDecimal.valueOf(activityPurchaseNum));
        log.info("购买金额 purchaseAmountNeedBalanceCurrent:{}", purchaseAmountNeedBalanceCurrent);
        WalletAsset walletAssetDB = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        log.info("判断账户资产是否足够walletAssetDB:{}", walletAssetDB.toString());
        if(null == walletAssetDB || (walletAssetDB.getBalance().subtract(walletAssetDB.getFrozenBal())).compareTo(purchaseAmountNeedBalanceCurrent) < 0){
            log.error("判断账户资产是否足够异常,可用资金余额不足");
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }
        // 尾款金额 = 活动单件尾款金额 * 活动购买数量
        // BigDecimal purchaseAmountNeedBalancePaymentBalance = activityHotDealsDB.getBalancePayment().multiply(BigDecimal.valueOf(activityPurchaseNum));
        BigDecimal purchaseAmountNeedBalancePaymentBalance = activityHotDealsDB.getBalancePayment();
        log.info("购买金额 purchaseAmountNeedBalancePaymentBalance:{}", purchaseAmountNeedBalancePaymentBalance);
        // 购买金额总共 = 购买金额当前 + 尾款金额
        BigDecimal purchaseAmountNeedBalanceAll = purchaseAmountNeedBalanceCurrent.add(purchaseAmountNeedBalancePaymentBalance);
        log.info("购买金额 purchaseAmountNeedBalanceAll:{}", purchaseAmountNeedBalanceAll);

        // 插入活动订单记录
        Order4Activity order4ActivityNew = new Order4Activity();
        order4ActivityNew.setId(SerialnoUtils.buildPrimaryKey());
        order4ActivityNew.setOrderTxNo(SerialnoUtils.getOrderNum()); // 订单编号
        order4ActivityNew.setActivityType(GlobalConst.ACTIVITY_TYPE_HOTDEALS);
        order4ActivityNew.setActivityId(activityId);
        order4ActivityNew.setSkuId(activityHotDealsDB.getSkuId());
        order4ActivityNew.setSpuId(activityHotDealsDB.getSpuId());
        order4ActivityNew.setActivitySkuPrice(goodsSkuDB.getPrice()); // 商品活动原价
        order4ActivityNew.setAccountId(accountId);
        order4ActivityNew.setOrderActPrice(activityHotDealsDB.getActivityPrice());
        order4ActivityNew.setOrderActBalancePayment(purchaseAmountNeedBalancePaymentBalance); // 尾款支付非0
        order4ActivityNew.setOrderActPurchaseNum(activityPurchaseNum);
        order4ActivityNew.setOrderSumBalance(purchaseAmountNeedBalanceAll); // 订单总金额
        order4ActivityNew.setOrderStatus(0); // 未开奖
        order4ActivityNew.setActivitySumNum(activityHotDealsDB.getActivitySumNum());
        order4ActivityNew.setActivitySumRound(activityHotDealsDB.getActivitySumRound());
        order4ActivityNew.setActivityCurrentRound(activityHotDealsDB.getActCurrentRound());
        order4ActivityNew.setIsLotteryDrawn(false); // 未开奖
        order4ActivityNew.setIsWinning(false); // 未中奖
        order4ActivityNew.setIsClaimLottery(false); // 未领取
        order4ActivityNew.setRemark("Shop");
        order4ActivityNew.setCreateTime(System.currentTimeMillis());
        log.info("order4Activity:{}", order4ActivityNew);
        order4ActivityService.insert(order4ActivityNew);

        // 扣减账户资产流水
        WalletAssetFlows walletAssetFlowsSub = new WalletAssetFlows();
        walletAssetFlowsSub.setAccountId(accountId);
        walletAssetFlowsSub.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsSub.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
        walletAssetFlowsSub.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_HOTDEALS);
        walletAssetFlowsSub.setBeforeBalance(walletAssetDB.getBalance());
        walletAssetFlowsSub.setBalance(purchaseAmountNeedBalanceCurrent);
        walletAssetFlowsSub.setFee(BigDecimal.ZERO);
        walletAssetFlowsSub.setDirection("-");
        walletAssetFlowsSub.setAfterBalance(walletAssetDB.getBalance().subtract(purchaseAmountNeedBalanceCurrent));
        walletAssetFlowsSub.setOrgBusinessId(order4ActivityNew.getId());
        walletAssetFlowsSub.setOrgBusinessNo(order4ActivityNew.getOrderTxNo());
        walletAssetFlowsSub.setStatus(true);
        walletAssetFlowsSub.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsSub.setRemark("Shop");
        log.info("扣减账户资产流水 walletAssetFlowsSub:{}", walletAssetFlowsSub);
        walletAssetFlowsService.insert(walletAssetFlowsSub);

        // 扣减账户资产
        walletAssetDB.setBalance(walletAssetDB.getBalance().subtract(purchaseAmountNeedBalanceCurrent));
        walletAssetDB.setUpdateTime(System.currentTimeMillis());
        log.info("扣减账户资产 walletAssetDB:{}", walletAssetDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsSub.getAfterBalance());

        // 更新activityHotDeals相关信息
        activityHotDealsDB.setActCurrentPurchasedNum(activityHotDealsDB.getActCurrentPurchasedNum() + activityPurchaseNum);
        activityHotDealsDB.setUpdateTime(System.currentTimeMillis());
        log.info("更新activityHotDeals相关信息 activityHotDealsDB:{}", activityHotDealsDB);
        activityHotDealsMapper.updateByPrimaryKeySelective(activityHotDealsDB);

        //
        // 判断是否满足条件开奖并且启动新一轮     当前轮购买量 = 一轮总份数 - 一轮机器人份数
        log.info("当前轮购买量 = 一轮总份数 - 一轮机器人份数:{}", activityHotDealsDB.getActCurrentPurchasedNum().equals( activityHotDealsDB.getActivitySumNum() - activityHotDealsDB.getActivityRobotNum() ));
        if(activityHotDealsDB.getActCurrentPurchasedNum().equals( activityHotDealsDB.getActivitySumNum() - activityHotDealsDB.getActivityRobotNum() ))
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
            //
            // 达标了进行随机开奖
            log.info("达标了进行随机开奖 listOrder4ActivityId size:{}", listOrder4ActivityId.size());
            Collections.shuffle(listOrder4ActivityId);
            int index = new Random().nextInt(listOrder4ActivityId.size());
            long order4ActivityId = listOrder4ActivityId.get(index);
            Order4Activity order4ActivityWinning = order4ActivityService.selectByPrimaryKey(order4ActivityId);
            order4ActivityWinning.setOrderStatus(3); // 3已中奖待支付尾款
            order4ActivityWinning.setIsLotteryDrawn(true); // 已开奖
            order4ActivityWinning.setIsWinning(true); // 已中奖
            order4ActivityWinning.setIsClaimLottery(false); // 未领取 核心属性
            order4ActivityWinning.setUpdateTime(System.currentTimeMillis());
            log.info("中奖订单更新order4ActivityWinning:{}", order4ActivityWinning);
            order4ActivityService.updateByPrimaryKeySelective(order4ActivityWinning);
            // 抽奖结束

            //
            // 更新activityTreasureHunt相关信息
            activityHotDealsDB.setActCurrentRound(activityHotDealsDB.getActCurrentRound()+1); // 当前轮数+1
            activityHotDealsDB.setActCurrentPurchasedNum(0); // 当前轮购买量重置为0
            activityHotDealsDB.setUpdateTime(System.currentTimeMillis());
            log.info("满足条件开奖并且启动新一轮 更新activityHotDeals相关信息 activityHotDealsDB:{}", activityHotDealsDB);
            activityHotDealsMapper.updateByPrimaryKeySelective(activityHotDealsDB);
        }
        //
    }

    /**
     * 尾款支付
     *
     * @param accountId
     * @param order4ActivityId
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void balancePayment(Long accountId, Long order4ActivityId) throws BusinessException
    {
        //
        Order4Activity order4ActivityDB = order4ActivityService.selectByPrimaryKey(order4ActivityId);
        log.info("order4ActivityDB:{}", order4ActivityDB);
        // 未开奖 || 未中奖 || 3已中奖待支付尾款
        if(!order4ActivityDB.getIsLotteryDrawn().equals(true) || !order4ActivityDB.getIsWinning().equals(true) || order4ActivityDB.getOrderStatus().intValue() != 3)
        {
            log.error("订单不满足尾款支付条件直接忽略，非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //
        //
        // 尾款支付核心逻辑
        //
        // 判断账户资产是否足够  可用余额 < 活动订单尾款金额
        WalletAsset walletAssetDB = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        log.info("判断账户资产是否足够walletAssetDB:{}", walletAssetDB.toString());
        // 需要支付尾款金额
        BigDecimal balancePaymentNeedBalance = order4ActivityDB.getOrderActBalancePayment();
        if(null == walletAssetDB || (walletAssetDB.getBalance().subtract(walletAssetDB.getFrozenBal())).compareTo(balancePaymentNeedBalance) < 0){
            log.error("判断账户资产是否足够异常,可用资金余额不足");
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        // 扣减账户资产流水
        WalletAssetFlows walletAssetFlowsSub = new WalletAssetFlows();
        walletAssetFlowsSub.setAccountId(accountId);
        walletAssetFlowsSub.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsSub.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
        walletAssetFlowsSub.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_HOTDEALS_BALANCEPAYMENT);
        walletAssetFlowsSub.setBeforeBalance(walletAssetDB.getBalance());
        walletAssetFlowsSub.setBalance(balancePaymentNeedBalance);
        walletAssetFlowsSub.setFee(BigDecimal.ZERO);
        walletAssetFlowsSub.setDirection("-");
        walletAssetFlowsSub.setAfterBalance(walletAssetDB.getBalance().subtract(balancePaymentNeedBalance));
        walletAssetFlowsSub.setOrgBusinessId(order4ActivityDB.getId());
        walletAssetFlowsSub.setOrgBusinessNo(order4ActivityDB.getOrderTxNo());
        walletAssetFlowsSub.setStatus(true);
        walletAssetFlowsSub.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsSub.setRemark("Balance");
        log.info("扣减账户资产流水 walletAssetFlowsSub:{}", walletAssetFlowsSub);
        walletAssetFlowsService.insert(walletAssetFlowsSub);

        // 扣减账户资产
        walletAssetDB.setBalance(walletAssetDB.getBalance().subtract(balancePaymentNeedBalance));
        walletAssetDB.setUpdateTime(System.currentTimeMillis());
        log.info("扣减账户资产 walletAssetDB:{}", walletAssetDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsSub.getAfterBalance());

        // 更新活动订单记录
        order4ActivityDB.setIsClaimLottery(true); // 已领取
        order4ActivityDB.setOrderStatus(4); // 4已支付尾款待发货
        order4ActivityDB.setBalanceDeductionAmount(balancePaymentNeedBalance);
        order4ActivityDB.setRemark("参与活动并支付尾款");
        order4ActivityDB.setUpdateTime(System.currentTimeMillis());
        log.info("order4Activity:{}", order4ActivityDB);
        order4ActivityService.updateByPrimaryKeySelective(order4ActivityDB);

        //
        //
        // 需要 等值金额增加 的金额  =  商品活动原价 * 订单购买数
        // BigDecimal equalAmountIncreaseNeedBalance = order4ActivityDB.getActivitySkuPrice().multiply(BigDecimal.valueOf(order4ActivityDB.getOrderActPurchaseNum()));
        BigDecimal equalAmountIncreaseNeedBalance = order4ActivityDB.getActivitySkuPrice();
        //
        log.info("判断账户资产是否足够 等值金额增加 walletAssetDB:{}", walletAssetDB.toString());
        // 增加账户资产流水
        WalletAssetFlows walletAssetFlowsAdd = new WalletAssetFlows();
        walletAssetFlowsAdd.setAccountId(walletAssetDB.getAccountId());
        walletAssetFlowsAdd.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsAdd.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        walletAssetFlowsAdd.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_HOTDEALS_REWARD);
        walletAssetFlowsAdd.setBeforeBalance(walletAssetDB.getBalance());
        walletAssetFlowsAdd.setBalance(equalAmountIncreaseNeedBalance);
        walletAssetFlowsAdd.setFee(BigDecimal.ZERO);
        walletAssetFlowsAdd.setDirection("+");
        walletAssetFlowsAdd.setAfterBalance(walletAssetDB.getBalance().add(equalAmountIncreaseNeedBalance));
        walletAssetFlowsAdd.setOrgBusinessId(order4ActivityDB.getId());
        walletAssetFlowsAdd.setOrgBusinessNo(order4ActivityDB.getOrderTxNo());
        walletAssetFlowsAdd.setStatus(true);
        walletAssetFlowsAdd.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsAdd.setRemark("Bonus-Shop");
        log.info("增加账户资产流水 walletAssetFlowsAdd:{}", walletAssetFlowsAdd);
        walletAssetFlowsService.insert(walletAssetFlowsAdd);

        // 增加账户资产
        walletAssetDB.setBalance(walletAssetDB.getBalance().add(equalAmountIncreaseNeedBalance));
        walletAssetDB.setUpdateTime(System.currentTimeMillis());
        log.info("增加账户资产 walletAssetDB:{}", walletAssetDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsAdd.getAfterBalance());

        // 更新活动订单记录
        order4ActivityDB.setIsClaimLottery(true); // 已领取
        order4ActivityDB.setOrderStatus(9); // 9已缺货等值金额充抵
        order4ActivityDB.setRemark("参与活动中奖并支付尾款并已缺货等值现金充抵");
        order4ActivityDB.setUpdateTime(System.currentTimeMillis());
        log.info("order4Activity:{}", order4ActivityDB);
        order4ActivityService.updateByPrimaryKeySelective(order4ActivityDB);
        //
    }

    /**
     * 抵扣返现
     *
     * @param accountId
     * @param order4ActivityId
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void refund(Long accountId, Long order4ActivityId) throws BusinessException
    {
        //
        Order4Activity order4ActivityDB = order4ActivityService.selectByPrimaryKey(order4ActivityId);
        log.info("order4ActivityDB:{}", order4ActivityDB);
        // 未开奖 || 未中奖 || 3已中奖待支付尾款
        if(!order4ActivityDB.getIsLotteryDrawn().equals(true) || !order4ActivityDB.getIsWinning().equals(true) || order4ActivityDB.getOrderStatus().intValue() != 3)
        {
            log.error("订单不满足抵扣返现条件直接忽略，非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //
        //
        // 直接抵扣核心逻辑
        //
        // 判断账户资产是否足够  可用余额 < 活动订单尾款金额
        WalletAsset walletAssetDB = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        log.info("判断账户资产是否足够walletAssetDB:{}", walletAssetDB.toString());

        //
        // 需要 等值金额增加 的金额  =  商品活动原价 * 订单购买数
        // BigDecimal equalAmountIncreaseNeedBalance = order4ActivityDB.getActivitySkuPrice().multiply(BigDecimal.valueOf(order4ActivityDB.getOrderActPurchaseNum()));
//                BigDecimal equalAmountIncreaseNeedBalance = order4ActivityDB.getActivitySkuPrice();
        BigDecimal equalAmountIncreaseNeedBalance = order4ActivityDB.getActivitySkuPrice().subtract(order4ActivityDB.getOrderActBalancePayment());
        // 增加账户资产流水
        WalletAssetFlows walletAssetFlowsAdd = new WalletAssetFlows();
        walletAssetFlowsAdd.setAccountId(walletAssetDB.getAccountId());
        walletAssetFlowsAdd.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsAdd.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        walletAssetFlowsAdd.setBusinessType(GlobalConst.BUSINESS_TYPE_ACTIVITY_HOTDEALS_REFUND_REWARD);
        walletAssetFlowsAdd.setBeforeBalance(walletAssetDB.getBalance());
        walletAssetFlowsAdd.setBalance(equalAmountIncreaseNeedBalance);
        walletAssetFlowsAdd.setFee(BigDecimal.ZERO);
        walletAssetFlowsAdd.setDirection("+");
        walletAssetFlowsAdd.setAfterBalance(walletAssetDB.getBalance().add(equalAmountIncreaseNeedBalance));
        walletAssetFlowsAdd.setOrgBusinessId(order4ActivityDB.getId());
        walletAssetFlowsAdd.setOrgBusinessNo(order4ActivityDB.getOrderTxNo());
        walletAssetFlowsAdd.setStatus(true);
        walletAssetFlowsAdd.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsAdd.setRemark("Bonus-Refund-Shop");
        log.info("增加账户资产流水 walletAssetFlowsAdd:{}", walletAssetFlowsAdd);
        walletAssetFlowsService.insert(walletAssetFlowsAdd);

        // 增加账户资产
        walletAssetDB.setBalance(walletAssetDB.getBalance().add(equalAmountIncreaseNeedBalance));
        walletAssetDB.setUpdateTime(System.currentTimeMillis());
        log.info("增加账户资产 walletAssetDB:{}", walletAssetDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsAdd.getAfterBalance());

        // 更新活动订单记录
        order4ActivityDB.setIsClaimLottery(true); // 已领取
        order4ActivityDB.setOrderStatus(10); // 10 不支付尾款直接抵扣返现
        order4ActivityDB.setRemark("参与活动中奖并不支付尾款直接抵扣返现");
        order4ActivityDB.setUpdateTime(System.currentTimeMillis());
        log.info("order4Activity:{}", order4ActivityDB);
        order4ActivityService.updateByPrimaryKeySelective(order4ActivityDB);
        //
    }
}
