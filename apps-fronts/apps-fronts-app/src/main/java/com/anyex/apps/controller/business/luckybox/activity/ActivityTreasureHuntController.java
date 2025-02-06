/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.business.luckybox.activity.entity.ActivityOperRecord;
import com.anyex.apps.business.luckybox.activity.entity.ActivityTreasureHunt;
import com.anyex.apps.business.luckybox.activity.service.ActivityOperRecordService;
import com.anyex.apps.business.luckybox.activity.service.ActivityTreasureHuntService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityTreasureHuntPagination;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqAttendActivityTreasureHunt;
import com.anyex.apps.controller.business.luckybox.activity.resp.RespActivityPlayerData;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import com.anyex.apps.business.luckybox.order.service.Order4ActivityService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.NetworkUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动一元夺宝 控制器
 * <p>File：ActivityTreasureHuntController.java </p>
 * <p>Title: ActivityTreasureHuntController </p>
 * <p>Description:ActivityTreasureHuntController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/activity/activityTreasureHunt")
@Api(tags = "活动一元夺宝")
public class ActivityTreasureHuntController extends GenericController
{
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private SysParameterService sysParameterService;

    @Autowired(required = false)
    private ActivityTreasureHuntService activityTreasureHuntService;

    @Autowired(required = false)
    private ActivityOperRecordService activityOperRecordService;

    @Autowired(required = false)
    private Order4ActivityService order4ActivityService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询活动一元夺宝分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ActivityTreasureHunt>> data(@Validated @RequestBody ReqActivityTreasureHuntPagination reqActivityTreasureHuntPagination) throws BusinessException
    {
        //
        ActivityTreasureHunt activityTreasureHunt = new ActivityTreasureHunt();
        BeanUtils.copyProperties(reqActivityTreasureHuntPagination, activityTreasureHunt);
        activityTreasureHunt.setStatus(true); // 夺宝活动是否启用
        //
        PaginateResult<ActivityTreasureHunt> result = activityTreasureHuntService.search(reqActivityTreasureHuntPagination, activityTreasureHunt);
        result.getRecords().stream().forEach(record ->{
            record.setActCurrentPurchasedNum(record.getActCurrentPurchasedNum() + record.getTreasureRobotNum()); //夺宝当前轮已购买份数 = 夺宝当前轮已购买份数 + 夺宝一轮机器人份数;
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
        Long browseNum = activityOperRecordService.getActivityOperRecordNum(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT, GlobalConst.ACTIVITY_OPERTYPE_BROWSE);
        Long participationAccountNum = order4ActivityService.getOrder4ActivityAccountNum(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT);
        //
        Pagination pagination = new Pagination();
        Order4Activity order4Activity = new Order4Activity();
        order4Activity.setActivityType(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT);
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
    @ApiOperation(value = "根据ID取活动一元夺宝", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<ActivityTreasureHunt> findBy(HttpServletRequest request, @RequestParam("id") Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        ActivityOperRecord activityOperRecord = new ActivityOperRecord();
        activityOperRecord.setActivityId(id);
        activityOperRecord.setActivityType(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT);
        activityOperRecord.setRequestIp(NetworkUtils.getIpAddr(request));
        if(null != principal) {
            activityOperRecord.setAccountId(principal.getId());
        }
        activityOperRecord.setOperType(GlobalConst.ACTIVITY_OPERTYPE_BROWSE);
        activityOperRecord.setCreateTime(System.currentTimeMillis());
        activityOperRecord.setUpdateTime(System.currentTimeMillis());
        log.info("activityOperRecord:{}", activityOperRecord);
        activityOperRecordService.insert(activityOperRecord);
        //
        ActivityTreasureHunt activityTreasureHuntDB = activityTreasureHuntService.selectByPrimaryKey(id);
        if(null != activityTreasureHuntDB){
            //夺宝当前轮已购买份数 = 夺宝当前轮已购买份数 + 夺宝一轮机器人份数;
            activityTreasureHuntDB.setActCurrentPurchasedNum(activityTreasureHuntDB.getActCurrentPurchasedNum() + activityTreasureHuntDB.getTreasureRobotNum());
            activityTreasureHuntDB.setAccountCurrentPurchasedNum(0);
            //
            if(null != principal) {
                Order4Activity order4Activity = new Order4Activity();
                order4Activity.setActivityType(GlobalConst.ACTIVITY_TYPE_TREASUREHUNT);
                order4Activity.setActivityId(id);
                order4Activity.setAccountId(principal.getId());
                order4Activity.setActivityCurrentRound(activityTreasureHuntDB.getActCurrentRound());
                List<Order4Activity> listOrder4Activity = order4ActivityService.findList(order4Activity);
                //
                listOrder4Activity.stream().forEach(order4ActivityInList -> {
                    activityTreasureHuntDB.setAccountCurrentPurchasedNum(activityTreasureHuntDB.getAccountCurrentPurchasedNum() + order4ActivityInList.getOrderActPurchaseNum());
                });
            }
            log.info("activityTreasureHuntDB:{}", activityTreasureHuntDB);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, activityTreasureHuntDB);
    }

    @PostMapping(value = "/attendActivity")
    @ApiOperation(value = "参加活动", httpMethod = "POST")
    public JsonMessage attendActivityTreasureHunt(@RequestBody ReqAttendActivityTreasureHunt reqAttendActivityTreasureHunt) throws BusinessException
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
        log.info("reqAttendActivityTreasureHunt:{}", reqAttendActivityTreasureHunt);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqAttendActivityTreasureHunt))
        {
            StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_ACTIVITY_TREASUREHUNT_PREFIX);
            redisLockName.append(reqAttendActivityTreasureHunt.getActivityId());
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
                            activityTreasureHuntService.attendActivityTreasureHunt(principal.getId(),
                                    reqAttendActivityTreasureHunt.getActivityId(), reqAttendActivityTreasureHunt.getActivityPurchaseNum());
                            //
                        } catch (BusinessException e) {
                            log.error("attendActivityTreasureHunt参与活动异常:{}", e.getLocalizedMessage());
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
                    log.error("attendActivityTreasureHunt参与活动异常:{}", e.getLocalizedMessage());
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
}
