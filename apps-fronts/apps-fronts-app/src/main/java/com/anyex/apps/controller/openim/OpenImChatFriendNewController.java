/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.openim.chat.req.SearchFriendReq;
import com.anyex.apps.openim.chat.resp.SearchUserInfoResp;
import com.anyex.apps.openim.chat.vo.UserFullInfo;
import com.anyex.apps.openim.service.OpenImChatService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.openim.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(GlobalConst.IM+"/friend")
@Api(tags = "好友管理")
public class OpenImChatFriendNewController extends GenericController
{
    @Autowired(required = false)
    private OpenImChatService openImChatService;

    @Autowired(required = false)
    private AttributeService attributeService;

    @PostMapping(value = "/search")
    @ApiOperation(value = "搜索好友", httpMethod = "POST")
    public JsonMessage<SearchUserInfoResp> search(@Validated @RequestBody SearchFriendReq req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Attribute attribute = attributeService.findByUserId(req.getUserID());
        SearchUserInfoResp resp = new SearchUserInfoResp();
        if(attribute != null)
        {
            resp.setTotal(1);
            UserFullInfo info = new UserFullInfo();
            info.setUserID(attribute.getUserId());
            info.setPassword("");
            info.setAccount(attribute.getAccount());
            info.setPhoneNumber(attribute.getPhoneNumber()==null?"": DesensitizedUtil.mobilePhone(attribute.getPhoneNumber()));
            info.setAreaCode(attribute.getAreaCode());
            info.setEmail(attribute.getEmail()==null?"": DesensitizedUtil.email(attribute.getEmail()));
            info.setNickname(attribute.getNickname());
            info.setFaceURL(attribute.getFaceUrl());
            info.setLevel(attribute.getLevel());
            info.setGender(attribute.getGender());
            info.setBirth(attribute.getBirthTime().getTime());
            info.setAllowAddFriend(attribute.getAllowAddFriend());
            info.setAllowBeep(attribute.getAllowBeep());
            info.setAllowVibration(attribute.getAllowVibration());
            info.setGlobalRecvMsgOpt(attribute.getGlobalRecvMsgOpt());
            resp.getUsers().add(info);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,resp);
    }

}
