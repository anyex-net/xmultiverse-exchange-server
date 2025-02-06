/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.asset.req.ReqWalletAssetPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.asset.entity.WalletAsset;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 钱包资产表 控制器
 * <p>File：WalletAssetController.java </p>
 * <p>Title: WalletAssetController </p>
 * <p>Description:WalletAssetController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/asset/walletAsset")
@Api(tags = "钱包资产")
public class WalletAssetController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    AccountService accountService;


    @GetMapping(value = "/findBy")
    @RequiresPermissions("asset:walletAsset:data")
    @ApiOperation(value = "根据ID取钱包资产", httpMethod = "GET")
    public JsonMessage<WalletAsset> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("asset:walletAsset:data")
    @ApiOperation(value = "查询钱包资产分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAsset>> data(@Validated @ModelAttribute ReqWalletAssetPagination reqWalletAssetPagination) throws BusinessException
    {
        //
        WalletAsset walletAsset = new WalletAsset();
        BeanUtils.copyProperties(reqWalletAssetPagination, walletAsset);
        //
        PaginateResult<WalletAsset> result = walletAssetService.search(reqWalletAssetPagination, walletAsset);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

}
