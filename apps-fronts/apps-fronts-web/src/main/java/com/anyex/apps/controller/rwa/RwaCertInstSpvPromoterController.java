/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaCertInstSpvPromoter;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.rwa.entity.RwaCertInstSpvPromoter;
import com.anyex.apps.rwa.service.RwaCertInstSpvPromoterService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getRwaCertInstSpvPromoter")
    @ApiOperation(value = "获取RWA认证机构SPV发起人", httpMethod = "GET")
    public JsonMessage<RwaCertInstSpvPromoter> getRwaCertInstSpvPromoter() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        RwaCertInstSpvPromoter rwaCertInstSpvPromoter = new RwaCertInstSpvPromoter();
        rwaCertInstSpvPromoter.setUserId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaCertInstSpvPromoterService.selectOne(rwaCertInstSpvPromoter));
    }

    @PostMapping(value = "/submitRwaCertInstSpvPromoter")
    @ApiOperation(value = "提交RWA认证机构SPV发起人", httpMethod = "POST")
    public JsonMessage submitRwaCertInstSpvPromoter(@Validated @RequestBody ReqRwaCertInstSpvPromoter reqRwaCertInstSpvPromoter) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User user = userService.selectByPrimaryKey(principal.getId());
        if (user.getCertState() > 0 ) throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_ALREADY_CERT);
         //
        if (beanValidator(json, reqRwaCertInstSpvPromoter))
        {
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter = new RwaCertInstSpvPromoter();
            BeanUtils.copyProperties(reqRwaCertInstSpvPromoter, rwaCertInstSpvPromoter);
            //
            if (null == reqRwaCertInstSpvPromoter.getId())
            {
                rwaCertInstSpvPromoter.setUserId(principal.getId());
                rwaCertInstSpvPromoter.setCreateTime(System.currentTimeMillis());
            }
            rwaCertInstSpvPromoter.setUpdateTime(System.currentTimeMillis());
            rwaCertInstSpvPromoter.setState("0");
            //
            log.info("entity:{}", rwaCertInstSpvPromoter);
            if(null == rwaCertInstSpvPromoter.getId()){
                rwaCertInstSpvPromoterService.insert(rwaCertInstSpvPromoter);
            } else {
                rwaCertInstSpvPromoterService.updateByPrimaryKeySelective(rwaCertInstSpvPromoter);
            }
        }
        return json;
    }
}
