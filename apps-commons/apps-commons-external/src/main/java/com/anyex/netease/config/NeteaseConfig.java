package com.anyex.netease.config;

import com.anyex.apps.utils.EncryptUtils;
import com.anyex.netease.builder.AuthTokenBuilder;
import com.anyex.netease.builder.CheckSumBuilder;

import java.util.HashMap;
import java.util.Map;

public class NeteaseConfig {
    private static String appKey = "";
    private static String appSecret = "";
    static
    {
        appKey = "12622c433fae0a4141b39b7b268b48e2";
        appSecret = "6d0b1171c153";
    }

    public static String baseUrl = "https://api.netease.im/nimserver/"; //  网易云信服务器地址
    public static String baseSgUrl = "https://api-sg.netease.im/nimserver"; //  网易云信海外服务器地址

    public static String userCreateUrl = baseUrl+"user/create.action"; //  注册云信IM账号
    public static String userTokenUrl = baseUrl+"user/update.action"; //  刷新Token 指定token
    public static String userrefreshTokenUrl = baseUrl+"user/refreshToken.action"; //  刷新Token 不指定token
    public static String userBlockUrl = baseUrl+"user/block.action"; //  封禁账户
    public static String userMuteUrl = baseUrl+"user/mute.action"; //  账号全局禁言
    public static String userMuteModuleUrl = baseUrl+"user/muteModule.action"; //  账号功能模块禁言
    public static String usersetDonnopUrl = baseUrl+"user/setDonnop.action"; //  设置移动端是否需要推送(桌面端在线时)
    public static String userOnlineStatusUrl = baseUrl+"user/userOnlineStatus.action"; //  批量查询账号在线状态


    public static String chatroomCreateUrl = baseUrl+"chatroom/create.action"; //  创建聊天室

    public static String teamCreateUrl = baseUrl+"team/create.action"; //  创建高级群
    public static String teamAddUrl = baseUrl+"team/add.action"; //  拉人入群
    public static String teamaddManagerUrl = baseUrl+"team/addManager.action"; //  添加管理员
    public static String teamRemoveManagerUrl = baseUrl+"team/removeManager.action"; //  删除管理员
    public static String teamChangeOwnerUrl = baseUrl+"team/changeOwner.action"; //  转让群主
    public static String teamMuteTlistAllUrl = baseUrl+"team/muteTlistAll.action"; //  禁言群组
    public static String teamMuteTlistUrl = baseUrl+"team/muteTlist.action"; //  禁言指定群成员
    public static String teamKickUrl = baseUrl+"team/kick.action"; //  踢人出群
    public static String teamLeaveUrl = baseUrl+"team/leave.action"; //  主动退群
    public static String teamUpdateTeamNickUrl = baseUrl+"team/updateTeamNick.action"; //  修改群成员昵称
    public static String teamUpdateUrl = baseUrl+"team/update.action"; //  修改群组信息
    public static String teamMuteTeamUrl = baseUrl+"team/muteTeam.action"; //  设置群消息提醒开关
    public static String teamRemoveUrl = baseUrl+"team/remove.action"; //  解散群组
    public static String teamQueryDetailUrl = baseUrl+"team/queryDetail.action"; //  获取群组详细信息
    public static String teamListTeamMuteUrl = baseUrl+"team/listTeamMute.action"; //  获取群组禁言列表
    public static String teamGetMarkReadInfoMuteUrl = baseUrl+"team/getMarkReadInfo.action"; //  获取群消息已读未读详情
    public static String teamJoinTeamsUrl = baseUrl+"team/joinTeams.action"; //  获取用户已加入的群组信息
    public static String teamListMemberInfoUrl = baseUrl+"team/listMemberInfo.action"; //  获取用户已加入的群组的所有群成员信息
    public static String teamListOnlineUsersUrl = baseUrl+"team/listOnlineUsers.action";// 获取群组的在线成员列表
    public static String teamQueryUrl = baseUrl+"team/query.action";// 批量获取群组信息与成员列表
    public static String teamListOnlineUserCountUrl = baseUrl+"team/listOnlineUserCount.action";// 批量获取群组的在线成员数量

    public static String userInfosUrl = baseUrl+"user/getUinfos.action";// 获取用户名片
    public static String userUpdateInfosUrl = baseUrl+"user/updateUinfo.action";// 获取用户名片

    public static String friendAddUrl = baseUrl+"friend/add.action";// 好友关系管理-添加好友
    public static String friendUpdateUrl = baseUrl+"friend/update.action";// 好友关系管理-修改好友信息
    public static String friendDeleteUrl = baseUrl+"friend/delete.action";// 好友关系管理-删除好友
    public static String friendGetUrl = baseUrl+"friend/get.action";// 好友关系管理-获取好友列表
    public static String friendGetByAccidUrl = baseUrl+"friend/getByAccid.action";// 好友关系管理-获取好友关系

    public static String userSetSpecialRelationUrl = baseUrl+"user/setSpecialRelation.action";// 设置黑名单/静音
    public static String userListBlackAndMuteListUrl = baseUrl+"user/listBlackAndMuteList.action"; // 查看指定用户的黑名单和静音列表


    public static String sendAttachMsgUrl = baseUrl+"msg/sendAttachMsg.action"; // 发送自定义消息



    public static Map<String, String> getHeadder()
    {
        String nonce = System.currentTimeMillis()+"";
        String currTime = (System.currentTimeMillis()/1000)+"";
        Map<String, String> head = new HashMap<>();
        head.put("AppKey",appKey);
        head.put("Nonce",nonce);
        head.put("CurTime",currTime);
        head.put("CheckSum", CheckSumBuilder.getCheckSum(appSecret, nonce, currTime));
        head.put("Content-Type","application/x-www-form-urlencoded");
        head.put("charset","utf-8");
        return head;
    }

    public static String getAuthToken(String accid)
    {
        return AuthTokenBuilder.getToken(appKey,appSecret,accid);
    }

    public static String getAppSecret()
    {
        return EncryptUtils.desEncrypt(appSecret);
    }
}
