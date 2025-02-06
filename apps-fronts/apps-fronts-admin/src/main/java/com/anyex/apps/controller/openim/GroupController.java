/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountPagination;
import com.anyex.apps.controller.openim.req.*;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.openim.entity.NoticeAccount;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.openim.api.group.req.*;
import com.anyex.openim.api.group.resp.*;
import com.anyex.openim.api.vo.GroupInfo;
import com.anyex.openim.api.vo.GroupMemberFullInfo;
import com.anyex.openim.api.vo.SetGroupMemberInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 群管理
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim/group")
@Api(tags = "群管理")
public class GroupController extends GenericController
{

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Autowired(required = false)
    private AccountService accountService;

    @PostMapping(value = "/list")
    @RequiresPermissions("openim:group:data")
    @ApiOperation(value = "群列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<CMSGroup>> groupList(@ModelAttribute ReqGroupPagination pagin) throws BusinessException
    {
        GetGroupsReq req = new GetGroupsReq();
        req.getPagination().setPageNumber(pagin.getCurrent());
        req.getPagination().setShowNumber(pagin.getSize());
        req.setGroupID(pagin.getGroupID());
        req.setGroupName(pagin.getGroupName());
        GetGroupsResp resp =openImApiService.getGroups(req);
        pagin.setTotal(resp.getTotal()*1L);
        PaginateResult<CMSGroup> result = new PaginateResult<CMSGroup>(pagin, resp.getGroups());
        return getJsonMessage(CommonEnums.SUCCESS,result);
    }

    @PostMapping(value = "/info")
    @RequiresPermissions("openim:group:data")
    @ApiOperation(value = "群信息", httpMethod = "POST")
    public JsonMessage<List<GroupInfo>> groupInfo(@ModelAttribute ReqGroupInfo reqInfo) throws BusinessException
    {
        GetGroupsInfoReq req = new GetGroupsInfoReq();
        req.getGroupIDs().add(reqInfo.getGroupID());
        GetGroupsInfoResp r = openImApiService.getGroupInfo(req);
        return getJsonMessage(CommonEnums.SUCCESS,r.getGroupInfos());
    }

    @PostMapping(value = "/add")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "新建群", httpMethod = "POST")
    public JsonMessage<GroupInfo> createGroup(@Validated @RequestBody CreateGroupReq createGroupReq) throws BusinessException
    {
        CreateGroupResp r =  openImApiService.createGroup(createGroupReq);
        return getJsonMessage(CommonEnums.SUCCESS,r.getGroupInfo());
    }

