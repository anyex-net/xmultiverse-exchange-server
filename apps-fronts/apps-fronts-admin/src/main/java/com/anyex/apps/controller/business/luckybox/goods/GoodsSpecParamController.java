/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSpecParam;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSpecParamPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.service.GoodsSpecParamService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.goods.entity.GoodsSpecParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 商品品类参数表 控制器
 * <p>File：GoodsSpecParamController.java </p>
 * <p>Title: GoodsSpecParamController </p>
 * <p>Description:GoodsSpecParamController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/goods/goodsSpecParam")
@Api(tags = "商品品类参数")
public class GoodsSpecParamController extends GenericController
{
    @Autowired(required = false)
    private GoodsSpecParamService goodsSpecParamService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("goods:goodsSpecParam:data")
    @ApiOperation(value = "根据ID取商品品类参数", httpMethod = "GET")
    public JsonMessage<GoodsSpecParam> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsSpecParamService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("goods:goodsSpecParam:operator")
    @ApiOperation(value = "保存或更新商品品类参数", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGoodsSpecParam info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GoodsSpecParam goodsSpecParam = new GoodsSpecParam();
            BeanUtils.copyProperties(info, goodsSpecParam);
            //
            log.info("goodsSpecParam:{}", goodsSpecParam);
            if(null == goodsSpecParam.getId()){
                goodsSpecParamService.insert(goodsSpecParam);
            } else {
                goodsSpecParamService.updateByPrimaryKey(goodsSpecParam);
            }
        }
        return json;
    }

    @GetMapping(value = "/specParamList")
    @RequiresPermissions("goods:goodsSpecParam:data")
    @ApiOperation(value = "查询商品品类对应品类参数所有数据", httpMethod = "GET")
    public JsonMessage<List<GoodsSpecParam>> specParamList(@RequestParam("spgId") Long spgId) throws BusinessException
    {
        GoodsSpecParam goodsSpecParam = new GoodsSpecParam();
        goodsSpecParam.setSpgId(spgId);
        log.info("goodsSpecParam:{}", goodsSpecParam);
        List<GoodsSpecParam> listGoodsSpecParam =  goodsSpecParamService.findList(goodsSpecParam);
        return getJsonMessage(CommonEnums.SUCCESS, listGoodsSpecParam);
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("goods:goodsSpecParam:data")
    @ApiOperation(value = "查询商品品类参数", httpMethod = "POST")
    public JsonMessage<PaginateResult<GoodsSpecParam>> data(@Validated @ModelAttribute ReqGoodsSpecParamPagination reqGoodsSpecParamPagination) throws BusinessException
    {
        //
        GoodsSpecParam goodsSpecParam = new GoodsSpecParam();
        BeanUtils.copyProperties(reqGoodsSpecParamPagination, goodsSpecParam);
        //
        PaginateResult<GoodsSpecParam> result = goodsSpecParamService.search(reqGoodsSpecParamPagination, goodsSpecParam);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("goods:goodsSpecParam:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        goodsSpecParamService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
