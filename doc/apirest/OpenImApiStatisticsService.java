package com.anyex.openim;

import com.anyex.openim.api.OpenImApiStatisticsRest;
import com.anyex.openim.api.statistics.req.GetActiveUserReq;
import com.anyex.openim.api.statistics.req.GroupCreateCountReq;
import com.anyex.openim.api.statistics.resp.GetActiveUserResp;
import com.anyex.openim.api.statistics.resp.GroupCreateCountResp;
import com.anyex.openim.api.statistics.resp.UserRegisterCountResp;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.utils.ValidatorUtils;


public class OpenImApiStatisticsService {

    private OpenImApiStatisticsRest openImApiStatisticsRest = new OpenImApiStatisticsRest();

    /**
     * Get the total number of users and the user increment within a specified time period
     * routePath=/statistics/user/register
     *
     * @param req
     * @return
     */
   /* public OpenImResult<UserRegisterCountResp> userRegisterCount(OpenImToken openImToken, UserRegisterCountReq req) {
        ValidatorUtils.validate(req);
        return openImApiStatisticsRest.userRegisterCount(openImToken, req);
    }*/

    /**
     * getActiveUser
     * routePath=/statistics/user/active
     *
     * @param req
     * @return
     */
    public OpenImResult<GetActiveUserResp> getActiveUser(OpenImToken openImToken, GetActiveUserReq req) {
        ValidatorUtils.validate(req);
        return openImApiStatisticsRest.getActiveUser(openImToken, req);
    }

    /**
     * groupCreateCount
     * routePath=/statistics/group/create
     *
     * @param req
     * @return
     */
    public OpenImResult<GroupCreateCountResp> groupCreateCount(OpenImToken openImToken, GroupCreateCountReq req) {
        ValidatorUtils.validate(req);
        return openImApiStatisticsRest.groupCreateCount(openImToken, req);
    }
}
