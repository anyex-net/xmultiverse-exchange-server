/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.asset.req.ReqWalletAdjust;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.asset.entity.WalletAssetAdjust;
import com.anyex.apps.asset.service.WalletAssetAdjustService;

import com.anyex.apps.controller.asset.req.ReqWalletAssetAdjustPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 钱包资产调整记录表 控制器
 * <p>File：WalletAssetAdjustController.java </p>
 * <p>Title: WalletAssetAdjustController </p>
 * <p>Description:WalletAssetAdjustController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/asset/walletAssetAdjust")
@Api(tags = "钱包资产调整记录")
public class WalletAssetAdjustController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetAdjustService walletAssetAdjustService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    AccountService accountService;

    @Autowired(required = false)
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Autowired(required = false)
    WalletAssetService walletAssetService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("asset:walletAssetAdjust:data")
    @ApiOperation(value = "根据ID取钱包资产调整记录", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetAdjustService.selectByPrimaryKey(id));
    }


    @PostMapping(value = "/data")
    @RequiresPermissions("asset:walletAssetAdjust:data")
    @ApiOperation(value = "查询钱包资产调整记录", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqWalletAssetAdjustPagination pagin) throws BusinessException
    {
        WalletAssetAdjust entity = new WalletAssetAdjust();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<WalletAssetAdjust> result = walletAssetAdjustService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/adjust")
    @RequiresPermissions("asset:walletAssetAdjust:operator")
    @ApiOperation(value = "资产调整", httpMethod = "POST")
    public JsonMessage<WalletAsset> adjust(@Validated @ModelAttribute ReqWalletAdjust adjust) throws BusinessException
    {
        if(adjust.getDirection() != 1 && adjust.getDirection() != -1)
        {
            throw new BusinessException("资产调整方向错误");
        }
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
        redisLockName.append(adjust.getAccountId());
        //  分布式锁 锁用户ID和钱币类型
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        // 分布式锁
        if (redisLock.lock()) {
            try {
                Account account = accountService.selectByPrimaryKey(adjust.getAccountId());
                if(null == account)
                {
                    throw new BusinessException("账户不存在");
                }
                // 资产处理
                WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(adjust.getAccountId(), GlobalConst.CURRENCY_PKR);
                if (null == asset) {
                    throw new BusinessException("钱包账户不存在");
                }
                if(adjust.getDirection() == -1) {
                    if (null == asset || (asset.getBalance().subtract(asset.getFrozenBal())).compareTo(adjust.getAmount()) < 0) {
                        log.error("账户{} PKR 资产不存在或可以可用余额不足", adjust.getAccountId());
                        throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
                    }
                }
                walletAssetTransactionsService.walletAssetAdjust(adjust.getAccountId(), adjust.getDirection(),adjust.getAmount(), adjust.getAttachment(), adjust.getRemark());
                return getJsonMessage(CommonEnums.SUCCESS);
            } catch (BusinessException e) {
                throw e;
            } catch (Exception e) {
                // 业务出现异常
                e.printStackTrace();
                log.error("资金调整异常：{}", e.getLocalizedMessage());
                throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
            } finally {
                redisLock.unlock();
            }
        } else {
            // 触发分布式锁
            log.error("资金调整触发分布式锁:{}", adjust.getAccountId());
            throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
        }
    }

}
