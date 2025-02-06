package com.anyex.openim;

import com.anyex.openim.api.OpenImApiSuperGroupRest;
import com.anyex.openim.api.group.req.GetGroupAbstractInfoReq;
import com.anyex.openim.api.group.resp.GetGroupAbstractInfoResp;
import com.anyex.openim.api.superGroup.req.GetJoinedSuperGroupListReq;
import com.anyex.openim.api.superGroup.resp.GetJoinedSuperGroupListResp;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.utils.ValidatorUtils;


public class OpenImApiSuperGroupService {

    private OpenImApiSuperGroupRest openImApiSuperGroupRest = new OpenImApiSuperGroupRest();

    /**
     * 获取某个用户加入的超级群
     * routePath=/super_group/get_joined_group_list
     *
     * @param req
     * @return
     */
    public OpenImResult<GetJoinedSuperGroupListResp> getJoinedSuperGroupList(OpenImToken openImToken, GetJoinedSuperGroupListReq req) {
        ValidatorUtils.validate(req);
        return openImApiSuperGroupRest.getJoinedSuperGroupList(openImToken, req);
    }

    /**
     * 获取群信息hash值
     * routePath=/super_group/get_groups_info
     *
     * @param req
     * @return
     */
    public OpenImResult<GetGroupAbstractInfoResp> getGroupAbstractInfo(OpenImToken openImToken, GetGroupAbstractInfoReq req) {
        ValidatorUtils.validate(req);
        return openImApiSuperGroupRest.getGroupAbstractInfo(openImToken, req);
    }

}
