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

import com.anyex.apps.rwa.entity.RwaInstSpvProductRedemption;
import com.anyex.apps.rwa.service.RwaInstSpvProductRedemptionService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductRedemption;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductRedemptionPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA机构SPV产品赎回记录 控制器
 * <p>File：RwaInstSpvProductRedemptionController.java </p>
 * <p>Title: RwaInstSpvProductRedemptionController </p>
 * <p>Description:RwaInstSpvProductRedemptionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProductRedemption")
@Api(tags = "RWA机构SPV产品赎回记录")
public class RwaInstSpvProductRedemptionController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductRedemptionService rwaInstSpvProductRedemptionService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvProductRedemption:data")
    @ApiOperation(value = "根据ID取RWA机构SPV产品赎回记录", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductRedemptionService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("rwa:rwaInstSpvProductRedemption:operator")
    @ApiOperation(value = "保存RWA机构SPV产品赎回记录", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqRwaInstSpvProductRedemption info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            RwaInstSpvProductRedemption entity = new RwaInstSpvProductRedemption();
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
                rwaInstSpvProductRedemptionService.insert(entity);
            } else {
                rwaInstSpvProductRedemptionService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvProductRedemption:data")
    @ApiOperation(value = "查询RWA机构SPV产品赎回记录", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRwaInstSpvProductRedemptionPagination pagin) throws BusinessException
    {
        RwaInstSpvProductRedemption entity = new RwaInstSpvProductRedemption();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductRedemption> result = rwaInstSpvProductRedemptionService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("rwa:rwaInstSpvProductRedemption:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        rwaInstSpvProductRedemptionService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
