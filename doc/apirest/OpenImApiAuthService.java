package com.anyex.openim;

import com.anyex.openim.api.OpenImApiAuthRest;
import com.anyex.openim.api.auth.req.ForceLogoutReq;
import com.anyex.openim.api.auth.req.GetUserTokenReq;
import com.anyex.openim.api.auth.req.ParseTokenReq;
import com.anyex.openim.api.auth.req.UserTokenReq;
import com.anyex.openim.api.auth.resp.ParseTokenResp;
import com.anyex.openim.api.auth.resp.UserTokenResp;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.utils.ValidatorUtils;


public class OpenImApiAuthService {

    private OpenImApiAuthRest openImApiAuthRest = new OpenImApiAuthRest();

    /**
     * 生成token
     * routePath=/auth/user_token
     *
     * @param req
     * @return
     */
    public OpenImResult<UserTokenResp> userToken(OpenImToken openImToken, UserTokenReq req) {
        ValidatorUtils.validate(req);
        return openImApiAuthRest.userToken(openImToken, req);
    }

    /**
     * 管理员获取用户 token
     * routePath=/auth/get_user_token
     *
     * @param req
     * @return
     */
    public OpenImResult<GetUserTokenReq> getUserToken(OpenImToken openImToken, GetUserTokenReq req){
        ValidatorUtils.validate(req);
        return openImApiAuthRest.getUserToken(openImToken, req);
    }

    /**
     * 解析token
     * routePath=/auth/parse_token
     *
     * @param req
     * @return
     */
    public OpenImResult<ParseTokenResp> parseToken(OpenImToken openImToken, ParseTokenReq req) {
        ValidatorUtils.validate(req);
        return openImApiAuthRest.parseToken(openImToken, req);
    }

    /**
     * 强制退出登录
     * routePath=/auth/force_logout
     *
     * @param req
     * @return
     */
    public OpenImResult<String> forceLogout(OpenImToken openImToken, ForceLogoutReq req) {
        ValidatorUtils.validate(req);
        return openImApiAuthRest.forceLogout(openImToken, req);
    }
}
