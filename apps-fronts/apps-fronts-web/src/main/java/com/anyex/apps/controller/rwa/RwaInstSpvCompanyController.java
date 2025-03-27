/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaCertInstSpvPromoter;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvCompany;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvCompanyPagination;
import com.anyex.apps.controller.rwa.resp.RespRwaInstSpvCompany;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaCertInstSpvPromoter;
import com.anyex.apps.rwa.entity.RwaInstSpvCompany;
import com.anyex.apps.rwa.service.RwaCertInstSpvPromoterService;
import com.anyex.apps.rwa.service.RwaInstSpvCompanyService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * RWA机构SPV公司 控制器
 * <p>File：RwaInstSpvCompanyController.java </p>
 * <p>Title: RwaInstSpvCompanyController </p>
 * <p>Description:RwaInstSpvCompanyController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvCompany")
@Api(tags = "RWA机构SPV公司")
public class RwaInstSpvCompanyController extends GenericController {
    @Autowired(required = false)
    private RwaInstSpvCompanyService rwaInstSpvCompanyService;

    @Autowired(required = false)
    private RwaCertInstSpvPromoterService rwaCertInstSpvPromoterService;

    @GetMapping(value = "/getRwaInstSpvCompany")
    @ApiOperation(value = "获取RWA机构SPV公司", httpMethod = "GET")
    public JsonMessage<RwaInstSpvCompany> getRwaInstSpvCompany(Long id) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvCompanyService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/submitRwaInstSpvCompany")
    @ApiOperation(value = "提交RWA机构SPV公司", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvCompany(@Validated @RequestBody ReqRwaInstSpvCompany reqRwaInstSpvCompany) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (beanValidator(json, reqRwaInstSpvCompany)) {
            RwaInstSpvCompany rwaInstSpvCompany = new RwaInstSpvCompany();
            BeanUtils.copyProperties(reqRwaInstSpvCompany, rwaInstSpvCompany);
            //
            if (null == reqRwaInstSpvCompany.getId()) {
                rwaInstSpvCompany.setUserId(principal.getId());
                rwaInstSpvCompany.setCreateTime(System.currentTimeMillis());
            }
            rwaInstSpvCompany.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvCompany.setState("0");
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter = new RwaCertInstSpvPromoter();
            rwaCertInstSpvPromoter.setUserId(principal.getId());
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter1 = rwaCertInstSpvPromoterService.selectOne(rwaCertInstSpvPromoter);
            rwaInstSpvCompany.setInstSpvPromoterId(rwaCertInstSpvPromoter1.getId());
            //
            log.info("entity:{}", rwaInstSpvCompany);
            if (null == rwaInstSpvCompany.getId()) {
                rwaInstSpvCompanyService.insert(rwaInstSpvCompany);
            } else {
                rwaInstSpvCompanyService.updateByPrimaryKeySelective(rwaInstSpvCompany);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA机构SPV公司列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvCompany>> data(@Validated @RequestBody ReqRwaInstSpvCompanyPagination pagin) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvCompany rwaInstSpvCompany = new RwaInstSpvCompany();
        BeanUtils.copyProperties(pagin, rwaInstSpvCompany);
        rwaInstSpvCompany.setUserId(principal.getId());
        PaginateResult<RwaInstSpvCompany> result = rwaInstSpvCompanyService.search(pagin, rwaInstSpvCompany);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/getRwaInstSpvCompanyList")
    @ApiOperation(value = "查询账户下所有RWA机构SPV公司", httpMethod = "GET")
    public JsonMessage<List<RespRwaInstSpvCompany>> getRwaInstSpvCompanyList() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvCompany rwaInstSpvCompany = new RwaInstSpvCompany();
        rwaInstSpvCompany.setUserId(principal.getId());
        List<RwaInstSpvCompany> rwaInstSpvCompanyList = rwaInstSpvCompanyService.findList(rwaInstSpvCompany);

        List<RespRwaInstSpvCompany> respRwaInstSpvCompanyList = new ArrayList<>();
        for (RwaInstSpvCompany rwaInstSpvCompany1 : rwaInstSpvCompanyList) {
            RespRwaInstSpvCompany respRwaInstSpvCompany = new RespRwaInstSpvCompany();
            respRwaInstSpvCompany.setId(rwaInstSpvCompany1.getId());
            respRwaInstSpvCompany.setSpvCompanyName(rwaInstSpvCompany1.getSpvCompanyName());
            respRwaInstSpvCompanyList.add(respRwaInstSpvCompany);
        }
        return getJsonMessage(CommonEnums.SUCCESS, respRwaInstSpvCompanyList);
    }
}
