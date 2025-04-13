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

import com.anyex.apps.rwa.entity.RwaInstSpvProductDividendSnapshot;
import com.anyex.apps.rwa.service.RwaInstSpvProductDividendSnapshotService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductDividendSnapshot;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductDividendSnapshotPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA机构SPV产品投资者分红快照 控制器
 * <p>File：RwaInstSpvProductDividendSnapshotController.java </p>
 * <p>Title: RwaInstSpvProductDividendSnapshotController </p>
 * <p>Description:RwaInstSpvProductDividendSnapshotController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProductDividendSnapshot")
@Api(description = "RWA机构SPV产品投资者分红快照")
public class RwaInstSpvProductDividendSnapshotController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductDividendSnapshotService rwaInstSpvProductDividendSnapshotService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvProductDividendSnapshot:data")
    @ApiOperation(value = "根据ID取RWA机构SPV产品投资者分红快照", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductDividendSnapshotService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("rwa:rwaInstSpvProductDividendSnapshot:operator")
    @ApiOperation(value = "保存RWA机构SPV产品投资者分红快照", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqRwaInstSpvProductDividendSnapshot info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            RwaInstSpvProductDividendSnapshot entity = new RwaInstSpvProductDividendSnapshot();
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
                rwaInstSpvProductDividendSnapshotService.insert(entity);
            } else {
                rwaInstSpvProductDividendSnapshotService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvProductDividendSnapshot:data")
    @ApiOperation(value = "查询RWA机构SPV产品投资者分红快照", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRwaInstSpvProductDividendSnapshotPagination pagin) throws BusinessException
    {
        RwaInstSpvProductDividendSnapshot entity = new RwaInstSpvProductDividendSnapshot();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductDividendSnapshot> result = rwaInstSpvProductDividendSnapshotService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("rwa:rwaInstSpvProductDividendSnapshot:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        rwaInstSpvProductDividendSnapshotService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
