/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaInstSpvProductNotice;
import com.anyex.apps.rwa.service.RwaInstSpvProductNoticeService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductNotice;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductNoticePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA机构SPV产品公告 控制器
 * <p>File：RwaInstSpvProductNoticeController.java </p>
 * <p>Title: RwaInstSpvProductNoticeController </p>
 * <p>Description:RwaInstSpvProductNoticeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProductNotice")
@Api(description = "RWA机构SPV产品公告")
public class RwaInstSpvProductNoticeController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductNoticeService rwaInstSpvProductNoticeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvProductNotice:data")
    @ApiOperation(value = "根据ID取RWA机构SPV产品公告", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductNoticeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("rwa:rwaInstSpvProductNotice:operator")
    @ApiOperation(value = "保存RWA机构SPV产品公告", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqRwaInstSpvProductNotice info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            RwaInstSpvProductNotice entity = new RwaInstSpvProductNotice();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                rwaInstSpvProductNoticeService.insert(entity);
            } else {
                rwaInstSpvProductNoticeService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvProductNotice:data")
    @ApiOperation(value = "查询RWA机构SPV产品公告", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRwaInstSpvProductNoticePagination pagin) throws BusinessException
    {
        RwaInstSpvProductNotice entity = new RwaInstSpvProductNotice();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductNotice> result = rwaInstSpvProductNoticeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("rwa:rwaInstSpvProductNotice:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        rwaInstSpvProductNoticeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
