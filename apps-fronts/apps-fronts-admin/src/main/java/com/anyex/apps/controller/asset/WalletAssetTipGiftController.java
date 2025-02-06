/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.asset.entity.WalletAssetTipGift;
import com.anyex.apps.asset.service.WalletAssetTipGiftService;

import com.anyex.apps.controller.asset.req.ReqWalletAssetTipGift;
import com.anyex.apps.controller.asset.req.ReqWalletAssetTipGiftPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 钱包资产打赏礼物记录 控制器
 * <p>File：WalletAssetTipGiftController.java </p>
 * <p>Title: WalletAssetTipGiftController </p>
 * <p>Description:WalletAssetTipGiftController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/asset/walletAssetTipGift")
@Api(description = "钱包资产打赏礼物记录")
public class WalletAssetTipGiftController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetTipGiftService walletAssetTipGiftService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("asset:walletAssetTipGift:data")
    @ApiOperation(value = "根据ID取钱包资产打赏礼物记录", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetTipGiftService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("asset:walletAssetTipGift:operator")
    @ApiOperation(value = "保存钱包资产打赏礼物记录", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqWalletAssetTipGift info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            WalletAssetTipGift entity = new WalletAssetTipGift();
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
                walletAssetTipGiftService.insert(entity);
            } else {
                walletAssetTipGiftService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("asset:walletAssetTipGift:data")
    @ApiOperation(value = "查询钱包资产打赏礼物记录", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqWalletAssetTipGiftPagination pagin) throws BusinessException
    {
        WalletAssetTipGift entity = new WalletAssetTipGift();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<WalletAssetTipGift> result = walletAssetTipGiftService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("asset:walletAssetTipGift:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        walletAssetTipGiftService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
