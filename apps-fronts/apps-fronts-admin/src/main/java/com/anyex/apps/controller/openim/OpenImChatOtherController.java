/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;


import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.openim.service.OpenImChatService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(GlobalConst.IM)
@Api(tags = "其他服务")
public class OpenImChatOtherController extends GenericController
{
    @Autowired(required = false)
    private OpenImChatService openImChatService;

    @Autowired(required = false)
    private AttributeService attributeService;


    @PostMapping(value = "/client_config/get")
    @ApiOperation(value = "获取客户端配置", httpMethod = "POST")
    public JsonMessage< Map<String, String>> getClientConfig() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Map<String, String> config = new HashMap<String, String>();
        config.put("allowSendMsgNotFriend",GlobalConst.IM_ALLOW_SEND_MSG_NOT_FRIEND);
        config.put("needInvitationCodeRegister",GlobalConst.IM_NEED_INVITATION_CODE_REGISTER);
        return getJsonMessage(CommonEnums.SUCCESS,config);
    }

}
