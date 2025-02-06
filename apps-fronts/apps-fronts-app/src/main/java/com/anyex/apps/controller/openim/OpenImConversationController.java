/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.DateConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.openim.req.ReqConversationLimit;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.openim.chat.user.req.FindUserFullInfoReq;
import com.anyex.apps.openim.chat.user.req.FindUserPublicInfoReq;
import com.anyex.apps.openim.chat.user.req.SearchUserFullInfoReq;
import com.anyex.apps.openim.chat.user.req.UpdateUserInfoReq;
import com.anyex.apps.openim.chat.user.resp.*;
import com.anyex.apps.openim.chat.vo.UserFullInfo;
import com.anyex.apps.openim.chat.vo.UserPublicInfo;
import com.anyex.apps.openim.chat.vo.UserSearchFullInfo;
import com.anyex.apps.openim.model.ConversationLimitModel;
import com.anyex.apps.openim.service.ConversationLimitService;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.openim.service.OpenImChatService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.DateUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.StringUtils;
import io.livekit.server.AccessToken;
import io.livekit.server.CanPublishSources;
import io.livekit.server.Room;
import io.livekit.server.RoomJoin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(GlobalConst.IM+"/converation")
@Api(tags = "会话服务")
public class OpenImConversationController extends GenericController
{
    @Autowired(required = false)
    private ConversationLimitService conversationLimitService;

    @Autowired(required = false)
    private AccountService accountService;


    @PostMapping(value = "/limit/query")
    @ApiOperation(value = "会话限制", httpMethod = "POST")
    public JsonMessage<ConversationLimitModel> check(@Validated @RequestBody ReqConversationLimit req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        ConversationLimitModel r = conversationLimitService.canChartCheck(req.getConversationId(),account.getUserId().toString(),req.getToUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS,r);
    }

    @PostMapping(value = "/limit/add")
    @ApiOperation(value = "会话次数计次", httpMethod = "POST")
    public JsonMessage<Integer> record(@Validated @RequestBody ReqConversationLimit req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        conversationLimitService.canChartRecord(req.getConversationId(),account.getUserId().toString(),req.getToUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }


}
