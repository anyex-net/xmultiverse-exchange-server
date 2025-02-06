/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;


import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.controller.openim.req.ReqAccountFavorite;
import com.anyex.apps.controller.openim.req.ReqAccountFavoritePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.openim.entity.AccountFavorite;
import com.anyex.apps.openim.service.AccountFavoriteService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;


@Slf4j
@RestController
@RequestMapping(GlobalConst.IM+"/user/favorite")
@Api(tags = "用户收藏")
public class OpenImFavoriteController extends GenericController
{

    @Autowired(required = false)
    private AccountFavoriteService accountFavoriteService;

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存账户收藏", httpMethod = "POST")
    public JsonMessage save(@Validated @RequestBody ReqAccountFavorite info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountFavorite entity = new AccountFavorite();
            BeanUtils.copyProperties(info, entity);
            entity.setAccountId(principal.getId());
            if(info.getBizId()!=null)
            {
                AccountFavorite search = new AccountFavorite();
                search.setAccountId(principal.getId());
                search.setBizId(info.getBizId());
                AccountFavorite favorite = accountFavoriteService.selectOne(search);
                if(favorite != null)
                {
                    info.setId(favorite.getId());
                    entity.setId(favorite.getId());
                    entity.setCreateTime(System.currentTimeMillis());
                }
            }

            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                accountFavoriteService.insert(entity);
            } else {
                accountFavoriteService.updateByPrimaryKeySelective(entity);
            }
        }
        return json;
    }

    public static byte[] compress(String input) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             GZIPOutputStream gos = new GZIPOutputStream(baos)) {
            gos.write(input.getBytes("UTF-8"));
            gos.finish();
            return baos.toByteArray();
        }
    }

    @GetMapping(value = "/data")
    @ApiOperation(value = "查询账户收藏", httpMethod = "GET")
    public JsonMessage<PaginateResult<AccountFavorite>> data(@ModelAttribute ReqAccountFavoritePagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }

        AccountFavorite entity = new AccountFavorite();
        BeanUtils.copyProperties(pagin, entity);
        entity.setAccountId(principal.getId());
        PaginateResult<AccountFavorite> result = accountFavoriteService.search(pagin,entity);
        log.info("result:{}", result);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除账户收藏", httpMethod = "POST")
    public JsonMessage delete(@Validated @RequestBody ReqIdParam info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        accountFavoriteService.remove(info.getId());
        return json;
    }


}
