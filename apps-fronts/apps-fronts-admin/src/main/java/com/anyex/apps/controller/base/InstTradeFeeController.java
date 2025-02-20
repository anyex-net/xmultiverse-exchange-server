/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.base.entity.InstTradeFee;
import com.anyex.apps.base.service.InstTradeFeeService;

import com.anyex.apps.controller.base.req.ReqInstTradeFee;
import com.anyex.apps.controller.base.req.ReqInstTradeFeePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 平台交易手续费费率 控制器
 * <p>File：InstTradeFeeController.java </p>
 * <p>Title: InstTradeFeeController </p>
 * <p>Description:InstTradeFeeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/instTradeFee")
@Api(tags = "平台交易手续费费率")
public class InstTradeFeeController extends GenericController
{
    @Autowired(required = false)
    private InstTradeFeeService instTradeFeeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("base:instTradeFee:data")
    @ApiOperation(value = "根据ID取平台交易手续费费率", httpMethod = "GET")
    public JsonMessage<InstTradeFee> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, instTradeFeeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("base:instTradeFee:operator")
    @ApiOperation(value = "保存平台交易手续费费率", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqInstTradeFee info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            InstTradeFee entity = new InstTradeFee();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                instTradeFeeService.insert(entity);
            } else {
                entity.setUpdateBy(principal.getUserName());
                instTradeFeeService.updateByPrimaryKeySelective(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("base:instTradeFee:data")
    @ApiOperation(value = "查询平台交易手续费费率", httpMethod = "POST")
    public JsonMessage<PaginateResult<InstTradeFee>> data(@ModelAttribute ReqInstTradeFeePagination pagin) throws BusinessException
    {
        InstTradeFee entity = new InstTradeFee();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<InstTradeFee> result = instTradeFeeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("base:instTradeFee:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        instTradeFeeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
