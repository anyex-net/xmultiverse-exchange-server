/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.base.entity.UserInstrumentsFavorite;
import com.anyex.apps.base.service.UserInstrumentsFavoriteService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.base.req.ReqUserInstrumentsFavorite;
import com.anyex.apps.controller.base.req.ReqUserInstrumentsFavoritePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
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
 * 用户交易产品收藏 控制器
 * <p>File：UserInstrumentsFavoriteController.java </p>
 * <p>Title: UserInstrumentsFavoriteController </p>
 * <p>Description:UserInstrumentsFavoriteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/userInstrumentsFavorite")
@Api(tags = "用户交易产品收藏")
public class UserInstrumentsFavoriteController extends GenericController
{
    @Autowired(required = false)
    private UserInstrumentsFavoriteService userInstrumentsFavoriteService;

    @PostMapping(value = "/favorite")
    @ApiOperation(value = "用户交易产品收藏", httpMethod = "POST")
    public JsonMessage favorite(@Validated @RequestBody ReqUserInstrumentsFavorite reqUserInstrumentsFavorite) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserInstrumentsFavorite entity = new UserInstrumentsFavorite();
        BeanUtils.copyProperties(reqUserInstrumentsFavorite, entity);
        entity.setUserId(principal.getId());
        log.info("entity:{}", entity);
        userInstrumentsFavoriteService.insert(entity);
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/unfavorite")
    @ApiOperation(value = "用户交易产品取消收藏", httpMethod = "POST")
    public JsonMessage unfavorite(@Validated @RequestBody ReqUserInstrumentsFavorite reqUserInstrumentsFavorite) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserInstrumentsFavorite entity = new UserInstrumentsFavorite();
        BeanUtils.copyProperties(reqUserInstrumentsFavorite, entity);
        entity.setUserId(principal.getId());
        log.info("entity:{}", entity);
        UserInstrumentsFavorite entityDB = userInstrumentsFavoriteService.selectOne(entity);
        if(null != entityDB){
            userInstrumentsFavoriteService.remove(entityDB.getId());
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询用户交易产品收藏", httpMethod = "POST")
    public JsonMessage data(@Validated @RequestBody ReqUserInstrumentsFavoritePagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserInstrumentsFavorite entity = new UserInstrumentsFavorite();
        BeanUtils.copyProperties(pagin, entity);
        entity.setUserId(principal.getId());
        PaginateResult<UserInstrumentsFavorite> result = userInstrumentsFavoriteService.search(pagin, entity);
        //
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
