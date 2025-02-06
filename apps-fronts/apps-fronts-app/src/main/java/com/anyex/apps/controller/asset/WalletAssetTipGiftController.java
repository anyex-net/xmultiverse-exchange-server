/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.asset.entity.WalletAssetTipGift;
import com.anyex.apps.asset.service.WalletAssetTipGiftService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.controller.asset.req.ReqWalletAssetTipGiftPagination;
import com.anyex.apps.controller.asset.req.ReqWalletAssetTipGiftSend;
import com.anyex.apps.controller.social.req.ReqUserId;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 钱包资产赠送礼品记录 控制器
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
@Api(tags = "钱包资产赠送礼品记录")
public class WalletAssetTipGiftController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetTipGiftService walletAssetTipGiftService;


    @Autowired(required = false)
    private AccountService accountService;

    @Autowired
    RedisTemplate redisTemplate;


    @PostMapping(value = "/sendList")
    @ApiOperation(value = "我送出的礼物", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAssetTipGift>> sendList(@Validated @RequestBody ReqWalletAssetTipGiftPagination reqWalletAssetFlowsPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        BeanUtils.copyProperties(reqWalletAssetFlowsPagination, walletAssetTipGift);
        walletAssetTipGift.setFromAccountId(principal.getId());
        //
        PaginateResult<WalletAssetTipGift> result = walletAssetTipGiftService.search(reqWalletAssetFlowsPagination, walletAssetTipGift);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/getList")
    @ApiOperation(value = "我收到的礼物", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAssetTipGift>> getList(@Validated @RequestBody ReqWalletAssetTipGiftPagination reqWalletAssetFlowsPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        BeanUtils.copyProperties(reqWalletAssetFlowsPagination, walletAssetTipGift);
        walletAssetTipGift.setToAccountId(principal.getId());
        walletAssetTipGift.setStatus(2);
        //
        PaginateResult<WalletAssetTipGift> result = walletAssetTipGiftService.search(reqWalletAssetFlowsPagination, walletAssetTipGift);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }


    @PostMapping(value = "/receiveStatus")
    @ApiOperation(value = "我是否收到TA的礼物", httpMethod = "POST")
    public JsonMessage<Boolean> getCnt(@Validated @RequestBody ReqUserId userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        Account account = accountService.findByUserId(userId.getUserId());
        //
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(account.getId());
        walletAssetTipGift.setToAccountId(principal.getId());
        walletAssetTipGift.setStatus(2);
        List<WalletAssetTipGift> result = walletAssetTipGiftService.findList(walletAssetTipGift);
        return getJsonMessage(CommonEnums.SUCCESS, result.size()>0);
    }

    @PostMapping(value = "/sendStatus")
    @ApiOperation(value = "我是否送给TA礼物", httpMethod = "POST")
    public JsonMessage<Boolean> getSendCnt(@Validated @RequestBody ReqUserId userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        Account account = accountService.findByUserId(userId.getUserId());
        //
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(principal.getId());
        walletAssetTipGift.setToAccountId(account.getId());
        List<WalletAssetTipGift> result = walletAssetTipGiftService.findList(walletAssetTipGift);
        return getJsonMessage(CommonEnums.SUCCESS, result.size()>0);
    }

    @PostMapping(value = "/canChat")
    @ApiOperation(value = "是否可以与TA聊天", httpMethod = "POST")
    public JsonMessage<Boolean> chatStatus(@Validated @RequestBody ReqUserId userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        Account account = accountService.findByUserId(userId.getUserId());
        // 我是否送给TA礼物
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(principal.getId());
        walletAssetTipGift.setToAccountId(account.getId());
        List<WalletAssetTipGift> result = walletAssetTipGiftService.findList(walletAssetTipGift);
        if(result.size()>0)
        {
            return getJsonMessage(CommonEnums.SUCCESS, true);
        }
        // TA是否送给我礼物
        walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(account.getId());
        walletAssetTipGift.setToAccountId(principal.getId());
        walletAssetTipGift.setStatus(2);
        result = walletAssetTipGiftService.findList(walletAssetTipGift);
        return getJsonMessage(CommonEnums.SUCCESS, result.size()>0);
    }

    @PostMapping(value = "/sendGift")
    @ApiOperation(value = "发送礼物", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 2, isLogin = true) // 登录情况下限制2秒内最多请求1次
    public JsonMessage<String> sendGift(@Validated @RequestBody ReqWalletAssetTipGiftSend req) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(principal.getId());
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                Account account = accountService.findByUserId(req.getUserId());
                WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
                walletAssetTipGift.setFromAccountId(principal.getId());
                walletAssetTipGift.setToAccountId(account.getId());
                walletAssetTipGift.setRemark(req.getGiftNo());
                walletAssetTipGift.setTrxBalance(req.getTrxBalance());
                walletAssetTipGift.setTrxFee(req.getTrxBalance().multiply(BigDecimal.valueOf(0.3)).setScale(2,BigDecimal.ROUND_UP));
                walletAssetTipGiftService.sendGift(walletAssetTipGift);
                return getJsonMessage(CommonEnums.SUCCESS, "success");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("发送礼物异常：accountId={} error={}", principal.getId(), e.getMessage());
                return getJsonMessage(CommonEnums.FAIL, e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("发送礼物异常：accountId={}  error={}", principal.getId(),  "分布式锁获取失败");
            return getJsonMessage(CommonEnums.SERVICE_BUSY_ERROR);
        }
    }
}
