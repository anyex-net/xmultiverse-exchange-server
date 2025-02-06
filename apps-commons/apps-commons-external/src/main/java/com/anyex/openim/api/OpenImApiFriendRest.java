package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.friend.req.*;
import com.anyex.openim.api.friend.resp.*;
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
public class OpenImApiFriendRest {
    static {}
    /**
     * 删除好友
     * routePath=/friend/delete_friend
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> deleteFriend(OpenimConfig openimConfig,OpenImToken openImToken, DeleteFriendReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/delete_friend");


        String body = JSONUtil.toJsonStr(req);
        log.info("Delete friend request {}", body);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);
        log.info("Delete friend respose {}", JSONObject.toJSONString(openImResult));
        if (!openImResult.isOk()) {
            log.warn("----deleteFriend--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 申请加好友
     * routePath=/friend/add_friend
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> addFriend(OpenimConfig openimConfig,OpenImToken openImToken, ApplyToAddFriendReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/add_friend");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----addFriend--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 导入好友关系
     * routePath=/friend/import_friend
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> importFriends(OpenimConfig openimConfig,OpenImToken openImToken, ImportFriendReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/import_friend");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----addFriend--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 对好友申请响应（同意或拒绝）
     * routePath=/friend/add_friend_response
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> respondFriendApply(OpenimConfig openimConfig,OpenImToken openImToken, RespondFriendApplyReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/add_friend_response");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----respondFriendApply--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * 对好友申请响应（同意或拒绝）
     * routePath=/friend/set_friend_remark
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> setFriendRemark(OpenimConfig openimConfig,OpenImToken openImToken, SetFriendRemarkReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/set_friend_remark");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----respondFriendApply--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 星标好友
     * routePath=/friend/update_friends
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> updateFriends(OpenimConfig openimConfig,OpenImToken openImToken, UpdateFriendsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/update_friends");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----updateFriends--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取收到的好友申请列表
     * routePath=/friend/get_friend_apply_list
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetPaginationFriendsApplyToResp> getFriendApplyList(OpenimConfig openimConfig,OpenImToken openImToken, GetPaginationFriendsApplyToReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_friend_apply_list");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetPaginationFriendsApplyToResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetPaginationFriendsApplyToResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getFriendApplyList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取指定好友申请
     * routePath=/friend/get_designated_friend_apply
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetDesignatedFriendsApplyResp> getDesignatedFriendsApply(OpenimConfig openimConfig,OpenImToken openImToken, GetDesignatedFriendsApplyReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_designated_friend_apply");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetDesignatedFriendsApplyResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetDesignatedFriendsApplyResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getDesignatedFriendsApply--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取主动发出去的好友申请列表
     * routePath=/friend/get_self_friend_apply_list
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetPaginationFriendsApplyFromResp> getSelfApplyList(OpenimConfig openimConfig,OpenImToken openImToken, GetPaginationFriendsApplyFromReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_self_friend_apply_list");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetPaginationFriendsApplyFromResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetPaginationFriendsApplyFromResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getSelfApplyList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取指定好友信息 有id不存在也返回错误
     * routePath=/friend/get_friend_list
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetPaginationFriendsResp> getFriendList(OpenimConfig openimConfig,OpenImToken openImToken, GetPaginationFriendsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_friend_list");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetPaginationFriendsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetPaginationFriendsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getFriendList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 翻页获取好友列表 无结果不返回错误
     * routePath=/friend/get_designated_friends
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetDesignatedFriendsResp> getDesignatedFriends(OpenimConfig openimConfig,OpenImToken openImToken, GetDesignatedFriendsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_designated_friends");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetDesignatedFriendsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetDesignatedFriendsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getFriendList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 添加黑名单
     * routePath=/friend/add_black
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> addBlack(OpenimConfig openimConfig,OpenImToken openImToken, AddBlackReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/add_black");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----addBlack--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * 获取黑名单列表
     * routePath=/friend/get_black_list
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetPaginationBlacksResp> getPaginationBlacks(OpenimConfig openimConfig,OpenImToken openImToken, GetPaginationBlacksReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_black_list");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetPaginationBlacksResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetPaginationBlacksResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getPaginationBlacks--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * 移除黑名单
     * routePath=/friend/remove_black
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> removeBlack(OpenimConfig openimConfig,OpenImToken openImToken, RemoveBlackReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/remove_black");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----removeBlack--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 判断是否好友关系
     * routePath=/friend/is_friend
     *
     * @param req
     * @return
     */
    public static OpenImResult<IsFriendResp> isFriend(OpenimConfig openimConfig,OpenImToken openImToken, IsFriendReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/is_friend");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<IsFriendResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<IsFriendResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----isFriend--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 判断是否在黑名单中(没有接口)
     * routePath=/friend/is_black
     *
     * @param req
     * @return
     * @deprecated
     */
    public static OpenImResult<IsBlackResp> isBlack(OpenimConfig openimConfig,OpenImToken openImToken, IsBlackReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/is_black");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<IsBlackResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<IsBlackResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----isBlack--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取好友ID列表
     * routePath=/friend/get_friend_id
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetFriendIDsResp> getFriendIDs(OpenimConfig openimConfig,OpenImToken openImToken, GetFriendIDsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_friend_id");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetFriendIDsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetFriendIDsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getFriendIDs--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取指定好友信息
     * routePath=/friend/get_specified_friends_info
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetSpecifiedFriendsInfoResp> getSpecifiedFriendsInfo(OpenimConfig openimConfig,OpenImToken openImToken, GetSpecifiedFriendsInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/friend/get_specified_friends_info");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetSpecifiedFriendsInfoResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetSpecifiedFriendsInfoResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getSpecifiedFriendsInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


}
