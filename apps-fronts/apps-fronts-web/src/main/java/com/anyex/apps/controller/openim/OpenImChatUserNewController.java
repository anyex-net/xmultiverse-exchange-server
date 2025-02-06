/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.DateConst;
import com.anyex.apps.consts.GlobalConst;
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
@RequestMapping(GlobalConst.IM+"/user")
@Api(tags = "用户服务")
public class OpenImChatUserNewController extends GenericController
{
    @Autowired(required = false)
    private OpenImChatService openImChatService;

    @Autowired(required = false)
    private AttributeService attributeService;

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Value(value = "${openim.livekit.server}")
    String livekitServer;

    @Value(value = "${openim.livekit.appkey}")
    String livekitAppkey;

    @Value(value = "${openim.livekit.appsecret}")
    String livekitAppsecret;

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新用户信息", httpMethod = "POST")
    public JsonMessage<String> updateUserInfo(@Validated @RequestBody UpdateUserInfoReq req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }

        Account account = accountService.selectByPrimaryKey(principal.getId());
        Attribute attribute = attributeService.findByUserId(req.getUserID());

        if(StringUtils.isNotEmpty(req.getNickname()))
        {
            account.setAccountName(req.getNickname());
            attribute.setNickname(req.getNickname());
            //
        }

        if(StringUtils.isNotEmpty(req.getFaceURL()))
        {
            account.setHeadUrl(req.getFaceURL());
            attribute.setFaceUrl(req.getFaceURL());
            //
        }

        if(req.getGender()!=null)
        {
            account.setGender(req.getGender()==1);
            attribute.setGender(req.getGender());
        }

        if(req.getBirth()!=null)
        {
            account.setBirth(DateUtils.formatDate(new Date(req.getBirth()), DateConst.DATE_FORMAT_YMD));
            attribute.setBirthTime(new Date(req.getBirth()));
        }

        if(req.getLevel()!=null)
        {
            attribute.setLevel(req.getLevel());
        }

        if(req.getAllowBeep()!=null)
        {
            attribute.setAllowBeep(req.getAllowBeep());
        }

        if(req.getAllowAddFriend()!=null)
        {
            attribute.setAllowAddFriend(req.getAllowAddFriend());
        }

        if(req.getAllowVibration()!=null)
        {
            attribute.setAllowVibration(req.getAllowVibration());
        }

        if(req.getGlobalRecvMsgOpt()!=null)
        {
            attribute.setGlobalRecvMsgOpt(req.getGlobalRecvMsgOpt());
        }
        accountService.updateByPrimaryKey(account);
        attributeService.updateByPrimaryKey(attribute);
        openImApiService.updateUserInfoReq(account.getUserId(),attribute.getNickname(),attribute.getFaceUrl(),"");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/find/full")
    @ApiOperation(value = "获取用户所有信息", httpMethod = "POST")
    public JsonMessage<FindUserFullInfoResp> findFull(@Validated @RequestBody FindUserFullInfoReq req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        List<Attribute> result =attributeService.findByUserIds(req.getUserIDs());
        FindUserFullInfoResp rest = new FindUserFullInfoResp();
        UserFullInfo info = null;
        for(Attribute attribute :result)
        {
            info = new UserFullInfo();
            info.setEmail(attribute.getEmail());
            info.setUserID(attribute.getUserId());
            info.setAreaCode(attribute.getAreaCode());
            info.setPhoneNumber(attribute.getPhoneNumber());
            info.setAccount(StringUtils.isNotEmpty(attribute.getAreaCode())?attribute.getAreaCode()+attribute.getPhoneNumber():attribute.getEmail());
            info.setNickname(attribute.getNickname());
            info.setFaceURL(attribute.getFaceUrl());
            info.setGender(attribute.getGender());
            info.setBirth(attribute.getBirthTime()==null?null:attribute.getBirthTime().getTime());
            info.setLevel(attribute.getLevel());
            info.setAllowBeep(attribute.getAllowBeep());
            info.setAllowAddFriend(attribute.getAllowAddFriend());
            info.setAllowVibration(attribute.getAllowVibration());
            info.setGlobalRecvMsgOpt(attribute.getGlobalRecvMsgOpt());
            rest.getUsers().add(info);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS,rest);
    }

    @PostMapping(value = "/find/public")
    @ApiOperation(value = "获取用户公开信息", httpMethod = "POST")
    public JsonMessage<FindUserPublicInfoResp> findPublic(@Validated @RequestBody FindUserPublicInfoReq req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        List<Attribute> result =attributeService.findByUserIds(req.getUserIDs());
        FindUserPublicInfoResp rest = new FindUserPublicInfoResp();
        UserPublicInfo info = null;
        for(Attribute attribute :result)
        {
            info = new UserPublicInfo();
            info.setAccount(attribute.getAccount());
            info.setUserID(attribute.getUserId());
            info.setNickname(attribute.getNickname());
            info.setFaceURL(attribute.getFaceUrl());
            info.setGender(attribute.getGender());
            info.setLevel(attribute.getLevel());
            info.setEmail(attribute.getEmail());
        }

        return this.getJsonMessage(CommonEnums.SUCCESS,rest);
    }

