package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.msg.req.*;
import com.anyex.openim.api.msg.resp.*;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.base.OpenimConfig;
import com.anyex.openim.constants.ApiServerType;
import com.anyex.openim.utils.CommUtils;
import com.anyex.openim.utils.HttpRequestUtils;
import com.anyex.openim.utils.OpenimUtils;
import org.springframework.stereotype.Service;

/**
 * Open-IM-Server服务接口
 * routePath=/msg/*
 *
 * @author  anyex
 */
@Service
@Slf4j
public class OpenImApiMsgRest {

    static {}
    /**
     * 获取最小最大seq（包括用户的，以及指定群组的）
     * routePath=/msg/newest_seq
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetMaxSeqResp> getSeq(OpenimConfig openimConfig,OpenImToken openImToken, GetMaxSeqReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/newest_seq");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetMaxSeqResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetMaxSeqResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getSeq--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 解搜索消息
     * routePath=/msg/search_msg
     *
     * @param req
     * @return
     */
    public static OpenImResult<SearchMessageResp> searchMsg(OpenimConfig openimConfig,OpenImToken openImToken, SearchMessageReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/search_msg");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<SearchMessageResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<SearchMessageResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----SearchMessageResp--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 发送消息
     * routePath=/msg/send_msg
     *
     * @param req
     * @return
     */
    public static OpenImResult<SendMsgResp> sendMessage(OpenimConfig openimConfig,OpenImToken openImToken, SendMsgReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/send_msg");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<SendMsgResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<SendMsgResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----sendMessage--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 发送消息
     * routePath=/msg/batch_send_msg
     *
     * @param req
     * @return
     */
    public static OpenImResult<BatchSendMsgResp> batchSendMsg(OpenimConfig openimConfig,OpenImToken openImToken, BatchSendMsgReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/batch_send_msg");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<BatchSendMsgResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<BatchSendMsgResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----batchSendMsg--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 发送消息
     * routePath=/msg/send_business_notification
     *
     * @param req
     * @return
     */
    public static OpenImResult<SendMsgResp> sendBusinessNotification(OpenimConfig openimConfig,OpenImToken openImToken, SendBusinessNotificationReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/send_business_notification");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<SendMsgResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<SendMsgResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----sendBusinessNotification--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 拉取历史消息（包括用户的，以及指定群组的）
     * routePath=/msg/pull_msg_by_seq
     *
     * @param req
     * @return
     */
    public static OpenImResult<PullMessageBySeqsResp> pullMsgBySeqs(OpenimConfig openimConfig,OpenImToken openImToken, PullMessageBySeqsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/pull_msg_by_seq");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<PullMessageBySeqsResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<PullMessageBySeqsResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----pullMsgBySeqs--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * revokeMsg
     * routePath=/msg/revoke_msg
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> revokeMsg(OpenimConfig openimConfig,OpenImToken openImToken, RevokeMsgReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/revoke_msg");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----revokeMsg--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * markMsgsAsRead
     * routePath=/msg/mark_msgs_as_read
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> markMsgsAsRead(OpenimConfig openimConfig,OpenImToken openImToken, MarkMsgsAsReadReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/mark_msgs_as_read");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----markMsgsAsRead--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * markConversationAsRead
     * routePath=/msg/mark_conversation_as_read
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> markConversationAsRead(OpenimConfig openimConfig,OpenImToken openImToken, MarkConversationAsReadReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/mark_conversation_as_read");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----markConversationAsRead--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * getConversationsHasReadAndMaxSeq
     * routePath=/msg/get_conversations_has_read_and_max_seq
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> getConversationsHasReadAndMaxSeq(OpenimConfig openimConfig,OpenImToken openImToken, GetConversationsHasReadAndMaxSeqReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/get_conversations_has_read_and_max_seq");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getConversationsHasReadAndMaxSeq--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * setConversationHasReadSeq
     * routePath=/msg/set_conversation_has_read_seq
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> setConversationHasReadSeq(OpenimConfig openimConfig,OpenImToken openImToken, SetConversationHasReadSeqReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/set_conversation_has_read_seq");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----setConversationHasReadSeq--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 全量清空指定会话消息 重置min seq 比最大seq大1
     * routePath=/msg/clear_conversation_msg
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> clearConversationsMsg(OpenimConfig openimConfig,OpenImToken openImToken, ClearConversationsMsgReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/clear_conversation_msg");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----clearConversationsMsg--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 删除用户全部消息 重置min seq 比最大seq大1
     * routePath=/msg/user_clear_all_msg
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> userClearAllMsg(OpenimConfig openimConfig,OpenImToken openImToken, UserClearAllMsgReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/user_clear_all_msg");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----userClearAllMsg--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 用户标记删除部分消息by Seq
     * routePath=/msg/delete_msgs
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> deleteMsgs(OpenimConfig openimConfig,OpenImToken openImToken, DeleteMsgsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/delete_msgs");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----deleteMsgs--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * seq物理删除消息
     * routePath=/msg/delete_msg_phsical_by_seq
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> deleteMsgPhysicalBySeq(OpenimConfig openimConfig,OpenImToken openImToken, DeleteMsgPhysicalBySeqReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/delete_msg_phsical_by_seq");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----deleteMsgPhysicalBySeq--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 物理删除消息by 时间
     * routePath=/msg/delete_msg_physical
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> deleteMsgPhysical(OpenimConfig openimConfig,OpenImToken openImToken, DeleteMsgPhysicalReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/delete_msg_physical");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----deleteMsgPhysical--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取消息发送状态
     * routePath=/msg/check_msg_is_send_success
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetSendMsgStatusResp> checkMsgIsSendSuccess(OpenimConfig openimConfig,OpenImToken openImToken, GetSendMsgStatusReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/check_msg_is_send_success");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetSendMsgStatusResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetSendMsgStatusResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----checkMsgIsSendSuccess--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取服务器时间
     * routePath=/msg/get_server_time
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetServerTimeResp> getServerTime(OpenimConfig openimConfig,OpenImToken openImToken, GetServerTimeReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/msg/get_server_time");

        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetServerTimeResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetServerTimeResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getServerTime--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
