/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsCategory;
import com.anyex.apps.controller.business.luckybox.goods.req.ReqGoodsCategoryPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.entity.GoodsBrand;
import com.anyex.apps.business.luckybox.goods.service.GoodsBrandService;
import com.anyex.apps.business.luckybox.goods.service.GoodsCategoryBrandService;
import com.anyex.apps.business.luckybox.goods.service.GoodsCategoryService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.goods.entity.GoodsCategory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 商品分类表 控制器
 * <p>File：GoodsCategoryController.java </p>
 * <p>Title: GoodsCategoryController </p>
 * <p>Description:GoodsCategoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/goods/goodsCategory")
@Api(tags = "商品分类")
public class GoodsCategoryController extends GenericController
{
    @Autowired(required = false)
    private GoodsCategoryService goodsCategoryService;

    @Autowired(required = false)
    private GoodsCategoryBrandService goodsCategoryBrandService;

    @Autowired(required = false)
    private GoodsBrandService goodsBrandService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("goods:goodsCategory:data")
    @ApiOperation(value = "根据ID取商品分类", httpMethod = "GET")
    public JsonMessage<GoodsCategory> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, goodsCategoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("goods:goodsCategory:operator")
    @ApiOperation(value = "保存或更新商品分类", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGoodsCategory info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GoodsCategory goodsCategory = new GoodsCategory();
            BeanUtils.copyProperties(info, goodsCategory);
            goodsCategory.setCreateTime(System.currentTimeMillis());
            goodsCategory.setUpdateTime(System.currentTimeMillis());
            //
            log.info("goodsCategory:{}", goodsCategory);
            if(null == goodsCategory.getId()){
                goodsCategoryService.insert(goodsCategory);
            } else {
                goodsCategoryService.updateByPrimaryKey(goodsCategory);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("goods:goodsCategory:data")
    @ApiOperation(value = "查询商品分类分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<GoodsCategory>> data(@Validated @ModelAttribute ReqGoodsCategoryPagination reqGoodsCategoryPagination) throws BusinessException
    {
        //
        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtils.copyProperties(reqGoodsCategoryPagination, goodsCategory);
        //
        PaginateResult<GoodsCategory> result = goodsCategoryService.search(reqGoodsCategoryPagination, goodsCategory);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("goods:goodsCategory:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        goodsCategoryService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/tree")
    @RequiresPermissions("goods:goodsCategory:data")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public JsonMessage<List<GoodsCategory>> tree() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, goodsCategoryService.treeData());
    }

    @GetMapping(value = "/findByGoodsCategoryId")
    @RequiresPermissions("goods:goodsCategory:data")
    @ApiOperation(value = "获取商品分类与品牌关联信息", httpMethod = "GET")
    public JsonMessage<List<GoodsBrand>> findByGoodsCategoryId(@RequestParam("categoryId") Long categoryId) throws BusinessException
    {
        List<GoodsBrand> data = goodsBrandService.findByGoodsCategoryId(categoryId);
        return getJsonMessage(CommonEnums.SUCCESS, data);
    }

    @PostMapping(value = "/saveGoodsBrand")
    @RequiresPermissions("goods:goodsCategory:operator")
    @ApiOperation(value = "保存商品分类与品牌关联信息(brandIds逗号分隔)", httpMethod = "POST")
    public JsonMessage saveGoodsBrand(@RequestParam("id") Long id, @RequestParam("goodsBrandIds") String brandIds) throws BusinessException
    {
        if (null == id || StringUtils.isBlank(brandIds))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        goodsCategoryBrandService.saveGoodsCategoryBrand(id, brandIds);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
