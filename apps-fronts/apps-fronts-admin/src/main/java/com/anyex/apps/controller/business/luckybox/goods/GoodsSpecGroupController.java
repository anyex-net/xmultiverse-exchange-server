/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSpecGroup;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsSpecGroupPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.service.GoodsSpecGroupService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.goods.entity.GoodsSpecGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 商品品类表 控制器
 * <p>File：GoodsSpecGroupController.java </p>
 * <p>Title: GoodsSpecGroupController </p>
 * <p>Description:GoodsSpecGroupController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/goods/goodsSpecGroup")
@Api(tags = "商品品类")
public class GoodsSpecGroupController extends GenericController
{
    @Autowired(required = false)
    private GoodsSpecGroupService goodsSpecGroupService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("goods:goodsSpecGroup:data")
    @ApiOperation(value = "根据ID取商品品类", httpMethod = "GET")
    public JsonMessage<GoodsSpecGroup> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsSpecGroupService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("goods:goodsSpecGroup:operator")
    @ApiOperation(value = "保存或更新商品品类", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGoodsSpecGroup info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GoodsSpecGroup goodsSpecGroup = new GoodsSpecGroup();
            BeanUtils.copyProperties(info, goodsSpecGroup);
            goodsSpecGroup.setCreateTime(System.currentTimeMillis());
            goodsSpecGroup.setUpdateTime(System.currentTimeMillis());
            //
            log.info("goodsSpecGroup:{}", goodsSpecGroup);
            if(null == goodsSpecGroup.getId()){
                goodsSpecGroupService.insert(goodsSpecGroup);
            } else {
                goodsSpecGroupService.updateByPrimaryKey(goodsSpecGroup);
            }
        }
        return json;
    }

    @GetMapping(value = "/specGroupList")
    @RequiresPermissions("goods:goodsSpecGroup:data")
    @ApiOperation(value = "查询商品品类所有数据", httpMethod = "GET")
    public JsonMessage<List<GoodsSpecGroup>> specGroupList() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, goodsSpecGroupService.selectAll());
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("goods:goodsSpecGroup:data")
    @ApiOperation(value = "查询商品品类", httpMethod = "POST")
    public JsonMessage<PaginateResult<GoodsSpecGroup>> data(@Validated @ModelAttribute ReqGoodsSpecGroupPagination reqGoodsSpecGroupPagination) throws BusinessException
    {
        //
        GoodsSpecGroup goodsSpecGroup = new GoodsSpecGroup();
        BeanUtils.copyProperties(reqGoodsSpecGroupPagination, goodsSpecGroup);
        //
        PaginateResult<GoodsSpecGroup> result = goodsSpecGroupService.search(reqGoodsSpecGroupPagination, goodsSpecGroup);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("goods:goodsSpecGroup:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        goodsSpecGroupService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
