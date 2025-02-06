/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.asset.req.ReqWalletAssetPagination;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 钱包资产 控制器
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

    @GetMapping(value = "/getAccountWalletAsset")
    @ApiOperation(value = "获取账户取钱包资产", httpMethod = "GET")
    public JsonMessage<WalletAsset> getAccountWalletAsset(HttpServletRequest request) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        WalletAsset walletAsset = new WalletAsset();
        walletAsset.setAccountId(principal.getId());
        //
        List<WalletAsset> listWalletAsset = walletAssetService.findList(walletAsset);
        if(null != listWalletAsset && listWalletAsset.size() >= 1) {
            log.info("getAccountWalletAsset walletAsset:{}", listWalletAsset.get(0));
            return this.getJsonMessage(CommonEnums.SUCCESS, listWalletAsset.get(0));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
    }

//    @GetMapping(value = "/findBy")
//    @ApiOperation(value = "根据ID取钱包资产", httpMethod = "GET")
//    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
//    public JsonMessage<WalletAsset> findBy(@RequestParam("id") Long id) throws BusinessException
//    {
//        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetService.selectByPrimaryKey(id));
//    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询钱包资产分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAsset>> data(@Validated @RequestBody ReqWalletAssetPagination reqWalletAssetPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        WalletAsset walletAsset = new WalletAsset();
        BeanUtils.copyProperties(reqWalletAssetPagination, walletAsset);
        walletAsset.setAccountId(principal.getId());
        //
        PaginateResult<WalletAsset> result = walletAssetService.search(reqWalletAssetPagination, walletAsset);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