    @PostMapping(value = "/search/full")
    @ApiOperation(value = "搜索用户所有信息", httpMethod = "POST")
    public JsonMessage<SearchUserFullInfoResp> searchFull(@Validated @RequestBody SearchUserFullInfoReq req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }

        Attribute search = new Attribute();
        // search.setNickname(req.getKeyword());
        search.setUserId(req.getKeyword());
        // search.setGender(req.getGenders());

        Pagination pagination = new Pagination(req.getPagination().getPageNumber(),req.getPagination().getShowNumber());
        PaginateResult<Attribute> result =attributeService.search(pagination,search);
        SearchUserFullInfoResp rest = new SearchUserFullInfoResp();
        rest.setTotal(result.getTotal());
        UserSearchFullInfo info = null;
        for(Attribute attribute :result.getRecords())
        {
            info = new UserSearchFullInfo();
            info.setUserID(attribute.getUserId());
            info.setNickname(attribute.getNickname());
            info.setPhoneNumber(attribute.getPhoneNumber());
            info.setFaceURL(attribute.getFaceUrl());
            info.setGender(attribute.getGender());
            info.setBirth(0L);
            info.setLevel(attribute.getLevel());
            info.setAllowBeep(attribute.getAllowBeep());
            info.setAllowAddFriend(attribute.getAllowAddFriend());
            info.setAllowVibration(attribute.getAllowVibration());
            info.setGlobalRecvMsgOpt(attribute.getGlobalRecvMsgOpt());
            rest.getUsers().add(info);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS,rest);
    }

    @PostMapping(value = "/search/public")
    @ApiOperation(value = "搜索用户所有公开信息", httpMethod = "POST")
    public JsonMessage<SearchUserPubliclInfoResp> searchPublic(@Validated @RequestBody com.anyex.apps.openim.chat.user.req.SearchUserPublicInfoReq req) throws BusinessException
    {
        // ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }

        Attribute search = new Attribute();
        search.setNickname(req.getKeyword());
        search.setGender(req.getGenders());

        Pagination pagination = new Pagination(req.getPagination().getPageNumber(),req.getPagination().getShowNumber());
        PaginateResult<Attribute> result =attributeService.search(pagination,search);

        SearchUserPubliclInfoResp rest = new SearchUserPubliclInfoResp();
        rest.setTotal(result.getTotal());
        UserPublicInfo info = null;
        for(Attribute attribute :result.getRecords())
        {
            info = new UserPublicInfo();
            info.setAccount(attribute.getAccount());
            info.setUserID(attribute.getUserId());
            info.setNickname(attribute.getNickname());
            info.setFaceURL(attribute.getFaceUrl());
            info.setGender(attribute.getGender());
            info.setLevel(attribute.getLevel());
            info.setEmail(attribute.getEmail());

            rest.getUsers().add(info);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS,rest);
    }

    @PostMapping(value = "/rtc/get_token")
    @ApiOperation(value = "RTC配置", httpMethod = "POST")
    public JsonMessage<LiveKitTokenResp> rtc(@Validated @RequestBody com.anyex.apps.openim.chat.user.req.LiveKitTokenReq req) throws BusinessException
    {
        Account account = accountService.findByUserId(req.getIdentity());
        String name = account.getAccountName();
        String roomName = req.getRoom();
        AccessToken token = new AccessToken(livekitAppkey, livekitAppsecret);
        token.setName(name);
        token.setIdentity(req.getIdentity());
        token.setMetadata("metadata");
        token.setExpiration(new Date(System.currentTimeMillis()+ 1000L *60*60*24*365));
//        token所拥有的权限
        List<String> list = new ArrayList<>();
//        摄像头
        list.add("camera");
//        麦克风
        list.add("microphone");
//        list.add("screen_share"); //屏幕共享
//        list.add("screen_share_audio"); //屏幕共享音频
//        允许参与者发布相机，但不允许其他来源
        CanPublishSources canPublishSources = new CanPublishSources(list);
//        配置解释：https://docs.livekit.io/realtime/concepts/authentication/
//        RoomJoin:加入房间许可   Room：房间名称
        token.addGrants(new RoomJoin(true), new Room(roomName),canPublishSources);
      //  return token.toJwt();
        LiveKitTokenResp ret = new LiveKitTokenResp();
        ret.setServerUrl(livekitServer);
        ret.setToken(token.toJwt());
        return this.getJsonMessage(CommonEnums.SUCCESS,ret);
    }

}
