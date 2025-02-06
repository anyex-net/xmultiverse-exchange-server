/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSku;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSkuPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.service.GoodsSkuService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.goods.entity.GoodsSku;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 商品SKU表 控制器
 * <p>File：GoodsSkuController.java </p>
 * <p>Title: GoodsSkuController </p>
 * <p>Description:GoodsSkuController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/goods/goodsSku")
@Api(tags = "商品SKU")
public class GoodsSkuController extends GenericController
{
    @Autowired(required = false)
    private GoodsSkuService goodsSkuService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("goods:goodsSku:data")
    @ApiOperation(value = "根据ID取商品SKU", httpMethod = "GET")
    public JsonMessage<GoodsSku> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsSkuService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("goods:goodsSku:operator")
    @ApiOperation(value = "保存或更新商品SKU", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGoodsSku info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GoodsSku goodsSku = new GoodsSku();
            BeanUtils.copyProperties(info, goodsSku);
            goodsSku.setCreateTime(System.currentTimeMillis());
            goodsSku.setUpdateTime(System.currentTimeMillis());
            //
            log.info("goodsSku:{}", goodsSku);
            if(null == goodsSku.getId()){
                goodsSkuService.insert(goodsSku);
            } else {
                goodsSkuService.updateByPrimaryKey(goodsSku);
            }
        }
        return json;
    }

    @GetMapping(value = "/skuList")
    @RequiresPermissions("goods:goodsSku:data")
    @ApiOperation(value = "查询产品SPU对应商品SKU所有数据", httpMethod = "GET")
    public JsonMessage<List<GoodsSku>> skuList(@RequestParam("spuId") Long spuId) throws BusinessException
    {
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSpuId(spuId);
        log.info("goodsSku:{}", goodsSku);
        List<GoodsSku> listGoodsSku =  goodsSkuService.findList(goodsSku);
        return getJsonMessage(CommonEnums.SUCCESS, listGoodsSku);
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("goods:goodsSku:data")
    @ApiOperation(value = "查询商品SKU", httpMethod = "POST")
    public JsonMessage<PaginateResult<GoodsSku>> data(@Validated @ModelAttribute ReqGoodsSkuPagination reqGoodsSkuPagination) throws BusinessException
    {
        //
        GoodsSku goodsSku = new GoodsSku();
        BeanUtils.copyProperties(reqGoodsSkuPagination, goodsSku);
        //
        PaginateResult<GoodsSku> result = goodsSkuService.search(reqGoodsSkuPagination, goodsSku);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("goods:goodsSku:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        goodsSkuService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/putOnSale")
    @RequiresPermissions("goods:goodsSku:operator")
    @ApiOperation(value = "根据指定ID商品SKU上架", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "SKU对应Id", paramType = "form", required = true)
    public JsonMessage putOnSale(@RequestParam("id") Long id) throws BusinessException
    {
        GoodsSku goodsSku = goodsSkuService.selectByPrimaryKey(id);
        if(null == goodsSku){
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        goodsSku.setSaleable(true);
        log.info("putOnSale goodsSku:{}", goodsSku);
        goodsSkuService.updateByPrimaryKeySelective(goodsSku);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/pullOffSale")
    @RequiresPermissions("goods:goodsSku:operator")
    @ApiOperation(value = "根据指定ID商品SKU下架", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "SKU对应Id", paramType = "form", required = true)
    public JsonMessage pullOffSale(@RequestParam("id") Long id) throws BusinessException
    {
        GoodsSku goodsSku = goodsSkuService.selectByPrimaryKey(id);
        if(null == goodsSku){
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        goodsSku.setSaleable(false);
        log.info("pullOffSale goodsSku:{}", goodsSku);
        goodsSkuService.updateByPrimaryKeySelective(goodsSku);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
