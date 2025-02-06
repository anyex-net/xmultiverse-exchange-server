package com.anyex.apps.openim.admin.req;

import lombok.Data;

@Data
public class AddAdminAccountReq {
    private String account;
    private String password;
    private String faceURL;
    private String nickname;
}
