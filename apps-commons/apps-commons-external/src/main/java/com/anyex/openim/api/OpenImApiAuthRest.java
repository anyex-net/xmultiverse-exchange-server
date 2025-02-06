package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.openim.api.auth.resp.GetUserTokenResp;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.auth.req.ForceLogoutReq;
import com.anyex.openim.api.auth.req.GetUserTokenReq;
import com.anyex.openim.api.auth.req.ParseTokenReq;
import com.anyex.openim.api.auth.req.UserTokenReq;
import com.anyex.openim.api.auth.resp.ParseTokenResp;
import com.anyex.openim.api.auth.resp.UserTokenResp;
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
public class OpenImApiAuthRest {
    
    static {}


    /**
     * 生成token
     * routePath=/auth/user_token
     *
     * @param req
     * @return
     */
    public static OpenImResult<UserTokenResp> userToken(OpenimConfig openimConfig, OpenImToken openImToken, UserTokenReq req) {
        long time = System.currentTimeMillis();
       // //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/auth/user_token");

        if (StringUtils.isEmpty(req.getSecret())) {
            req.setSecret(openimConfig.getSecret());
        }
        if (req.getPlatformID() == null) {
            req.setPlatformID(openimConfig.getPlatformId());
        }
        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<UserTokenResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<UserTokenResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----userToken--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 解析token
     * routePath=/auth/parse_token
     *
     * @param req
     * @return
     */
    public static OpenImResult<ParseTokenResp> parseToken(OpenimConfig openimConfig,OpenImToken openImToken, ParseTokenReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/auth/parse_token");




        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<ParseTokenResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<ParseTokenResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----parseToken--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 管理员获取用户 token
     * routePath=/auth/get_user_token
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetUserTokenResp> getUserToken(OpenimConfig openimConfig,OpenImToken openImToken, GetUserTokenReq req) {
//        ValidateUtils.notNull(token, "token is null");
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/auth/get_user_token");




        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetUserTokenResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetUserTokenResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getUserToken--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 强制退出登录
     * routePath=/auth/force_logout
     *
     * @param req
     * @return
     */
    public static OpenImResult<String> forceLogout(OpenimConfig openimConfig,OpenImToken openImToken, ForceLogoutReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/auth/force_logout");


        if (req.getPlatformID() == null) {
            req.setPlatformID(openimConfig.getPlatformId());
        }
        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<String> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<String>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----forceLogout--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
