/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.activity.req.ReqActivityOperRecordPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.activity.entity.ActivityOperRecord;
import com.anyex.apps.business.luckybox.activity.service.ActivityOperRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 活动操作记录表 控制器
 * <p>File：ActivityOperRecordController.java </p>
 * <p>Title: ActivityOperRecordController </p>
 * <p>Description:ActivityOperRecordController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/business/luckybox/activity/activityOperRecord")
@Api(tags = "活动操作记录")
public class ActivityOperRecordController extends GenericController
{
    @Autowired(required = false)
    private ActivityOperRecordService activityOperRecordService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("activity:activityOperRecord:data")
    @ApiOperation(value = "根据ID取活动操作记录", httpMethod = "GET")
    public JsonMessage<ActivityOperRecord> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, activityOperRecordService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("activity:activityOperRecord:data")
    @ApiOperation(value = "查询活动操作记录", httpMethod = "POST")
    public JsonMessage<PaginateResult<ActivityOperRecord>> data(@ModelAttribute ReqActivityOperRecordPagination pagin) throws BusinessException
    {
        ActivityOperRecord entity = new ActivityOperRecord();
        BeanUtils.copyProperties(pagin,entity);
        //
        PaginateResult<ActivityOperRecord> result = activityOperRecordService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
