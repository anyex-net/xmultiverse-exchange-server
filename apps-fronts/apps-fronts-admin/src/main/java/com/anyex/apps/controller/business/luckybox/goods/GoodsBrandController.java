/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsBrand;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsBrandPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.service.GoodsBrandService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.goods.entity.GoodsBrand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 商品品牌表 控制器
 * <p>File：GoodsBrandController.java </p>
 * <p>Title: GoodsBrandController </p>
 * <p>Description:GoodsBrandController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/goods/goodsBrand")
@Api(tags = "商品品牌")
public class GoodsBrandController extends GenericController
{
    @Autowired(required = false)
    private GoodsBrandService goodsBrandService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("goods:goodsBrand:data")
    @ApiOperation(value = "根据ID取商品品牌", httpMethod = "GET")
    public JsonMessage<GoodsBrand> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsBrandService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("goods:goodsBrand:operator")
    @ApiOperation(value = "保存或更新商品品牌", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGoodsBrand info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GoodsBrand goodsBrand = new GoodsBrand();
            BeanUtils.copyProperties(info, goodsBrand);
            goodsBrand.setCreateTime(System.currentTimeMillis());
            goodsBrand.setUpdateTime(System.currentTimeMillis());
            //
            log.info("goodsBrand:{}", goodsBrand);
            if(null == goodsBrand.getId()){
                goodsBrandService.insert(goodsBrand);
            } else {
                goodsBrandService.updateByPrimaryKey(goodsBrand);
            }
        }
        return json;
    }

    @GetMapping(value = "/brandList")
    @RequiresPermissions("goods:goodsBrand:data")
    @ApiOperation(value = "查询商品品牌所有数据", httpMethod = "GET")
    public JsonMessage<List<GoodsBrand>> brandList() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, goodsBrandService.selectAll());
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("goods:goodsBrand:data")
    @ApiOperation(value = "查询商品品牌分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<GoodsBrand>> data(@Validated @ModelAttribute ReqGoodsBrandPagination reqGoodsBrandPagination) throws BusinessException
    {
        //
        GoodsBrand goodsBrand = new GoodsBrand();
        BeanUtils.copyProperties(reqGoodsBrandPagination, goodsBrand);
        //
        PaginateResult<GoodsBrand> result = goodsBrandService.search(reqGoodsBrandPagination, goodsBrand);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("goods:goodsBrand:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        goodsBrandService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
