/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductNotice;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductNoticePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaCertInstInvestor;
import com.anyex.apps.rwa.entity.RwaInstSpvProductNotice;
import com.anyex.apps.rwa.service.RwaCertInstInvestorService;
import com.anyex.apps.rwa.service.RwaInstSpvProductNoticeService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RWA机构SPV产品公告 控制器
 * <p>File：RwaInstSpvProductNoticeController.java </p>
 * <p>Title: RwaInstSpvProductNoticeController </p>
 * <p>Description:RwaInstSpvProductNoticeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProductNotice")
@Api(tags = "RWA机构SPV产品公告")
public class RwaInstSpvProductNoticeController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductNoticeService rwaInstSpvProductNoticeService;

    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @GetMapping(value = "/getRwaInstSpvProductNotice")
    @ApiOperation(value = "获取RWA机构SPV产品公告", httpMethod = "GET")
    public JsonMessage<RwaInstSpvProductNotice> getRwaInstSpvProductNotice(String id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        if (null == id || id.trim().isEmpty()) {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        RwaInstSpvProductNotice rwaInstSpvProductNotice = rwaInstSpvProductNoticeService.selectByPrimaryKey(Long.valueOf(id));
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductNotice);
    }

    @PostMapping(value = "/submitRwaInstSpvProductNotice")
    @ApiOperation(value = "提交RWA机构SPV产品公告", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProductNotice(@Validated @RequestBody ReqRwaInstSpvProductNotice info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        if (beanValidator(json, info))
        {
            RwaInstSpvProductNotice entity = new RwaInstSpvProductNotice();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setUserId(principal.getId());
            RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
            rwaCertInstInvestor.setUserId(principal.getId());
            RwaCertInstInvestor rwaCertInstInvestor1 = rwaCertInstInvestorService.selectOne(rwaCertInstInvestor);
            entity.setInstInvestorId(rwaCertInstInvestor1.getId());
            entity.setState(0);
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                rwaInstSpvProductNoticeService.insert(entity);
            } else {
                rwaInstSpvProductNoticeService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA机构SPV产品公告", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductNotice>> data(@Validated @RequestBody ReqRwaInstSpvProductNoticePagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        RwaInstSpvProductNotice entity = new RwaInstSpvProductNotice();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductNotice> result = rwaInstSpvProductNoticeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/delRwaInstSpvProductNotice")
    @ApiOperation(value = "删除RWA机构SPV产品公告", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String id) throws BusinessException
    {
        rwaInstSpvProductNoticeService.remove(Long.valueOf(id));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
