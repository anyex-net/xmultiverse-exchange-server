/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProduct;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaCertInstSpvPromoter;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.service.RwaCertInstSpvPromoterService;
import com.anyex.apps.rwa.service.RwaInstSpvProductService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getRwaInstSpvProduct")
    @ApiOperation(value = "获取RWA机构SPV产品", httpMethod = "GET")
    public JsonMessage<RwaInstSpvProduct> getRwaInstSpvProduct(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductService.selectByPrimaryKey(id));
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
}
