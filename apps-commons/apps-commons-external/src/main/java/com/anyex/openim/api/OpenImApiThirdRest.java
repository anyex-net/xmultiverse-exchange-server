package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.third.req.*;
import com.anyex.openim.api.third.resp.*;
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
public class OpenImApiThirdRest {
    static {}
    /**
     * fcmUpdateToken
     * routePath=/third/fcm_update_token
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> fcmUpdateToken(OpenimConfig openimConfig,OpenImToken openImToken, FcmUpdateTokenReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/fcm_update_token");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----fcmUpdateToken--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * setAppBadge
     * routePath=/third/set_app_badge
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> setAppBadge(OpenimConfig openimConfig,OpenImToken openImToken, SetAppBadgeReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/set_app_badge");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----setAppBadge--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * routePath=/third/object/part_limit
     *
     * @param req
     * @return
     */
    public static OpenImResult<PartLimitResp> partLimit(OpenimConfig openimConfig,OpenImToken openImToken, PartLimitReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/part_limit");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<PartLimitResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<PartLimitResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----partLimit--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * routePath=/third/object/part_size
     *
     * @param req
     * @return
     */
    public static OpenImResult<PartSizeResp> partSize(OpenimConfig openimConfig,OpenImToken openImToken, PartSizeReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/part_size");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<PartSizeResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<PartSizeResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----partSize--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * initiateMultipartUpload
     * routePath=/third/object/initiate_multipart_upload
     *
     * @param req
     * @return
     */
    public static OpenImResult<InitiateMultipartUploadResp> initiateMultipartUpload(OpenimConfig openimConfig,OpenImToken openImToken, InitiateMultipartUploadReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/initiate_multipart_upload");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<InitiateMultipartUploadResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<InitiateMultipartUploadResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----initiateMultipartUpload--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * initiateFormData
     * routePath=/third/object/initiate_form_data
     *
     * @param req
     * @return
     */
    public static OpenImResult<InitiateFormDataResp> initiateFormData(OpenimConfig openimConfig,OpenImToken openImToken, InitiateFormDataReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/initiate_form_data");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<InitiateFormDataResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<InitiateFormDataResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----initiateFormData--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * completeFormData
     * routePath=/third/object/complete_form_data
     *
     * @param req
     * @return
     */
    public static OpenImResult<CompleteFormDataResp> completeFormData(OpenimConfig openimConfig,OpenImToken openImToken, CompleteFormDataReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/complete_form_data");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<CompleteFormDataResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<CompleteFormDataResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----completeFormData--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }



    /**
     * authSign
     * routePath=/third/object/auth_sign
     *
     * @param req
     * @return
     */
    public static OpenImResult<AuthSignResp> authSign(OpenimConfig openimConfig,OpenImToken openImToken, AuthSignReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/auth_sign");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<AuthSignResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<AuthSignResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----authSign--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * completeMultipartUpload
     * routePath=/third/object/complete_multipart_upload
     *
     * @param req
     * @return
     */
    public static OpenImResult<CompleteMultipartUploadResp> completeMultipartUpload(OpenimConfig openimConfig,OpenImToken openImToken, CompleteMultipartUploadReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/complete_multipart_upload");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<CompleteMultipartUploadResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<CompleteMultipartUploadResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----completeMultipartUpload--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * accessURL
     * routePath=/third/object/access_url
     *
     * @param req
     * @return
     */
    public static OpenImResult<AccessURLResp> accessURL(OpenimConfig openimConfig,OpenImToken openImToken, AccessURLReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/object/access_url");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<AccessURLResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<AccessURLResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----accessURL--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    public static OpenImResult<LogInfoResp> searchLog(OpenimConfig openimConfig,OpenImToken openImToken, SearchLogsReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/third/logs/search");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<LogInfoResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<LogInfoResp>>() {
        }, false);
        log.warn("----searchLog--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
        if (!openImResult.isOk()) {
            log.warn("----searchLog--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
