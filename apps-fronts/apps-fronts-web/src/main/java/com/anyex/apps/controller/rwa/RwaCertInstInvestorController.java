/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaCertInstInvestor;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.rwa.entity.RwaCertInstInvestor;
import com.anyex.apps.rwa.service.RwaCertInstInvestorService;
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
 * RWA认证机构投资者 控制器
 * <p>File：RwaCertInstInvestorController.java </p>
 * <p>Title: RwaCertInstInvestorController </p>
 * <p>Description:RwaCertInstInvestorController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaCertInstInvestor")
@Api(tags = "RWA认证机构投资者")
public class RwaCertInstInvestorController extends GenericController
{
    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @GetMapping(value = "/getRwaCertInstInvestor")
    @ApiOperation(value = "获取RWA认证机构投资者", httpMethod = "GET")
    public JsonMessage<RwaCertInstInvestor> getRwaCertInstInvestor() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
        rwaCertInstInvestor.setUserId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaCertInstInvestorService.selectOne(rwaCertInstInvestor));
    }


    @PostMapping(value = "/submitRwaCertInstInvestor")
    @ApiOperation(value = "提交RWA认证机构投资者", httpMethod = "POST")
    public JsonMessage submitRwaCertInstInvestor(@Validated @RequestBody ReqRwaCertInstInvestor info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        if (beanValidator(json, info))
        {
            RwaCertInstInvestor entity = new RwaCertInstInvestor();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
                entity.setUserId(principal.getId());
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setState("0");
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                rwaCertInstInvestorService.insert(entity);
            } else {
                rwaCertInstInvestorService.updateByPrimaryKeySelective(entity);
            }
        }
        return json;
    }

}
