package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.group.req.*;
import com.anyex.openim.api.group.resp.*;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.base.OpenimConfig;
import com.anyex.openim.constants.ApiServerType;
import com.anyex.openim.utils.CommUtils;
import com.anyex.openim.utils.HttpRequestUtils;
import com.anyex.openim.utils.OpenimUtils;

/**
 * Open-IM-Server服务接口
 *
 * @author  anyex
 */

@Slf4j
public class OpenImApiGroupRest {
    static {}
    /**
     * 创建群
     *
     * @param req
     * @return
     */
    public static OpenImResult<CreateGroupResp> createGroup(OpenimConfig openimConfig,OpenImToken openImToken, CreateGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/create_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<CreateGroupResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<CreateGroupResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----createGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 设置群信息
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> setGroupInfo(OpenimConfig openimConfig,OpenImToken openImToken, SetGroupInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/set_group_info");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----setGroupInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取指定群信息
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupsInfoResp> getGroupInfo(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupsInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_groups_info");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupsInfoResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupsInfoResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getGroupInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * （以管理员或群主身份）获取群的加群申请
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupApplicationListResp> getRecvGroupApplicationList(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupApplicationListReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_recv_group_applicationList");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupApplicationListResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupApplicationListResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getRecvGroupApplicationList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取用户自己的主动加群申请
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetUserReqApplicationListResp> getUserReqApplicationList(OpenimConfig openimConfig,OpenImToken openImToken, GetUserReqApplicationListReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_user_req_group_applicationList");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetUserReqApplicationListResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetUserReqApplicationListResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getUserReqApplicationList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取该群用户的加群申请
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupUsersReqApplicationListResp> getGroupUsersReqApplicationList(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupUsersReqApplicationListReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_group_users_req_application_list");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupUsersReqApplicationListResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupUsersReqApplicationListResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getGroupUsersReqApplicationList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 设置群成员昵称
     * 设置群成员信息
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> setGroupMemberInfo(OpenimConfig openimConfig,OpenImToken openImToken, SetGroupMemberInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/set_group_member_info");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----setGroupMemberInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取群信息hash值
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupAbstractInfoResp> getGroupAbstractInfo(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupAbstractInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_group_abstract_info");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupAbstractInfoResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupAbstractInfoResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----setGroupMemberInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 申请加群
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> joinGroup(OpenimConfig openimConfig,OpenImToken openImToken, JoinGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/join_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----joinGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 退出群
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> quitGroup(OpenimConfig openimConfig,OpenImToken openImToken, QuitGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/quit_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----quitGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 踢出群
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> kickGroupMember(OpenimConfig openimConfig,OpenImToken openImToken, KickGroupMemberReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/kick_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----quitGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 解散群
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> dismissGroup(OpenimConfig openimConfig,OpenImToken openImToken, DismissGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/dismiss_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----dismissGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 对某个群成员禁言
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> muteGroupMember(OpenimConfig openimConfig,OpenImToken openImToken, MuteGroupMemberReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/mute_group_member");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----muteGroupMember--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 对某个群成员取消禁言
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> cancelMuteGroupMember(OpenimConfig openimConfig,OpenImToken openImToken, CancelMuteGroupMemberReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/cancel_mute_group_member");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----cancelMuteGroupMember--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 对某个群禁言
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> muteGroup(OpenimConfig openimConfig,OpenImToken openImToken, MuteGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/mute_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----muteGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 对某个群取消禁言
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> cancelMuteGroup(OpenimConfig openimConfig,OpenImToken openImToken, CancelMuteGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/cancel_mute_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----muteGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * 群主或管理员处理进群申请
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> applicationGroupResponse(OpenimConfig openimConfig,OpenImToken openImToken, GroupApplicationResponseReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/group_application_response");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----quitGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取某个群的群成员
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupMemberListResp> getGroupMemberList(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupMemberListReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_group_member_list");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupMemberListResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupMemberListResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----quitGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取某个群的指定群成员
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupMembersInfoResp> getGroupMembersInfo(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupMembersInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_group_members_info");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupMembersInfoResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupMembersInfoResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getGroupMembersInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 转让群主
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> transferGroupOwner(OpenimConfig openimConfig,OpenImToken openImToken, TransferGroupOwnerReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/transfer_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----transferGroupOwner--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 转让群主
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupsResp> getGroups(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_groups");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupsResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getGroups--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取群成员用户ID
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupMemberUserIDsResp> getGroupMemberUserIDs(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupMemberUserIDsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/get_groups");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupMemberUserIDsResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupMemberUserIDsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getGroupMemberUserIDs--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    public static OpenImResult<String> inviteUserToGroup(OpenimConfig openimConfig,OpenImToken openImToken,InviteToGroup req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/group/invite_user_to_group");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----inviteUserToGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
