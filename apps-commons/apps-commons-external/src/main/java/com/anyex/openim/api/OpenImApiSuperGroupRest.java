package com.anyex.openim.api;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import com.anyex.openim.api.group.req.GetGroupAbstractInfoReq;
import com.anyex.openim.api.group.resp.GetGroupAbstractInfoResp;
import com.anyex.openim.api.superGroup.req.GetJoinedSuperGroupListReq;
import com.anyex.openim.api.superGroup.resp.GetJoinedSuperGroupListResp;
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
public class OpenImApiSuperGroupRest {
    static {}
    /**
     * 获取某个用户加入的超级群
     * routePath=/super_group/get_joined_group_list
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetJoinedSuperGroupListResp> getJoinedSuperGroupList(OpenimConfig openimConfig,OpenImToken openImToken, GetJoinedSuperGroupListReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/super_group/get_joined_group_list");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetJoinedSuperGroupListResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetJoinedSuperGroupListResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getJoinedSuperGroupList--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }

    /**
     * 获取群信息hash值
     * routePath=/super_group/get_groups_info
     *
     * @param req
     * @return
     */
    public static OpenImResult<GetGroupAbstractInfoResp> getGroupAbstractInfo(OpenimConfig openimConfig,OpenImToken openImToken, GetGroupAbstractInfoReq req) {
        long time = System.currentTimeMillis();
        //openimConfig.getApiUrl(SERVER_TYPE);
        String url = CommUtils.appendUrl(openimConfig.getApiApi(), "/super_group/get_groups_info");


        String body = JSONUtil.toJsonStr(req);
        HttpResponse exchanges = HttpRequestUtils.exchange(url, body, OpenimUtils.apiHeaderMap(openImToken));

        OpenImResult<GetGroupAbstractInfoResp> openImResult = JSONUtil.toBean(exchanges.body(), new TypeReference<OpenImResult<GetGroupAbstractInfoResp>>() {
        }, false);

        if (!openImResult.isOk()) {
            log.warn("----getGroupAbstractInfo--body={} time={} result={}", body, System.currentTimeMillis() - time, exchanges.body());
            throw new BusinessException(openImResult.getErrCode(),openImResult.getErrMsg());
        }

        return openImResult;
    }
}