    @PostMapping(value = "/update")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "设置群", httpMethod = "POST")
    public JsonMessage<GroupInfo> createGroup(@RequestBody SetGroupInfoReq setGroupInfoReq) throws BusinessException
    {
        String r =  openImApiService.setGroupInfo(setGroupInfoReq);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "群解散", httpMethod = "POST")
    public JsonMessage<String> dismissGroup(@ModelAttribute ReqGroupInfo reqInfo) throws BusinessException
    {
        DismissGroupReq req = new DismissGroupReq();
        req.setGroupID(reqInfo.getGroupID());
        openImApiService.dismissGroup(req);
        return getJsonMessage(CommonEnums.SUCCESS,"success");
    }

    @PostMapping(value = "/forbidden")
    @RequiresPermissions("openim:group:data")
    @ApiOperation(value = "群全体禁言/解除禁言", httpMethod = "POST")
    public JsonMessage<String> list(@ModelAttribute ReqGroupMute req) throws BusinessException
    {
        if(req.getStatus())
        {
            MuteGroupReq muteGroupReq = new MuteGroupReq();
            muteGroupReq.setGroupID(req.getGroupID());
            openImApiService.muteGroup(muteGroupReq);
        }
        else
        {
            CancelMuteGroupReq muteGroupReq = new CancelMuteGroupReq();
            muteGroupReq.setGroupID(req.getGroupID());
            openImApiService.cancelMuteGroup(muteGroupReq);
        }

        return getJsonMessage(CommonEnums.SUCCESS,"success");
    }

    @PostMapping(value = "/members")
    @RequiresPermissions("openim:group:data")
    @ApiOperation(value = "群成员", httpMethod = "POST")
    public JsonMessage<PaginateResult<GroupMemberFullInfo>> data(@ModelAttribute ReqGroupMemberPagination pagin) throws BusinessException
    {
        if(StringUtils.isNotEmpty(pagin.getKeyword()))
        {
            GetGroupMemberListReq req = new GetGroupMemberListReq();
            req.getPagination().setPageNumber(pagin.getCurrent());
            req.getPagination().setShowNumber(3000);
            req.setGroupID(pagin.getGroupID());
           // req.setFilter(pagin.getFilter());
            GetGroupMemberListResp resp = openImApiService.getGroupMemberList(req);
            //筛选
            List<GroupMemberFullInfo> filterUserList = resp.getMembers().stream().filter(user -> StringUtils.equalsAnyIgnoreCase(user.getUserID(),pagin.getKeyword())||StringUtils.equalsAnyIgnoreCase(user.getNickname(),pagin.getKeyword())).collect(Collectors.toList());
            pagin.setTotal(filterUserList.size()*1L);
            PaginateResult<GroupMemberFullInfo> result = new PaginateResult<GroupMemberFullInfo>(pagin, filterUserList);
            return getJsonMessage(CommonEnums.SUCCESS,result);
        }
        else {
            GetGroupMemberListReq req = new GetGroupMemberListReq();
            req.getPagination().setPageNumber(pagin.getCurrent());
            req.getPagination().setShowNumber(pagin.getSize());
            req.setGroupID(pagin.getGroupID());
            // req.setFilter(pagin.getFilter());
            GetGroupMemberListResp resp = openImApiService.getGroupMemberList(req);
            pagin.setTotal(resp.getTotal()*1L);
            PaginateResult<GroupMemberFullInfo> result = new PaginateResult<GroupMemberFullInfo>(pagin, resp.getMembers());
            return getJsonMessage(CommonEnums.SUCCESS,result);
        }
    }

    @PostMapping(value = "/forbidden/member")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "群成员禁言/解除成员禁言", httpMethod = "POST")
    public JsonMessage<String> list(@ModelAttribute ReqGroupMemberMute req) throws BusinessException
    {
        if(req.getStatus())
        {
            MuteGroupMemberReq muteGroupReq = new MuteGroupMemberReq();
            muteGroupReq.setGroupID(req.getGroupID());
            muteGroupReq.setMutedSeconds(req.getMutedSeconds());
            muteGroupReq.setUserID(req.getUserID());
            openImApiService.muteGroupMember(muteGroupReq);
        }
        else
        {
            CancelMuteGroupMemberReq muteGroupReq = new CancelMuteGroupMemberReq();
            muteGroupReq.setGroupID(req.getGroupID());
            muteGroupReq.setUserID(req.getUserID());
            openImApiService.cancelMuteGroupMember(muteGroupReq);
        }
        return getJsonMessage(CommonEnums.SUCCESS,"success");
    }

    @PostMapping(value = "/setRole")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "设置成员身份", httpMethod = "POST")
    public JsonMessage setGroupMemberInfo(@Valid @ModelAttribute SetGroupMemberInfo req) throws BusinessException
    {
        GetGroupsInfoReq req0 = new GetGroupsInfoReq();
        req0.getGroupIDs().add(req.getGroupID());
        GetGroupsInfoResp r = openImApiService.getGroupInfo(req0);

        if(req.getRoleLevel()!=100 && StringUtils.equals(r.getGroupInfos().get(0).getOwnerUserID(), req.getUserID()) )
        {
            //  转让群
            return getJsonMessage(CommonEnums.FAIL,"你是群主不能变更身份");
        }

        if(req.getRoleLevel()==100 && !StringUtils.equals(r.getGroupInfos().get(0).getOwnerUserID(), req.getUserID()) )
        {
            //  转让群
            TransferGroupOwnerReq transferGroupOwnerReq = new TransferGroupOwnerReq();
            transferGroupOwnerReq.setGroupID(req.getGroupID());
            transferGroupOwnerReq.setOldOwnerUserID(r.getGroupInfos().get(0).getOwnerUserID());
            transferGroupOwnerReq.setNewOwnerUserID(req.getUserID());
            openImApiService.transferGroupOwner(transferGroupOwnerReq);
        }
        else {
            SetGroupMemberInfoReq reqBody = new SetGroupMemberInfoReq();
            List<SetGroupMemberInfo> members = new ArrayList<SetGroupMemberInfo>();
            members.add(req);
            reqBody.setMembers(members);
            openImApiService.setGroupMemberInfo(reqBody);
        }
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/add/member")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "添加群成员", httpMethod = "POST")
    public JsonMessage<PaginateResult<NoticeAccount>> addMember(InviteToGroup req) throws BusinessException
    {
        openImApiService.inviteUserToGroupInteface(req);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/del/member")
    @RequiresPermissions("openim:group:operator")
    @ApiOperation(value = "删除群成员", httpMethod = "POST")
    public JsonMessage<PaginateResult<NoticeAccount>> delMember(KickGroupMemberReq req) throws BusinessException
    {
        openImApiService.kickGroupMember(req);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/user")
    @RequiresPermissions("openim:group:data")
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



}
