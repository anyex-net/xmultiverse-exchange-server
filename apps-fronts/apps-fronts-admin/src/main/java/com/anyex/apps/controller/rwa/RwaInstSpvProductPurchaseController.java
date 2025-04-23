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

import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.anyex.apps.rwa.service.RwaInstSpvProductPurchaseService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPurchase;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPurchasePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA机构SPV产品申购记录 控制器
 * <p>File：RwaInstSpvProductPurchaseController.java </p>
 * <p>Title: RwaInstSpvProductPurchaseController </p>
 * <p>Description:RwaInstSpvProductPurchaseController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProductPurchase")
@Api(tags = "RWA机构SPV产品申购记录")
public class RwaInstSpvProductPurchaseController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductPurchaseService rwaInstSpvProductPurchaseService;

    @Autowired(required = false)
    private RwaBalancesService balancesService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvProductPurchase:data")
    @ApiOperation(value = "根据ID取RWA机构SPV产品申购记录", httpMethod = "GET")
    public JsonMessage<RwaInstSpvProductPurchase> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductPurchaseService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvProductPurchase:data")
    @ApiOperation(value = "查询RWA机构SPV产品申购记录", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductPurchase>> data(@ModelAttribute ReqRwaInstSpvProductPurchasePagination pagin) throws BusinessException
    {
        RwaInstSpvProductPurchase entity = new RwaInstSpvProductPurchase();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductPurchase> result = rwaInstSpvProductPurchaseService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/check")
    @RequiresPermissions("rwa:rwaInstSpvProductPurchase:operator")
    @ApiOperation(value = "审核", httpMethod = "POST")
    public JsonMessage check(Long id, String state) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        RwaInstSpvProductPurchase entity = rwaInstSpvProductPurchaseService.selectByPrimaryKey(id);
        entity.setState(state);
        if ("failed".equals(state)){
            entity.setRemark("审核失败");
            balancesService.purchaseFrozenBalUncheck(entity);
        }
        if ("success".equals(state)){
            balancesService.purchaseFrozenBalCheckAfter(entity);
        }
        if (principal != null) {
            entity.setUpdateBy(principal.getUserName());
        }
        entity.setUpdateTime(System.currentTimeMillis());
        rwaInstSpvProductPurchaseService.updateByPrimaryKeySelective(entity);
        return json;
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaInstSpvProductPurchase:operator")
//    @ApiOperation(value = "保存RWA机构SPV产品申购记录", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaInstSpvProductPurchase info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaInstSpvProductPurchase entity = new RwaInstSpvProductPurchase();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//                entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                rwaInstSpvProductPurchaseService.insert(entity);
//            } else {
//                rwaInstSpvProductPurchaseService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaInstSpvProductPurchase:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaInstSpvProductPurchaseService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
