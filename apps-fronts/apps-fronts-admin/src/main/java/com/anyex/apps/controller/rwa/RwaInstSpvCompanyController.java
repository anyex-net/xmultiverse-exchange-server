/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaInstSpvCompany;
import com.anyex.apps.rwa.service.RwaInstSpvCompanyService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvCompany;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvCompanyPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA机构SPV公司 控制器
 * <p>File：RwaInstSpvCompanyController.java </p>
 * <p>Title: RwaInstSpvCompanyController </p>
 * <p>Description:RwaInstSpvCompanyController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvCompany")
@Api(tags = "RWA机构SPV公司")
public class RwaInstSpvCompanyController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvCompanyService rwaInstSpvCompanyService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvCompany:data")
    @ApiOperation(value = "根据ID取RWA机构SPV公司", httpMethod = "GET")
    public JsonMessage<RwaInstSpvCompany> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvCompanyService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvCompany:data")
    @ApiOperation(value = "查询RWA机构SPV公司", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvCompany>> data(@ModelAttribute ReqRwaInstSpvCompanyPagination pagin) throws BusinessException
    {
        RwaInstSpvCompany entity = new RwaInstSpvCompany();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvCompany> result = rwaInstSpvCompanyService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaInstSpvCompany:operator")
//    @ApiOperation(value = "保存RWA机构SPV公司", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaInstSpvCompany info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaInstSpvCompany entity = new RwaInstSpvCompany();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//                entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                rwaInstSpvCompanyService.insert(entity);
//            } else {
//                rwaInstSpvCompanyService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaInstSpvCompany:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaInstSpvCompanyService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
