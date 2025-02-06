package com.anyex.apps.openim.chat.account.resp;

import lombok.Data;

@Data
public class UserRegisterResp {
    private String imToken;
    private String chatToken;
    private String userID;
}
