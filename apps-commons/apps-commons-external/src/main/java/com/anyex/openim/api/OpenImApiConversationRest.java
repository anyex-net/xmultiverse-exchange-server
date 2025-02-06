package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import com.anyex.openim.api.conversation.req.*;
import com.anyex.openim.api.conversation.resp.*;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.base.OpenimConfig;
import com.anyex.openim.constants.ApiServerType;
import com.anyex.openim.utils.CommUtils;
import com.anyex.openim.utils.HttpRequestUtils;
import com.anyex.openim.utils.OpenimUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Open-IM-Server服务接口
 *
 * @author  anyex
 */
@Slf4j
public class OpenImApiConversationRest {
    static {}
    /**
     * getAllConversations
     * routePath=/conversation/get_all_conversations
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetAllConversationsResp> getAllConversations(OpenimConfig openimConfig,OpenImToken openImToken, GetAllConversationsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/conversation/get_all_conversations");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetAllConversationsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetAllConversationsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getAllConversations--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * getConversation
     * routePath=/conversation/get_conversation
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetConversationResp> getConversation(OpenimConfig openimConfig,OpenImToken openImToken, GetConversationReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/conversation/get_conversation");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetConversationResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetConversationResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getConversation--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * getConversations
     * routePath=/conversation/get_conversations
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetConversationsResp> getConversations(OpenimConfig openimConfig,OpenImToken openImToken, GetConversationsReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/conversation/get_conversations");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetConversationsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetConversationsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getConversations--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * setConversations
     * routePath=/conversation/set_conversations
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> setConversations(OpenimConfig openimConfig,OpenImToken openImToken, SetConversationsReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/conversation/set_conversations");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----setConversations--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * getConversationOfflinePushUserIDs
     * /conversation/get_conversation_offline_push_user_ids
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetConversationOfflinePushUserIDsResp> getConversationOfflinePushUserIDs(OpenimConfig openimConfig,OpenImToken openImToken, GetConversationOfflinePushUserIDsReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/conversation/get_conversation_offline_push_user_ids");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetConversationOfflinePushUserIDsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetConversationOfflinePushUserIDsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getConversationOfflinePushUserIDs--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }


    /**
     * getSortedConversationList
     * routePath=/conversation/get_sorted_conversation_list
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetSortedConversationListResp> getSortedConversationList(OpenimConfig openimConfig,OpenImToken openImToken, GetSortedConversationListReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/conversation/get_sorted_conversation_list");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetSortedConversationListResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetSortedConversationListResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getSortedConversationList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
