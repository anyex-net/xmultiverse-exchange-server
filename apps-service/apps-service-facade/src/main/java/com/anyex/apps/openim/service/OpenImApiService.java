package com.anyex.apps.openim.service;

import com.anyex.openim.api.OpenImApiGroupRest;
import com.anyex.openim.api.auth.req.GetUserTokenReq;
import com.anyex.openim.api.conversation.resp.GetSortedConversationListResp;
import com.anyex.openim.api.friend.req.DeleteFriendReq;
import com.anyex.openim.api.friend.req.GetPaginationFriendsReq;
import com.anyex.openim.api.friend.resp.GetPaginationFriendsResp;
import com.anyex.openim.api.group.req.*;
import com.anyex.openim.api.group.resp.CreateGroupResp;
import com.anyex.openim.api.group.resp.GetGroupMemberListResp;
import com.anyex.openim.api.group.resp.GetGroupsInfoResp;
import com.anyex.openim.api.group.resp.GetGroupsResp;
import com.anyex.openim.api.msg.req.BatchSendMsgReq;
import com.anyex.openim.api.msg.req.RevokeMsgReq;
import com.anyex.openim.api.msg.req.SearchMessageReq;
import com.anyex.openim.api.msg.req.SendMsgReq;
import com.anyex.openim.api.msg.resp.BatchSendMsgResp;
import com.anyex.openim.api.msg.resp.PullMessageBySeqsResp;
import com.anyex.openim.api.msg.resp.SearchMessageResp;
import com.anyex.openim.api.msg.resp.SendMsgResp;
import com.anyex.openim.api.third.req.SearchLogsReq;
import com.anyex.openim.api.third.resp.LogInfoResp;
import com.anyex.openim.api.user.req.AddNotificationAccountReq;
import com.anyex.openim.api.user.req.GetPaginationUsersReq;
import com.anyex.openim.api.user.req.GetUsersOnlineStatusReq;
import com.anyex.openim.api.user.req.UpdateNotificationAccountInfoReq;
import com.anyex.openim.api.user.resp.AccountCheckResp;
import com.anyex.openim.api.user.resp.AddNotificationAccountResp;
import com.anyex.openim.api.user.resp.GetPaginationUsersResp;
import com.anyex.openim.api.user.resp.GetUsersOnlineStatusResp_SuccessResult;
import com.anyex.openim.api.vo.UserInfo;
import com.anyex.openim.base.OpenImToken;

import java.util.List;
import java.util.UUID;

public interface OpenImApiService {

    String getAdminToken();

    GetSortedConversationListResp getConversation(String userId, String conversationId);
    PullMessageBySeqsResp getHistoryMsg(String userId, String conversationId);

    String getImToken(GetUserTokenReq req);

    String registerUser(UserInfo req);

    void forceLogout(Integer platform,String userId);

    void updateUserInfoReq(String userId,String nickName,String faceUrl,String ex);

    AccountCheckResp accountCheck(String userIds);

    BatchSendMsgResp batchSendMsg(BatchSendMsgReq req);

    GetGroupsInfoResp getGroupInfo(GetGroupsInfoReq req);

    String importFriends(String ownerUserId,List<String> userIds);

    String inviteUserToGroup(String groupId,List<String> userIds);

    String inviteUserToGroupInteface(InviteToGroup req);

    GetPaginationUsersResp getUsers(GetPaginationUsersReq req);

    List<GetUsersOnlineStatusResp_SuccessResult> getUsersOnlineStatus(GetUsersOnlineStatusReq req);

    GetPaginationFriendsResp getFriendList(GetPaginationFriendsReq req);

    String deleteFriend(DeleteFriendReq req);

    GetGroupsResp getGroups(GetGroupsReq req);

    CreateGroupResp createGroup(CreateGroupReq req);

    String dismissGroup(DismissGroupReq req);

    String setGroupInfo(SetGroupInfoReq req);

    String muteGroup(MuteGroupReq req) ;

    String cancelMuteGroup(CancelMuteGroupReq req) ;

    String muteGroupMember(MuteGroupMemberReq req) ;

    String cancelMuteGroupMember(CancelMuteGroupMemberReq req) ;

    GetGroupMemberListResp getGroupMemberList(GetGroupMemberListReq req);

    String setGroupMemberInfo(SetGroupMemberInfoReq req);

    String kickGroupMember(KickGroupMemberReq req);

    SearchMessageResp searchMsg(SearchMessageReq req);

    String revokeMsg(RevokeMsgReq req);

    LogInfoResp searchLog(SearchLogsReq req);

    SendMsgResp sendMessage(SendMsgReq req);

    AddNotificationAccountResp addNotificationAccount(AddNotificationAccountReq req);

    String updateNotificationAccountInfo(UpdateNotificationAccountInfoReq req);

    String transferGroupOwner(TransferGroupOwnerReq req);
}
