package com.anyex.openim.api.user.req;

import lombok.Data;
import com.anyex.openim.api.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRegisterReq {
    private String secret;
    private List<UserInfo> users = new ArrayList<UserInfo>();
}
