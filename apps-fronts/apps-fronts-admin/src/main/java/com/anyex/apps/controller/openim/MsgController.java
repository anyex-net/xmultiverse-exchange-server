/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.openim.req.*;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.openim.api.msg.req.RevokeMsgReq;
import com.anyex.openim.api.msg.req.SearchMessageReq;
import com.anyex.openim.api.msg.resp.ChatLog;
import com.anyex.openim.api.msg.resp.SearchMessageResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 消息管理
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim/message")
@Api(tags = "消息管理")
public class MsgController extends GenericController
{

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @PostMapping(value = "/single/list")
    @RequiresPermissions("openim:userMessage:data")
    @ApiOperation(value = "单聊消息列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ChatLog>> singleMsg(@ModelAttribute ReqMsgPagination pagin) throws BusinessException
    {
        if(pagin.getSessionType() == null)
        {
            pagin.setSessionType(1);
        }
        if(pagin.getCurrent() == null)
        {
            pagin.setCurrent(1);
        }
        if(pagin.getSize() == null)
        {
            pagin.setSize(20);
        }
        SearchMessageReq req = new SearchMessageReq();
        req.setSendID(pagin.getSendID());
        req.setRecvID(pagin.getRecvID());
        req.setMsgType(pagin.getMsgType());
        req.setSendTime(pagin.getSendTime());
        req.setSessionType(pagin.getSessionType());
        req.getPagination().setPageNumber(pagin.getCurrent());
        req.getPagination().setShowNumber(pagin.getSize());
        SearchMessageResp resp =openImApiService.searchMsg(req);
        pagin.setTotal(resp.getChatLogsNum()*1L);
        PaginateResult<ChatLog> result = new PaginateResult<ChatLog>(pagin, resp.getChatLogs());
        return getJsonMessage(CommonEnums.SUCCESS,result);
    }

    @PostMapping(value = "/single/revoke")
    @RequiresPermissions("openim:userMessage:operator")
    @ApiOperation(value = "单聊消息撤回", httpMethod = "POST")
    public JsonMessage<String> singleRevokeMsg(@ModelAttribute RevokeMsgReq req) throws BusinessException
    {
        String r = openImApiService.revokeMsg(req);
        return getJsonMessage(CommonEnums.SUCCESS,"success");
    }


    @PostMapping(value = "/group/list")
    @RequiresPermissions("openim:groupMessage:data")
    @ApiOperation(value = "群聊消息列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ChatLog>> groupMsg(@ModelAttribute ReqMsgPagination pagin) throws BusinessException
    {
        if(pagin.getSessionType() == null)
        {
            pagin.setSessionType(3);
        }
        if(pagin.getCurrent() == null)
        {
            pagin.setCurrent(1);
        }
        if(pagin.getSize() == null)
        {
            pagin.setSize(20);
        }
        // 公用一个方法 但不共用权限
        return singleMsg(pagin);
    }

    @PostMapping(value = "/group/revoke")
    @RequiresPermissions("openim:groupMessage:operator")
    @ApiOperation(value = "群聊消息撤回", httpMethod = "POST")
    public JsonMessage<String> groupRevokeMsg(@ModelAttribute RevokeMsgReq req) throws BusinessException
    {
        String r = openImApiService.revokeMsg(req);
        return getJsonMessage(CommonEnums.SUCCESS,"success");
    }

}
