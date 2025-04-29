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
import com.anyex.apps.rwa.entity.RwaCertInstInvestor;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaCertInstSpvPromoter;
import com.anyex.apps.rwa.service.RwaCertInstSpvPromoterService;

import com.anyex.apps.controller.rwa.req.ReqRwaCertInstSpvPromoter;
import com.anyex.apps.controller.rwa.req.ReqRwaCertInstSpvPromoterPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA认证机构SPV发起人 控制器
 * <p>File：RwaCertInstSpvPromoterController.java </p>
 * <p>Title: RwaCertInstSpvPromoterController </p>
 * <p>Description:RwaCertInstSpvPromoterController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaCertInstSpvPromoter")
@Api(tags = "RWA认证机构SPV发起人")
public class RwaCertInstSpvPromoterController extends GenericController
{
    @Autowired(required = false)
    private RwaCertInstSpvPromoterService rwaCertInstSpvPromoterService;

    @Autowired(required = false)
    private UserService userService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaCertInstSpvPromoter:data")
    @ApiOperation(value = "根据ID取RWA认证机构SPV发起人", httpMethod = "GET")
    public JsonMessage<RwaCertInstSpvPromoter> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaCertInstSpvPromoterService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaCertInstSpvPromoter:data")
    @ApiOperation(value = "查询RWA认证机构SPV发起人", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaCertInstSpvPromoter>> data(@ModelAttribute ReqRwaCertInstSpvPromoterPagination pagin) throws BusinessException
    {
        RwaCertInstSpvPromoter entity = new RwaCertInstSpvPromoter();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaCertInstSpvPromoter> result = rwaCertInstSpvPromoterService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/check")
    @RequiresPermissions("rwa:rwaCertInstSpvPromoter:check")
    @ApiOperation(value = "复核", httpMethod = "POST")
    public JsonMessage check(Long id, String state) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        RwaCertInstSpvPromoter rwaCertInstSpvPromoter = rwaCertInstSpvPromoterService.selectByPrimaryKey(id);
        if (null == rwaCertInstSpvPromoter) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        rwaCertInstSpvPromoter.setState(state);
        if (principal != null) {
            rwaCertInstSpvPromoter.setCheckBy(principal.getUserName());
        }
        rwaCertInstSpvPromoter.setCheckTime(System.currentTimeMillis());
        rwaCertInstSpvPromoterService.updateByPrimaryKeySelective(rwaCertInstSpvPromoter);
        //
        User user = userService.selectByPrimaryKey(rwaCertInstSpvPromoter.getUserId());
        user.setCertState(3);
        userService.updateByPrimaryKeySelective(user);
        //
        return json;
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaCertInstSpvPromoter:operator")
//    @ApiOperation(value = "保存RWA认证机构SPV发起人", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaCertInstSpvPromoter info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaCertInstSpvPromoter entity = new RwaCertInstSpvPromoter();
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
//                rwaCertInstSpvPromoterService.insert(entity);
//            } else {
//                rwaCertInstSpvPromoterService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaCertInstSpvPromoter:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaCertInstSpvPromoterService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
