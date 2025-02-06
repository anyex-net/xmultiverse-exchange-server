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

import com.anyex.apps.social.entity.SnsPostShare;
import com.anyex.apps.social.service.SnsPostShareService;

import com.anyex.apps.controller.social.req.ReqSnsPostShare;
import com.anyex.apps.controller.social.req.ReqSnsPostSharePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交帖子分享 控制器
 * <p>File：SnsPostShareController.java </p>
 * <p>Title: SnsPostShareController </p>
 * <p>Description:SnsPostShareController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsPostShare")
@Api(tags = "社交帖子分享")
public class SnsPostShareController extends GenericController
{
    @Autowired(required = false)
    private SnsPostShareService snsPostShareService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsPostShare:data")
    @ApiOperation(value = "根据ID取社交帖子分享", httpMethod = "GET")
    public JsonMessage<SnsPostShare> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsPostShareService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsPostShare:operator")
    @ApiOperation(value = "保存社交帖子分享", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsPostShare info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsPostShare entity = new SnsPostShare();
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
                snsPostShareService.insert(entity);
            } else {
                snsPostShareService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsPostShare:data")
    @ApiOperation(value = "查询社交帖子分享", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsPostShare>> data(@ModelAttribute ReqSnsPostSharePagination pagin) throws BusinessException
    {
        SnsPostShare entity = new SnsPostShare();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsPostShare> result = snsPostShareService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsPostShare:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsPostShareService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
