/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSku;
import com.anyex.apps.business.luckybox.goods.service.GoodsSkuService;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品SKU 控制器
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
    @ApiOperation(value = "根据ID取商品SKU", httpMethod = "GET")
    @ApiImplicitParam(name = "skuId", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<GoodsSku> findBy(@RequestParam("skuId") Long skuId) throws BusinessException
    {
        if (null == skuId) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsSkuService.selectByPrimaryKey(skuId));
    }
}
