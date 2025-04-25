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
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.service.RwaBalancesService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaInstSpvProductAsset;
import com.anyex.apps.rwa.service.RwaInstSpvProductAssetService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductAsset;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductAssetPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * RWA机构SPV产品资产信息 控制器
 * <p>File：RwaInstSpvProductAssetController.java </p>
 * <p>Title: RwaInstSpvProductAssetController </p>
 * <p>Description:RwaInstSpvProductAssetController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProductAsset")
@Api(description = "RWA机构SPV产品资产信息")
public class RwaInstSpvProductAssetController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductAssetService rwaInstSpvProductAssetService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvProductAsset:data")
    @ApiOperation(value = "根据ID取RWA机构SPV产品资产信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductAssetService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaInstSpvProductAsset:operator")
//    @ApiOperation(value = "保存RWA机构SPV产品资产信息", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaInstSpvProductAsset info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaInstSpvProductAsset entity = new RwaInstSpvProductAsset();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//            entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                rwaInstSpvProductAssetService.insert(entity);
//            } else {
//                rwaInstSpvProductAssetService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvProductAsset:data")
    @ApiOperation(value = "查询RWA机构SPV产品资产信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRwaInstSpvProductAssetPagination pagin) throws BusinessException
    {
        RwaInstSpvProductAsset entity = new RwaInstSpvProductAsset();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductAsset> result = rwaInstSpvProductAssetService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/check")
    @RequiresPermissions("rwa:rwaInstSpvProductAsset:operator")
    @ApiOperation(value = "复核", httpMethod = "POST")
    public JsonMessage check(Long id, String state) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        RwaInstSpvProductAsset entity = rwaInstSpvProductAssetService.selectByPrimaryKey(id);
        entity.setState(Integer.valueOf(state));
        if (principal != null) {
            entity.setUpdateBy(principal.getUserName());
        }
        entity.setUpdateTime(System.currentTimeMillis());
        rwaInstSpvProductAssetService.updateByPrimaryKeySelective(entity);
        //审核通过后解冻冻结
        rwaBalancesService.unFrozenBal(entity);
        return json;
    }

//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaInstSpvProductAsset:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaInstSpvProductAssetService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
