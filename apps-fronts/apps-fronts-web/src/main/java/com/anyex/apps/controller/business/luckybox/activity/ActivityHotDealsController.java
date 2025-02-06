/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.business.luckybox.activity.entity.ActivityHotDeals;
import com.anyex.apps.business.luckybox.activity.service.ActivityHotDealsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityHotDealsPagination;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqAttendActivityHotDeals;
import com.anyex.apps.controller.business.luckybox.activity.resp.RespActivityPlayerData;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import com.anyex.apps.business.luckybox.order.service.Order4ActivityService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisLock;
import com.anyex.apps.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动半价购买 控制器
 * <p>File：ActivityHotDealsController.java </p>
 * <p>Title: ActivityHotDealsController </p>
 * <p>Description:ActivityHotDealsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/activity/activityHotDeals")
@Api(tags = "活动半价购买")
public class ActivityHotDealsController extends GenericController
{
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private SysParameterService sysParameterService;

    @Autowired(required = false)
    private ActivityHotDealsService activityHotDealsService;

    @Autowired(required = false)
    private Order4ActivityService order4ActivityService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询活动半价购买分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ActivityHotDeals>> data(@Validated @RequestBody ReqActivityHotDealsPagination reqActivityHotDealsPagination) throws BusinessException
    {
        //
        ActivityHotDeals activityHotDeals = new ActivityHotDeals();
        BeanUtils.copyProperties(reqActivityHotDealsPagination, activityHotDeals);
        activityHotDeals.setStatus(true); // 活动是否启用
        //
        PaginateResult<ActivityHotDeals> result = activityHotDealsService.search(reqActivityHotDealsPagination, activityHotDeals);
        result.getRecords().stream().forEach(record ->{
            record.setActCurrentPurchasedNum(record.getActCurrentPurchasedNum() + record.getActivityRobotNum()); //活动当前轮已购买份数 = 活动当前轮已购买份数 + 活动一轮机器人份数;
        });
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/playerData")
    @ApiOperation(value = "查询活动玩家数据", httpMethod = "GET")
    public JsonMessage<RespActivityPlayerData> playerData() throws BusinessException
    {
        //
        RespActivityPlayerData respActivityPlayerData = new RespActivityPlayerData();
        //
        Long browseNum = 0l;
        Long participationAccountNum = 0l;
        //
        Pagination pagination = new Pagination();
        Order4Activity order4Activity = new Order4Activity();
        order4Activity.setActivityType(GlobalConst.ACTIVITY_TYPE_HOTDEALS);
        order4Activity.setIsWinning(true);
        List<Order4Activity> listWinningOrder4Activity = order4ActivityService.search(pagination, order4Activity).getRecords();
        listWinningOrder4Activity.stream().forEach(entity->
        {
            entity.setEmail(DesensitizedUtil.email(entity.getEmail()));
        });
        //
        respActivityPlayerData.setBrowseNum(browseNum);
        respActivityPlayerData.setParticipationAccountNum(participationAccountNum);
        respActivityPlayerData.setListWinningOrder4Activity(listWinningOrder4Activity);
        log.info("respActivityPlayerData:{}", respActivityPlayerData);
        return getJsonMessage(CommonEnums.SUCCESS, respActivityPlayerData);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取活动半价购买", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<ActivityHotDeals> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        ActivityHotDeals activityHotDealsDB = activityHotDealsService.selectByPrimaryKey(id);
        if(null != activityHotDealsDB){
            //活动当前轮已购买份数 = 活动当前轮已购买份数 + 活动一轮机器人份数;
            activityHotDealsDB.setActCurrentPurchasedNum(activityHotDealsDB.getActCurrentPurchasedNum() + activityHotDealsDB.getActivityRobotNum());
            activityHotDealsDB.setAccountCurrentPurchasedNum(0);
            //
            //
            if(null != principal) {
                Order4Activity order4Activity = new Order4Activity();
                order4Activity.setActivityType(GlobalConst.ACTIVITY_TYPE_HOTDEALS);
                order4Activity.setActivityId(id);
                order4Activity.setAccountId(principal.getId());
                order4Activity.setActivityCurrentRound(activityHotDealsDB.getActCurrentRound());
                List<Order4Activity> listOrder4Activity = order4ActivityService.findList(order4Activity);
                //
                listOrder4Activity.stream().forEach(order4ActivityInList -> {
                    activityHotDealsDB.setAccountCurrentPurchasedNum(activityHotDealsDB.getAccountCurrentPurchasedNum() + order4ActivityInList.getOrderActPurchaseNum());
                });
            }
            log.info("activityHotDealsDB:{}", activityHotDealsDB);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, activityHotDealsDB);
    }

    @PostMapping(value = "/attendActivity")
    @ApiOperation(value = "参加活动", httpMethod = "POST")
    public JsonMessage attendActivityHotDeals(@RequestBody ReqAttendActivityHotDeals reqAttendActivityHotDeals) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        if(StringUtils.equalsIgnoreCase(sysParameterService.getParameterByName("SystemTradeSwitch").getValue(), "OFF"))
        {
            log.error("系统开关已关闭");
            throw new BusinessException(CommonEnums.RISK_TRADE_OFF);
        }
        //
        log.info("reqAttendActivityHotDeals:{}", reqAttendActivityHotDeals);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqAttendActivityHotDeals))
        {
            StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_ACTIVITY_HOTDEALS_PREFIX);
            redisLockName.append(reqAttendActivityHotDeals.getActivityId());
            log.info("redisLockName:{}", redisLockName.toString());
            // 分布式redis锁判断
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock())
            {
                log.info("拿到分布式redis锁:{}, ts:{}", redisLockName.toString(), System.currentTimeMillis());
                try {
                    //
                    //
                    StringBuilder redisLockAssetName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
                    redisLockAssetName.append(principal.getId());
                    log.info("redisLockAssetName:{}", redisLockAssetName.toString());
                    // 分布式redis锁判断
                    RedisLock redisLockAsset = new RedisLock(redisTemplate, redisLockAssetName.toString(), 3);
                    if (redisLockAsset.lock())
                    {
                        log.info("拿到分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                        try {
                            //
                            // 参与活动核心逻辑
                            activityHotDealsService.attendActivityHotDeals(principal.getId(),
                                    reqAttendActivityHotDeals.getActivityId(), reqAttendActivityHotDeals.getActivityPurchaseNum());
                            //
                        } catch (BusinessException e) {
                            log.error("attendActivityHotDeals参与活动异常:{}", e.getLocalizedMessage());
                            throw e;
                        } finally {
                            log.info("释放分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                            redisLockAsset.unlock();
                        }
                    } else {
                        log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
                        throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
                    }
                    //
                } catch (BusinessException e) {
                    log.error("attendActivityHotDeals参与活动异常:{}", e.getLocalizedMessage());
                    throw e;
                } finally {
                    log.info("释放分布式redis锁:{}, ts:{}", redisLockName.toString(), System.currentTimeMillis());
                    redisLock.unlock();
                }
            } else {
                log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
                throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
            }
        }
        //
        return json;
    }

    @PostMapping(value = "/balancePayment")
    @ApiOperation(value = "尾款支付", httpMethod = "POST")
    public JsonMessage balancePayment(@RequestBody ReqIdParam reqIdParam) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        if(StringUtils.equalsIgnoreCase(sysParameterService.getParameterByName("SystemTradeSwitch").getValue(), "OFF"))
        {
            log.error("系统开关已关闭");
            throw new BusinessException(CommonEnums.RISK_TRADE_OFF);
        }
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        StringBuilder redisLockAssetName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockAssetName.append(principal.getId());
        log.info("redisLockAssetName:{}", redisLockAssetName.toString());
        // 分布式redis锁判断
        RedisLock redisLockAsset = new RedisLock(redisTemplate, redisLockAssetName.toString(), 3);
        if (redisLockAsset.lock())
        {
            log.info("拿到分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
            try {
                //
                Order4Activity order4ActivityDB = order4ActivityService.selectByPrimaryKey(reqIdParam.getId());
                if(null == order4ActivityDB || principal.getId().longValue() != order4ActivityDB.getAccountId().longValue())
                {
                    log.error("非法请求");
                    throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                }
                //
                log.info("order4Activity:{}", order4ActivityDB);
                // 未开奖 || 未中奖 || 3已中奖待支付尾款
                if(!order4ActivityDB.getIsLotteryDrawn().equals(true) || !order4ActivityDB.getIsWinning().equals(true) || order4ActivityDB.getOrderStatus().intValue() != 3)
                {
                    log.error("订单不满足尾款支付条件直接忽略，非法请求");
                    throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                }
                // 尾款支付核心逻辑
                activityHotDealsService.balancePayment(principal.getId(), order4ActivityDB.getId());
                //
            } catch (BusinessException e) {
                log.error("balancePayment尾款支付异常:{}", e.getLocalizedMessage());
                throw e;
            } finally {
                log.info("释放分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                redisLockAsset.unlock();
            }
        } else {
            log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }
        //
        return json;
    }

    @PostMapping(value = "/refund")
    @ApiOperation(value = "抵扣返现", httpMethod = "POST")
    public JsonMessage refund(@RequestBody ReqIdParam reqIdParam) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        if(StringUtils.equalsIgnoreCase(sysParameterService.getParameterByName("SystemTradeSwitch").getValue(), "OFF"))
        {
            log.error("系统开关已关闭");
            throw new BusinessException(CommonEnums.RISK_TRADE_OFF);
        }
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        StringBuilder redisLockAssetName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockAssetName.append(principal.getId());
        log.info("redisLockAssetName:{}", redisLockAssetName.toString());
        // 分布式redis锁判断
        RedisLock redisLockAsset = new RedisLock(redisTemplate, redisLockAssetName.toString(), 3);
        if (redisLockAsset.lock())
        {
            log.info("拿到分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
            try {
                //
                Order4Activity order4ActivityDB = order4ActivityService.selectByPrimaryKey(reqIdParam.getId());
                if(null == order4ActivityDB || principal.getId().longValue() != order4ActivityDB.getAccountId().longValue())
                {
                    log.error("非法请求");
                    throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                }
                //
                log.info("order4Activity:{}", order4ActivityDB);
                // 未开奖 || 未中奖 || 3已中奖待支付尾款
                if(!order4ActivityDB.getIsLotteryDrawn().equals(true) || !order4ActivityDB.getIsWinning().equals(true) || order4ActivityDB.getOrderStatus().intValue() != 3)
                {
                    log.error("订单不满足抵扣返现条件直接忽略，非法请求");
                    throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                }
                // 抵扣返现核心逻辑
                activityHotDealsService.refund(principal.getId(), order4ActivityDB.getId());
                //
            } catch (BusinessException e) {
                log.error("refund抵扣返现异常:{}", e.getLocalizedMessage());
                throw e;
            } finally {
                log.info("释放分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                redisLockAsset.unlock();
            }
        } else {
            log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }
        //
        return json;
    }
}
