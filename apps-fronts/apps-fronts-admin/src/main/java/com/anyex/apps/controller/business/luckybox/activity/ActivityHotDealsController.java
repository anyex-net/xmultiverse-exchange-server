/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity;

import com.anyex.apps.business.luckybox.activity.service.ActivityHotDealsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityHotDeals;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityHotDealsPagination;
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

import com.anyex.apps.business.luckybox.activity.entity.ActivityHotDeals;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 活动半价购买表 控制器
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
    private ActivityHotDealsService activityHotDealsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("activity:activityHotDeals:data")
    @ApiOperation(value = "根据ID取活动半价购买", httpMethod = "GET")
    public JsonMessage<ActivityHotDeals> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, activityHotDealsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("activity:activityHotDeals:operator")
    @ApiOperation(value = "保存或更新活动半价购买", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqActivityHotDeals info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            ActivityHotDeals activityHotDeals = new ActivityHotDeals();
            BeanUtils.copyProperties(info, activityHotDeals);
            //
            if (null == info.getId())
            {
                activityHotDeals.setCreateTime(System.currentTimeMillis());
            }
            activityHotDeals.setUpdateTime(System.currentTimeMillis());
            //
            log.info("activityHotDeals:{}", activityHotDeals);
            if(null == activityHotDeals.getId()){
                activityHotDealsService.insert(activityHotDeals);
            } else {
                activityHotDealsService.updateByPrimaryKey(activityHotDeals);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("activity:activityHotDeals:data")
    @ApiOperation(value = "查询活动半价购买分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ActivityHotDeals>> data(@Validated @ModelAttribute ReqActivityHotDealsPagination reqActivityHotDealsPagination) throws BusinessException
    {
        //
        ActivityHotDeals activityHotDeals = new ActivityHotDeals();
        BeanUtils.copyProperties(reqActivityHotDealsPagination, activityHotDeals);
        //
        PaginateResult<ActivityHotDeals> result = activityHotDealsService.search(reqActivityHotDealsPagination, activityHotDeals);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("activity:activityHotDeals:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        activityHotDealsService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/enable")
    @RequiresPermissions("activity:activityTreasureHunt:operator")
    @ApiOperation(value = "根据指定ID半价购买活动启用", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "半价购买活动对应Id", paramType = "form", required = true)
    public JsonMessage enable(@RequestParam("id") Long id) throws BusinessException
    {
        ActivityHotDeals activityHotDeals = activityHotDealsService.selectByPrimaryKey(id);
        if(null == activityHotDeals){
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        activityHotDeals.setStatus(true);
        log.info("enable activityHotDeals:{}", activityHotDeals);
        activityHotDealsService.updateByPrimaryKeySelective(activityHotDeals);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/disable")
    @RequiresPermissions("activity:activityTreasureHunt:operator")
    @ApiOperation(value = "根据指定ID半价购买活动停用", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "半价购买活动对应Id", paramType = "form", required = true)
    public JsonMessage disable(@RequestParam("id") Long id) throws BusinessException
    {
        ActivityHotDeals activityHotDeals = activityHotDealsService.selectByPrimaryKey(id);
        if(null == activityHotDeals){
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        activityHotDeals.setStatus(false);
        log.info("disable activityHotDeals:{}", activityHotDeals);
        activityHotDealsService.updateByPrimaryKeySelective(activityHotDeals);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
