/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity;

import com.anyex.apps.business.luckybox.activity.service.ActivityTreasureHuntService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityTreasureHunt;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityTreasureHuntPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.activity.entity.ActivityTreasureHunt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 活动一元夺宝表 控制器
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
    private ActivityTreasureHuntService activityTreasureHuntService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("activity:activityTreasureHunt:data")
    @ApiOperation(value = "根据ID取活动一元夺宝", httpMethod = "GET")
    public JsonMessage<ActivityTreasureHunt> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, activityTreasureHuntService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("activity:activityTreasureHunt:operator")
    @ApiOperation(value = "保存或更新活动一元夺宝", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqActivityTreasureHunt info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            ActivityTreasureHunt activityTreasureHunt = new ActivityTreasureHunt();
            BeanUtils.copyProperties(info, activityTreasureHunt);
            //
            if (null == info.getId())
            {
                activityTreasureHunt.setCreateTime(System.currentTimeMillis());
            }
            activityTreasureHunt.setUpdateTime(System.currentTimeMillis());
            //
            log.info("activityTreasureHunt:{}", activityTreasureHunt);
            if(null == activityTreasureHunt.getId()){
                activityTreasureHuntService.insert(activityTreasureHunt);
            } else {
                activityTreasureHuntService.updateByPrimaryKey(activityTreasureHunt);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("activity:activityTreasureHunt:data")
    @ApiOperation(value = "查询活动一元夺宝分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ActivityTreasureHunt>> data(@Validated @ModelAttribute ReqActivityTreasureHuntPagination reqActivityTreasureHuntPagination) throws BusinessException
    {
        //
        ActivityTreasureHunt activityTreasureHunt = new ActivityTreasureHunt();
        BeanUtils.copyProperties(reqActivityTreasureHuntPagination, activityTreasureHunt);
        //
        PaginateResult<ActivityTreasureHunt> result = activityTreasureHuntService.search(reqActivityTreasureHuntPagination, activityTreasureHunt);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("activity:activityTreasureHunt:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        activityTreasureHuntService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/enable")
    @RequiresPermissions("activity:activityTreasureHunt:operator")
    @ApiOperation(value = "根据指定ID一元夺宝活动启用", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "一元夺宝活动对应Id", paramType = "form", required = true)
    public JsonMessage enable(@RequestParam("id") Long id) throws BusinessException
    {
        ActivityTreasureHunt activityTreasureHunt = activityTreasureHuntService.selectByPrimaryKey(id);
        if(null == activityTreasureHunt){
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        activityTreasureHunt.setStatus(true);
        log.info("enable activityTreasureHunt:{}", activityTreasureHunt);
        activityTreasureHuntService.updateByPrimaryKeySelective(activityTreasureHunt);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/disable")
    @RequiresPermissions("activity:activityTreasureHunt:operator")
    @ApiOperation(value = "根据指定ID一元夺宝活动停用", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "一元夺宝活动对应Id", paramType = "form", required = true)
    public JsonMessage disable(@RequestParam("id") Long id) throws BusinessException
    {
        ActivityTreasureHunt activityTreasureHunt = activityTreasureHuntService.selectByPrimaryKey(id);
        if(null == activityTreasureHunt){
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        activityTreasureHunt.setStatus(false);
        log.info("disable activityTreasureHunt:{}", activityTreasureHunt);
        activityTreasureHuntService.updateByPrimaryKeySelective(activityTreasureHunt);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
