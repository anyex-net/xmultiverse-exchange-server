/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.social.entity.SnsFollow;
import com.anyex.apps.social.service.SnsFollowService;

import com.anyex.apps.controller.social.req.ReqSnsFollow;
import com.anyex.apps.controller.social.req.ReqSnsFollowPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交关注(我关注的) 控制器
 * <p>File：SnsFollowController.java </p>
 * <p>Title: SnsFollowController </p>
 * <p>Description:SnsFollowController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsFollow")
@Api(tags = "社交关注(我关注的)")
public class SnsFollowController extends GenericController
{
    @Autowired(required = false)
    private SnsFollowService snsFollowService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsFollow:data")
    @ApiOperation(value = "根据ID取社交关注(我关注的)", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsFollowService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsFollow:operator")
    @ApiOperation(value = "保存社交关注(我关注的)", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsFollow info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsFollow entity = new SnsFollow();
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
                snsFollowService.insert(entity);
            } else {
                snsFollowService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsFollow:data")
    @ApiOperation(value = "查询社交关注(我关注的)", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSnsFollowPagination pagin) throws BusinessException
    {
        SnsFollow entity = new SnsFollow();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsFollow> result = snsFollowService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsFollow:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsFollowService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
