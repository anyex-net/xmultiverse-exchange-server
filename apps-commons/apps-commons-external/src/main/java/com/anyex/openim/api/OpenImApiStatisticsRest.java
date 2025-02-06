package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import com.anyex.openim.api.statistics.req.GetActiveGroupReq;
import com.anyex.openim.api.statistics.req.GetActiveUserReq;
import com.anyex.openim.api.statistics.req.GroupCreateCountReq;
import com.anyex.openim.api.statistics.resp.GetActiveGroupResp;
import com.anyex.openim.api.statistics.resp.GetActiveUserResp;
import com.anyex.openim.api.statistics.resp.GroupCreateCountResp;
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
public class OpenImApiStatisticsRest {
    static {}
    /**
     * Get the total number of users and the user increment within a specified time period
     * routePath=/statistics/user/register
     *
     * @param req
     * @return
     */
   /* public static OpenImResult<UserRegisterCountResp> userRegisterCount(OpenimConfig openimConfig,OpenImToken openImToken, UserRegisterCountReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/statistics/user/register");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<UserRegisterCountResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<UserRegisterCountResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----userRegisterCount--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }*/

    /**
     * getActiveUser
     * routePath=/statistics/user/active
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetActiveUserResp> getActiveUser(OpenimConfig openimConfig,OpenImToken openImToken, GetActiveUserReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/statistics/user/active");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetActiveUserResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetActiveUserResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getActiveUser--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * groupCreateCount
     * routePath=/statistics/group/create
     *
     * @param req
     * @return
     */
    public static OpenImResult<GroupCreateCountResp> groupCreateCount(OpenimConfig openimConfig,OpenImToken openImToken, GroupCreateCountReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/statistics/group/create");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GroupCreateCountResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GroupCreateCountResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----groupCreateCount--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * getActiveGroup
     * routePath=/statistics/group/active
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetActiveGroupResp> getActiveGroup(OpenimConfig openimConfig,OpenImToken openImToken, GetActiveGroupReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/statistics/group/active");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetActiveGroupResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetActiveGroupResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getActiveGroup--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
