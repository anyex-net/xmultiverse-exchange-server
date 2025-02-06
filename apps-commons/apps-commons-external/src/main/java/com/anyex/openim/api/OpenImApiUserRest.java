package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.user.req.*;
import com.anyex.openim.api.user.resp.*;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.base.OpenimConfig;
import com.anyex.openim.base.OpenimParams;
import com.anyex.openim.constants.ApiServerType;
import com.anyex.openim.utils.CommUtils;
import com.anyex.openim.utils.HttpRequestUtils;
import com.anyex.openim.utils.OpenimUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Open-IM-Server服务接口
 *
 * @author  anyex
 */

@Slf4j
public class OpenImApiUserRest {
    static {}
    /**
     * 一般是通过业务服务器（如chat服务器）完成用户的账号注册后，再注册到IM系统中，这样业务系统和IM系统完成账号打通。
     * routePath=/user/user_register
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> userRegister(OpenimConfig openimConfig,OpenImToken openImToken, UserRegisterReq req) {
//        ValidateUtils.notNull(users, "users is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/user_register");

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(OpenimParams.OPERATIONID, openImToken.getOperationId());

        if (StringUtils.isEmpty(req.getSecret())) {
            req.setSecret(openimConfig.getSecret());
        }
        String body = JSONUtil.toJsonStr(req);

        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, headerMap);

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----userRegister--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * Check if userID exists
     * routePath=/user/account_check
     *
     * @param req
     * @return
     */
    public static OpenImResult<AccountCheckResp> accountCheck(OpenimConfig openimConfig,OpenImToken openImToken, AccountCheckReq req) {
//        ValidateUtils.notNullForCoding(userInfo, "userInfo is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/account_check");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<AccountCheckResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<AccountCheckResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----updateUserInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 更新用户在IM中的资料，主要是头像、昵称
     * routePath=/user/update_user_info
     *
     * @param userInfo
     * @return
     */
    public static OpenImResult<String> updateUserInfo(OpenimConfig openimConfig,OpenImToken openImToken, UpdateUserInfoReq userInfo) {
//        ValidateUtils.notNullForCoding(userInfo, "userInfo is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/update_user_info");


        String body = JSONUtil.toJsonStr(userInfo);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----updateUserInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 更新用户在IM中的资料，主要是头像、昵称
     * routePath=/user/update_user_info_ex
     *
     * @param userInfo
     * @return
     */
    public static OpenImResult<String> updateUserInfoEx(OpenimConfig openimConfig,OpenImToken openImToken, UpdateUserInfoExReq userInfo) {
//        ValidateUtils.notNullForCoding(userInfo, "userInfo is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/update_user_info_ex");


        String body = JSONUtil.toJsonStr(userInfo);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----updateUserInfoEx--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 用户信息查询
     * routePath=/user/get_users_info
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetDesignateUsersResp> getUsersPublicInfo(OpenimConfig openimConfig,OpenImToken openImToken, GetDesignateUsersReq req) {
//        ValidateUtils.notNullForCoding(userInfo, "userInfo is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_users_info");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetDesignateUsersResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetDesignateUsersResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----updateUserInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 设置用户全局免打扰，所有消息的接收属于静默状态，无推送提醒。
     * Set user message receiving options
     * routePath=/user/set_global_msg_recv_opt
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> globalRecvMsgOpt(OpenimConfig openimConfig,OpenImToken openImToken, SetGlobalRecvMessageOptReq req) {
//        ValidateUtils.notNullForCoding(userId, "userInfo is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/set_global_msg_recv_opt");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----globalRecvMsgOpt--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * Turn the page (or specify userID, nickname) to pull user information Full field
     * routePath=/user/get_users
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetPaginationUsersResp> getUsers(OpenimConfig openimConfig,OpenImToken openImToken, GetPaginationUsersReq req) {
//        ValidateUtils.notNullForCoding(userId, "userInfo is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_users");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));
        OpenImResult<GetPaginationUsersResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetPaginationUsersResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getUsers--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * Get all user IDs
     * routePath=/user/get_all_users_uid
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetAllUserIDResp> getAllUsersID(OpenimConfig openimConfig,OpenImToken openImToken, GetAllUserIDReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_all_users_uid");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetAllUserIDResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetAllUserIDResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getAllUsersID--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * Get the online status of the user.
     * routePath=/user/get_users_status
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetUserStatusResp> getUsersStatus(OpenimConfig openimConfig,OpenImToken openImToken, GetUserStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_users_status");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetUserStatusResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetUserStatusResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getUsersStatus--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * Get the online status of subscribers
     * routePath=/user/get_subscribe_users_status
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetSubscribeUsersStatusResp> getSubscribeUsersStatus(OpenimConfig openimConfig,OpenImToken openImToken, GetSubscribeUsersStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_subscribe_users_status");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetSubscribeUsersStatusResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetSubscribeUsersStatusResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getSubscribeUsersStatus--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * Get user online status.
     * routePath=/user/get_users_online_status
     *
     * @param req
     * @return
     */
    public static OpenImResult<List<GetUsersOnlineStatusResp_SuccessResult>> getUsersOnlineStatus(OpenimConfig openimConfig,OpenImToken openImToken, GetUsersOnlineStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_users_online_status");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<List<GetUsersOnlineStatusResp_SuccessResult>>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<List<GetUsersOnlineStatusResp_SuccessResult>>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getUsersOnlineStatus--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * Get user online token details.
     * routePath=/user/get_users_online_token_detail
     *
     * @param req
     * @return
     */
    public static OpenImResult<List<SingleDetail>> getUsersOnlineTokenDetail(OpenimConfig openimConfig,OpenImToken openImToken, GetUsersOnlineStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/get_users_online_token_detail");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<List<SingleDetail>>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<List<SingleDetail>>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getUsersOnlineTokenDetail--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * subscriberStatus 订阅用户状态
     * Presence status of subscribed users.
     * routePath=/user/subscribe_users_status
     *
     * @param req
     * @return
     */
    public static OpenImResult<SubscribeOrCancelUsersStatusResp> subscriberStatus(OpenimConfig openimConfig,OpenImToken openImToken, SubscribeOrCancelUsersStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/subscribe_users_status");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<SubscribeOrCancelUsersStatusResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<SubscribeOrCancelUsersStatusResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----subscriberStatus--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * unsubscriberStatus 取消订阅用户状态
     * Unsubscribe a user's presence.
     * routePath=/user/unsubscribe_users_status
     *
     * @param req
     * @return
     */
    public static OpenImResult<SubscribeOrCancelUsersStatusResp> unsubscriberStatus(OpenimConfig openimConfig,OpenImToken openImToken, SubscribeOrCancelUsersStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/unsubscribe_users_status");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<SubscribeOrCancelUsersStatusResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<SubscribeOrCancelUsersStatusResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----unsubscriberStatus--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * add a general function for add
     * <p>
     * routePath=/user/process_user_command_add
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> processUserCommandAdd(OpenimConfig openimConfig,OpenImToken openImToken, ProcessUserCommandAddReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/process_user_command_add");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----processUserCommandAdd--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * add a general function for update
     * <p>
     * routePath=/user/process_user_command_update
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> processUserCommandUpdate(OpenimConfig openimConfig,OpenImToken openImToken, ProcessUserCommandUpdateReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/process_user_command_update");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----processUserCommandUpdate--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * add a general function for delete
     * <p>
     * routePath=/user/process_user_command_delete
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> processUserCommandDelete(OpenimConfig openimConfig,OpenImToken openImToken, ProcessUserCommandDeleteReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/process_user_command_delete");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----processUserCommandDelete--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * add a general function for get
     * <p>
     * routePath=/user/process_user_command_get
     *
     * @param req
     * @return
     */
    public static OpenImResult<ProcessUserCommandGetResp> processUserCommandGet(OpenimConfig openimConfig,OpenImToken openImToken, ProcessUserCommandGetReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/process_user_command_get");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<ProcessUserCommandGetResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<ProcessUserCommandGetResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----processUserCommandGet--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * add a general function for get
     * <p>
     * routePath=/user/process_user_command_get
     *
     * @param req
     * @return
     */
    public static OpenImResult<ProcessUserCommandGetAllResp> processUserCommandGetAll(OpenimConfig openimConfig,OpenImToken openImToken, ProcessUserCommandGetAllReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/process_user_command_get_all");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<ProcessUserCommandGetAllResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<ProcessUserCommandGetAllResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----processUserCommandGetAll--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * add a system notification account
     * <p>
     * routePath=/user/add_notification_account
     *
     * @param req
     * @return
     */
    public static OpenImResult<AddNotificationAccountResp> addNotificationAccount(OpenimConfig openimConfig,OpenImToken openImToken, AddNotificationAccountReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/add_notification_account");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<AddNotificationAccountResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<AddNotificationAccountResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----addNotificationAccount--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * update the system notification info
     * <p>
     * routePath=/user/update_notification_account
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> updateNotificationAccountInfo(OpenimConfig openimConfig,OpenImToken openImToken, UpdateNotificationAccountInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/update_notification_account");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----updateNotificationAccountInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * update the system notification info
     * <p>
     * routePath=/user/search_notification_account
     *
     * @param req
     * @return
     */
    public static OpenImResult<SearchNotificationAccountResp> searchNotificationAccount(OpenimConfig openimConfig,OpenImToken openImToken, SearchNotificationAccountReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/user/search_notification_account");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<SearchNotificationAccountResp>
                openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<SearchNotificationAccountResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----searchNotificationAccount--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
