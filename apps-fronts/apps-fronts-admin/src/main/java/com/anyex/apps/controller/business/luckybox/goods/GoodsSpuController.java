/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSpu;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSpuPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.service.GoodsSpuService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.goods.entity.GoodsSpu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 产品SPU表 控制器
 * <p>File：GoodsSpuController.java </p>
 * <p>Title: GoodsSpuController </p>
 * <p>Description:GoodsSpuController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/goods/goodsSpu")
@Api(tags = "产品SPU")
public class GoodsSpuController extends GenericController
{
    @Autowired(required = false)
    private GoodsSpuService goodsSpuService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("goods:goodsSpu:data")
    @ApiOperation(value = "根据ID取产品SPU", httpMethod = "GET")
    public JsonMessage<GoodsSpu> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsSpuService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("goods:goodsSpu:operator")
    @ApiOperation(value = "保存产品SPU", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGoodsSpu info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GoodsSpu goodsSpu = new GoodsSpu();
            BeanUtils.copyProperties(info, goodsSpu);
            goodsSpu.setCreateTime(System.currentTimeMillis());
            goodsSpu.setUpdateTime(System.currentTimeMillis());
            //
            log.info("goodsSpu:{}", goodsSpu);
            if(null == goodsSpu.getId()){
                goodsSpuService.insert(goodsSpu);
            } else {
                goodsSpuService.updateByPrimaryKey(goodsSpu);
            }
        }
        return json;
    }

    @GetMapping(value = "/spuList")
    @RequiresPermissions("goods:goodsSpu:data")
    @ApiOperation(value = "查询产品SPU所有数据", httpMethod = "GET")
    public JsonMessage<List<GoodsSpu>> spuList() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, goodsSpuService.selectAll());
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("goods:goodsSpu:data")
    @ApiOperation(value = "查询产品SPU", httpMethod = "POST")
    public JsonMessage<PaginateResult<GoodsSpu>> data(@Validated @ModelAttribute ReqGoodsSpuPagination reqGoodsSpuPagination) throws BusinessException
    {
        //
        GoodsSpu goodsSpu = new GoodsSpu();
        BeanUtils.copyProperties(reqGoodsSpuPagination, goodsSpu);
        //
        PaginateResult<GoodsSpu> result = goodsSpuService.search(reqGoodsSpuPagination, goodsSpu);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("goods:goodsSpu:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        goodsSpuService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
