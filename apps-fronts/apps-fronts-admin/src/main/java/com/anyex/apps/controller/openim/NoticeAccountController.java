/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.controller.account.req.ReqAccountPagination;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.utils.StringUtils;
import com.anyex.openim.api.msg.req.BatchSendMsgReq;
import com.anyex.openim.api.msg.resp.BatchSendMsgResp;
import com.anyex.openim.api.user.req.AddNotificationAccountReq;
import com.anyex.openim.api.user.req.UpdateNotificationAccountInfoReq;
import com.anyex.openim.api.user.resp.AccountCheckResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.openim.entity.NoticeAccount;
import com.anyex.apps.openim.service.NoticeAccountService;

import com.anyex.apps.controller.openim.req.ReqNoticeAccount;
import com.anyex.apps.controller.openim.req.ReqNoticeAccountPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 通知账号 控制器
 * <p>File：NoticeAccountController.java </p>
 * <p>Title: NoticeAccountController </p>
 * <p>Description:NoticeAccountController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim")
@Api(tags = "通知账号")
public class NoticeAccountController extends GenericController
{
    @Autowired(required = false)
    private NoticeAccountService noticeAccountService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Autowired(required = false)
    private AccountService accountService;

    @GetMapping(value = "/notificationAccount/findBy")
    @RequiresPermissions("openim:notificationAccount:data")
    @ApiOperation(value = "根据ID取通知账号", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, noticeAccountService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/notificationAccount/save")
    @RequiresPermissions("openim:notificationAccount:operator")
    @ApiOperation(value = "保存通知账号", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqNoticeAccount info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            NoticeAccount entity = new NoticeAccount();
            BeanUtils.copyProperties(info, entity);
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                AccountCheckResp r = openImApiService.accountCheck(info.getUserId());
                if(StringUtils.equalsIgnoreCase(r.getResults().get(0).getAccountStatus(),"registered"))
                {
                    throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
                }
                noticeAccountService.insert(entity);
                AddNotificationAccountReq userInfo = new AddNotificationAccountReq();
                userInfo.setUserID(entity.getUserId());
                userInfo.setNickName(entity.getNickname());
                userInfo.setFaceURL(entity.getFaceUrl());
                openImApiService.addNotificationAccount(userInfo);
            } else {
                NoticeAccount oldEntity = noticeAccountService.selectByPrimaryKey(entity.getId());
                oldEntity.setNickname(info.getNickname());
                oldEntity.setFaceUrl(info.getFaceUrl());
                noticeAccountService.updateByPrimaryKey(oldEntity);

                UpdateNotificationAccountInfoReq req = new UpdateNotificationAccountInfoReq();
                req.setUserID(oldEntity.getUserId());
                req.setNickName(info.getNickname());
                req.setFaceURL(info.getFaceUrl());
                openImApiService.updateNotificationAccountInfo(req);
            }
        }
        return json;
    }

    @PostMapping(value = "/notificationAccount/data")
    @RequiresPermissions("openim:notificationAccount:data")
    @ApiOperation(value = "查询通知账号", httpMethod = "POST")
    public JsonMessage<PaginateResult<NoticeAccount>> data(@ModelAttribute ReqNoticeAccountPagination pagin) throws BusinessException
    {
        NoticeAccount entity = new NoticeAccount();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<NoticeAccount> result = noticeAccountService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/notificationPublish/list")
    @RequiresPermissions("openim:notificationPublish:data")
    @ApiOperation(value = "通知用户下拉列表", httpMethod = "POST")
    public JsonMessage<List<NoticeAccount>> list() throws BusinessException
    {
        NoticeAccount search = new NoticeAccount();
        List<NoticeAccount> result = noticeAccountService.findList(search);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/notificationPublish/notificationPublish/user")
    @RequiresPermissions("openim:notificationPublish:data")
    @ApiOperation(value = "用户下拉列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Account>> data(@ModelAttribute ReqAccountPagination reqAccountPagination) throws BusinessException
    {
        //
        Account account = new Account();
        BeanUtils.copyProperties(reqAccountPagination, account);
        account.setStatus(0);
        //
        PaginateResult<Account> result = accountService.search(reqAccountPagination, account);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/notificationPublish/batchSendMsg")
    @RequiresPermissions("openim:notificationPublish:operator")
    @ApiOperation(value = "发送通知", httpMethod = "POST")
    public JsonMessage<BatchSendMsgResp> batchSendMsg(@RequestBody BatchSendMsgReq req) throws BusinessException
    {
        BatchSendMsgResp result = openImApiService.batchSendMsg(req);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/notificationPublish/batchSendMsgAuth")
    @RequiresPermissions("openim:notificationPublish:operator")
    @ApiOperation(value = "获取IMToken", httpMethod = "GET")
    public JsonMessage<String> batchSendMsgAuth(@ModelAttribute BatchSendMsgReq req) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, openImApiService.getAdminToken());
    }


    /*@PostMapping(value = "/del")
    @RequiresPermissions("openim:noticeAccount:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        noticeAccountService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }*/
}
