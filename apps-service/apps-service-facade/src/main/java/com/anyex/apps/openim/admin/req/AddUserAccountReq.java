package com.anyex.apps.openim.admin.req;

import com.anyex.apps.openim.chat.account.req.RegisterUserInfo;
import lombok.Data;

@Data
public class AddUserAccountReq {
    private String ip;
    private String deviceID;
    private Integer platform;
    private RegisterUserInfo user;
}
