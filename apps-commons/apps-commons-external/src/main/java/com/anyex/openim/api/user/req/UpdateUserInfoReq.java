package com.anyex.openim.api.user.req;


import lombok.Data;
import com.anyex.openim.api.vo.UserInfo;

@Data
public class UpdateUserInfoReq {
    private UserInfo userInfo;
}
