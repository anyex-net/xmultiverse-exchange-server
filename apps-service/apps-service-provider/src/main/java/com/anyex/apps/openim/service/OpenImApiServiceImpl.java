package com.anyex.apps.openim.service;

import com.anyex.openim.api.*;
import com.anyex.openim.api.auth.req.ForceLogoutReq;
import com.anyex.openim.api.auth.req.GetUserTokenReq;
import com.anyex.openim.api.auth.req.UserTokenReq;
import com.anyex.openim.api.auth.resp.UserTokenResp;
import com.anyex.openim.api.conversation.req.GetSortedConversationListReq;
import com.anyex.openim.api.conversation.resp.GetSortedConversationListResp;
import com.anyex.openim.api.friend.req.DeleteFriendReq;
import com.anyex.openim.api.friend.req.GetPaginationFriendsReq;
import com.anyex.openim.api.friend.req.ImportFriendReq;
import com.anyex.openim.api.friend.resp.GetPaginationFriendsResp;
import com.anyex.openim.api.group.req.*;
import com.anyex.openim.api.group.resp.CreateGroupResp;
import com.anyex.openim.api.group.resp.GetGroupMemberListResp;
import com.anyex.openim.api.group.resp.GetGroupsInfoResp;
import com.anyex.openim.api.group.resp.GetGroupsResp;
import com.anyex.openim.api.msg.req.*;
import com.anyex.openim.api.msg.resp.BatchSendMsgResp;
import com.anyex.openim.api.msg.resp.PullMessageBySeqsResp;
import com.anyex.openim.api.msg.resp.SearchMessageResp;
import com.anyex.openim.api.msg.resp.SendMsgResp;
import com.anyex.openim.api.third.req.SearchLogsReq;
import com.anyex.openim.api.third.resp.LogInfoResp;
import com.anyex.openim.api.user.req.*;
import com.anyex.openim.api.user.resp.AccountCheckResp;
import com.anyex.openim.api.user.resp.AddNotificationAccountResp;
import com.anyex.openim.api.user.resp.GetPaginationUsersResp;
import com.anyex.openim.api.user.resp.GetUsersOnlineStatusResp_SuccessResult;
import com.anyex.openim.api.vo.UserInfo;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.base.OpenimConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class OpenImApiServiceImpl implements OpenImApiService {

    @Autowired
    OpenimConfig openimConfig;


    /**
     * 获取超级账户的token
     * @return
     */
    @Override
    public String getAdminToken() {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        UserTokenReq req = new UserTokenReq();
        // req.setSecret("openIM123"); 实现方法会从配置文件中填入
        req.setPlatformID(1);
        req.setUserID("imAdmin");
        OpenImResult<UserTokenResp> ret = OpenImApiAuthRest.userToken(openimConfig,openImToken,req);
        return ret.getData().getToken();
    }



    @Override
    public GetSortedConversationListResp getConversation(String userId,String conversationId) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setImToken(getAdminToken());
        openImToken.setOperationId(UUID.randomUUID().toString());
        GetSortedConversationListReq req = new GetSortedConversationListReq();
        // req.setSecret("openIM123"); 实现方法会从配置文件中填入
        List<String> conversationList = new ArrayList<String>();
        conversationList.add(conversationId);
        req.setConversationIDs(conversationList);
        req.setUserID(userId);
        OpenImResult<GetSortedConversationListResp> ret = OpenImApiConversationRest.getSortedConversationList(openimConfig,openImToken,req);
        return ret.getData();
    }




    @Override
    public PullMessageBySeqsResp getHistoryMsg(String userId,String conversationId) {

        OpenImToken openImToken = new OpenImToken();
        openImToken.setImToken(getAdminToken());
        openImToken.setOperationId(UUID.randomUUID().toString());
        PullMessageBySeqsReq req = new PullMessageBySeqsReq();
        List<SeqRange> conversationList = new ArrayList<>();
        SeqRange r = new SeqRange();
        r.setConversationID(conversationId);
        r.setBegin(1L);
        r.setEnd(10L);
        r.setNum(10L);
        conversationList.add(r);
        req.setUserID(userId);
        req.setSeqRanges(conversationList);
        OpenImResult<PullMessageBySeqsResp> ret = OpenImApiMsgRest.pullMsgBySeqs(openimConfig,openImToken,req);
        return ret.getData();
    }


    /**
     * 为用户获取token
     * @param req
     * @return
     */
    @Override
    public String getImToken(GetUserTokenReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiAuthRest.getUserToken(openimConfig,openImToken,req).getData().getToken();
    }


    /**
     * 注册用户
     * @param info
     * @return
     */
    @Override
    public String registerUser(UserInfo info) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        UserRegisterReq req = new UserRegisterReq();
        req.getUsers().add(info);
        // req.setSecret(); 已在注册服务中填入 不需要再外层set
        return OpenImApiUserRest.userRegister(openimConfig,openImToken,req).getData();
    }

    @Override
    public void forceLogout(Integer platform,String userId) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());

        ForceLogoutReq req = new ForceLogoutReq();
        req.setPlatformID(platform);
        req.setUserID(userId);
        OpenImApiAuthRest.forceLogout(openimConfig,openImToken,req);
    }


    @Override
    public void updateUserInfoReq(String userId,String nickName,String faceUrl,String ex) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());

        UpdateUserInfoReq req = new UpdateUserInfoReq();
        UserInfo info = new UserInfo();
        info.setUserID(userId);
        info.setNickname(nickName);
        info.setFaceURL(faceUrl);
        info.setEx(ex);
        req.setUserInfo(info);
        OpenImApiUserRest.updateUserInfo(openimConfig,openImToken,req);
    }

    @Override
    public AccountCheckResp accountCheck(String userIds) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        AccountCheckReq req = new AccountCheckReq();
        req.getCheckUserIDs().addAll(Arrays.asList(userIds.split(",")));
        // req.setSecret(); 已在注册服务中填入 不需要再外层set
        return OpenImApiUserRest.accountCheck(openimConfig,openImToken,req).getData();
    }


    @Override
    public BatchSendMsgResp batchSendMsg(BatchSendMsgReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiMsgRest.batchSendMsg(openimConfig,openImToken,req).getData();
    }

    @Override
    public GetGroupsInfoResp getGroupInfo(GetGroupsInfoReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.getGroupInfo(openimConfig,openImToken,req).getData();
    }

    @Override
    public String importFriends(String ownerUserId,List<String> userIds) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        ImportFriendReq req = new ImportFriendReq();
        req.setOwnerUserID(ownerUserId);
        req.setFriendUserIDs(userIds);
        return OpenImApiFriendRest.importFriends(openimConfig,openImToken,req).getData();
    }

    @Override
    public String inviteUserToGroup(String groupId,List<String> userIds) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        InviteToGroup req = new InviteToGroup();
        req.setInvitedUserIDs(userIds);
        req.setGroupID(groupId);
        return OpenImApiGroupRest.inviteUserToGroup(openimConfig,openImToken,req).getData();
    }

    @Override
    public String inviteUserToGroupInteface(InviteToGroup req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.inviteUserToGroup(openimConfig,openImToken,req).getData();
    }

    @Override
    public GetPaginationUsersResp getUsers(GetPaginationUsersReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiUserRest.getUsers(openimConfig,openImToken,req).getData();
    }


    @Override
    public List<GetUsersOnlineStatusResp_SuccessResult> getUsersOnlineStatus(GetUsersOnlineStatusReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiUserRest.getUsersOnlineStatus(openimConfig,openImToken,req).getData();
    }

    @Override
    public GetPaginationFriendsResp getFriendList(GetPaginationFriendsReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiFriendRest.getFriendList(openimConfig,openImToken,req).getData();
    }

    @Override
    public String deleteFriend(DeleteFriendReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiFriendRest.deleteFriend(openimConfig,openImToken,req).getData();
    }


    @Override
    public GetGroupsResp getGroups(GetGroupsReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.getGroups(openimConfig,openImToken,req).getData();
    }

    @Override
    public CreateGroupResp createGroup(CreateGroupReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.createGroup(openimConfig,openImToken,req).getData();
    }

    @Override
    public String dismissGroup(DismissGroupReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.dismissGroup(openimConfig,openImToken,req).getData();
    }

    @Override
    public String setGroupInfo(SetGroupInfoReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.setGroupInfo(openimConfig,openImToken,req).getData();
    }

    @Override
    public String muteGroup(MuteGroupReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.muteGroup(openimConfig,openImToken,req).getData();
    }

    @Override
    public String cancelMuteGroup(CancelMuteGroupReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.cancelMuteGroup(openimConfig,openImToken,req).getData();
    }

    @Override
    public String muteGroupMember(MuteGroupMemberReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.muteGroupMember(openimConfig,openImToken,req).getData();
    }

    @Override
    public String cancelMuteGroupMember(CancelMuteGroupMemberReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.cancelMuteGroupMember(openimConfig,openImToken,req).getData();
    }

    @Override
    public GetGroupMemberListResp getGroupMemberList(GetGroupMemberListReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.getGroupMemberList(openimConfig,openImToken,req).getData();
    }

    @Override
    public String setGroupMemberInfo(SetGroupMemberInfoReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.setGroupMemberInfo(openimConfig,openImToken,req).getData();
    }

    @Override
    public String kickGroupMember(KickGroupMemberReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.kickGroupMember(openimConfig,openImToken,req).getData();
    }

    @Override
    public SearchMessageResp searchMsg(SearchMessageReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiMsgRest.searchMsg(openimConfig,openImToken,req).getData();
    }


    @Override
    public String revokeMsg(RevokeMsgReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiMsgRest.revokeMsg(openimConfig,openImToken,req).getData();
    }

    @Override
    public LogInfoResp searchLog(SearchLogsReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiThirdRest.searchLog(openimConfig,openImToken,req).getData();
    }

    @Override
    public SendMsgResp sendMessage(SendMsgReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiMsgRest.sendMessage(openimConfig,openImToken,req).getData();
    }

    @Override
    public AddNotificationAccountResp addNotificationAccount(AddNotificationAccountReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiUserRest.addNotificationAccount(openimConfig,openImToken,req).getData();
    }

    @Override
    public String updateNotificationAccountInfo(UpdateNotificationAccountInfoReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiUserRest.updateNotificationAccountInfo(openimConfig,openImToken,req).getData();
    }

    @Override
    public String transferGroupOwner(TransferGroupOwnerReq req) {
        OpenImToken openImToken = new OpenImToken();
        openImToken.setOperationId(UUID.randomUUID().toString());
        openImToken.setImToken(getAdminToken());
        return OpenImApiGroupRest.transferGroupOwner(openimConfig,openImToken,req).getData();
    }



}
