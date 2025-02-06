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

import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.service.SnsPostService;

import com.anyex.apps.controller.social.req.ReqSnsPost;
import com.anyex.apps.controller.social.req.ReqSnsPostPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交帖子 控制器
 * <p>File：SnsPostController.java </p>
 * <p>Title: SnsPostController </p>
 * <p>Description:SnsPostController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsPost")
@Api(tags = "社交帖子")
public class SnsPostController extends GenericController
{
    @Autowired(required = false)
    private SnsPostService snsPostService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsPost:data")
    @ApiOperation(value = "根据ID取社交帖子", httpMethod = "GET")
    public JsonMessage<SnsPost> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsPostService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsPost:operator")
    @ApiOperation(value = "保存社交帖子", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsPost info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsPost entity = new SnsPost();
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
                snsPostService.insert(entity);
            } else {
                snsPostService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsPost:data")
    @ApiOperation(value = "查询社交帖子", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsPost>> data(@ModelAttribute ReqSnsPostPagination pagin) throws BusinessException
    {
        SnsPost entity = new SnsPost();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsPost> result = snsPostService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsPost:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsPostService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
