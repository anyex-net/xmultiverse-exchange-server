/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProduct;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductDividend;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductDividendPagination;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPagination;
import com.anyex.apps.controller.rwa.resp.RespRwaInstSpvProduct;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.*;
import com.anyex.apps.rwa.service.*;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hpsf.Decimal;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * RWA机构SPV产品 控制器
 * <p>File：RwaInstSpvProductController.java </p>
 * <p>Title: RwaInstSpvProductController </p>
 * <p>Description:RwaInstSpvProductController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProduct")
@Api(tags = "RWA机构SPV产品")
public class RwaInstSpvProductController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductService rwaInstSpvProductService;

    @Autowired(required = false)
    private RwaCertInstSpvPromoterService rwaCertInstSpvPromoterService;

    @Autowired(required = false)
    private RwaInstSpvCompanyService rwaInstSpvCompanyService;

    @Autowired(required = false)
    private RwaInstSpvProductDividendService rwaInstSpvProductDividendService;

    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @GetMapping(value = "/getRwaInstSpvProduct")
    @ApiOperation(value = "获取RWA机构SPV产品详情", httpMethod = "GET")
    public JsonMessage<RespRwaInstSpvProduct> getRwaInstSpvProduct(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        RespRwaInstSpvProduct respRwaInstSpvProduct = new RespRwaInstSpvProduct();
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(id);
        BeanUtils.copyProperties(rwaInstSpvProduct,respRwaInstSpvProduct);

        //企业信息
        RwaInstSpvCompany rwaInstSpvCompany = rwaInstSpvCompanyService.selectByPrimaryKey(rwaInstSpvProduct.getInstSpvCompanyId());
        BeanUtils.copyProperties(rwaInstSpvCompany,respRwaInstSpvProduct);
        return this.getJsonMessage(CommonEnums.SUCCESS, respRwaInstSpvProduct);
    }

    @PostMapping(value = "/submitRwaInstSpvProduct")
    @ApiOperation(value = "提交RWA机构SPV产品", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProduct(@Validated @RequestBody ReqRwaInstSpvProduct reqRwaInstSpvProduct) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqRwaInstSpvProduct))
        {
            RwaInstSpvProduct rwaInstSpvProduct = new RwaInstSpvProduct();
            BeanUtils.copyProperties(reqRwaInstSpvProduct, rwaInstSpvProduct);
            //
            if (null == reqRwaInstSpvProduct.getId())
            {
                rwaInstSpvProduct.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProduct.setUserId(principal.getId());
            }
            rwaInstSpvProduct.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProduct.setState("0");
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter = new RwaCertInstSpvPromoter();
            rwaCertInstSpvPromoter.setUserId(principal.getId());
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter1 = rwaCertInstSpvPromoterService.selectOne(rwaCertInstSpvPromoter);
            rwaInstSpvProduct.setInstSpvPromoterId(rwaCertInstSpvPromoter1.getId());
            rwaInstSpvProduct.setRaiseMarginState(0);
            //
            log.info("entity:{}", rwaInstSpvProduct);
            if(null == rwaInstSpvProduct.getId()){
                rwaInstSpvProductService.insert(rwaInstSpvProduct);
            } else {
                rwaInstSpvProductService.updateByPrimaryKeySelective(rwaInstSpvProduct);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA机构SPV产品列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProduct>> data(@Validated @RequestBody ReqRwaInstSpvProductPagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProduct rwaInstSpvProduct = new RwaInstSpvProduct();
        BeanUtils.copyProperties(pagin, rwaInstSpvProduct);
        rwaInstSpvProduct.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProduct> result = rwaInstSpvProductService.search(pagin,rwaInstSpvProduct);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/submitRaiseMargin")
    @ApiOperation(value = "提交保证金", httpMethod = "POST")
    public JsonMessage submitRaiseMargin(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(id);
        if (null == rwaInstSpvProduct)
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        //需转保证金,需调划转接口
        BigDecimal raiseMargin = rwaInstSpvProduct.getRaiseMarginRatio().multiply(rwaInstSpvProduct.getRaiseAmount());


        //
        rwaInstSpvProduct.setRaiseMarginState(1);
        rwaInstSpvProductService.updateByPrimaryKeySelective(rwaInstSpvProduct);
        return json;
    }

    @PostMapping(value = "/RwaInstSpvProductDividendData")
    @ApiOperation(value = "查询RWA机构SPV产品分红管理列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductDividend>> RwaInstSpvProductDividendData(@Validated @RequestBody ReqRwaInstSpvProductDividendPagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProductDividend rwaInstSpvProductDividend = new RwaInstSpvProductDividend();
        BeanUtils.copyProperties(pagin, rwaInstSpvProductDividend);
        rwaInstSpvProductDividend.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProductDividend> result = rwaInstSpvProductDividendService.search(pagin,rwaInstSpvProductDividend);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/submitRwaInstSpvProductDividend")
    @ApiOperation(value = "执行分红单", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProductDividend(@Validated @RequestBody ReqRwaInstSpvProductDividend reqrwaInstSpvProductDividend) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqrwaInstSpvProductDividend)) {
            RwaInstSpvProductDividend rwaInstSpvProductDividend = new RwaInstSpvProductDividend();
            BeanUtils.copyProperties(reqrwaInstSpvProductDividend, rwaInstSpvProductDividend);
            //
            if (null == rwaInstSpvProductDividend.getId()) {
                rwaInstSpvProductDividend.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProductDividend.setUserId(principal.getId());
            }
//            rwaInstSpvProductDividend.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductDividend.setState("pending");
            RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
            rwaCertInstInvestor.setUserId(principal.getId());
            RwaCertInstInvestor rwaCertInstInvestor1 = rwaCertInstInvestorService.selectOne(rwaCertInstInvestor);
            rwaInstSpvProductDividend.setInstInvestorId(rwaCertInstInvestor1.getId());
            //
            log.info("entity:{}", rwaInstSpvProductDividend);
            if (null == rwaInstSpvProductDividend.getId()) {
                rwaInstSpvProductDividendService.insert(rwaInstSpvProductDividend);
            } else {
                rwaInstSpvProductDividendService.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
            }
        }
        return json;
    }
}
