/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.shop;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.shop.req.ReqShop;
import com.anyex.apps.controller.business.luckybox.shop.req.ReqShopPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.business.luckybox.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.shop.entity.Shop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺表 控制器
 * <p>File：ShopController.java </p>
 * <p>Title: ShopController </p>
 * <p>Description:ShopController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/shop/shop")
@Api(tags = "店铺管理")
public class ShopController extends GenericController
{
    @Autowired(required = false)
    private ShopService shopService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("shop:shop:data")
    @ApiOperation(value = "根据ID取店铺", httpMethod = "GET")
    public JsonMessage<Shop> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, shopService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("shop:shop:operator")
    @ApiOperation(value = "保存或更新店铺", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqShop info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            Shop shop = new Shop();
            BeanUtils.copyProperties(info, shop);
            //
            log.info("shop:{}", shop);
            if(null == shop.getId()){
                shopService.insert(shop);
            } else {
                shopService.updateByPrimaryKey(shop);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("shop:shop:data")
    @ApiOperation(value = "查询店铺分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Shop>> data(@Validated @ModelAttribute ReqShopPagination reqShopPagination) throws BusinessException
    {
        //
        Shop shop = new Shop();
        BeanUtils.copyProperties(reqShopPagination, shop);
        //
        PaginateResult<Shop> result = shopService.search(reqShopPagination, shop);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("shop:shop:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required= true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        shopService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
