package com.anyex.openim.api.user.resp;

import lombok.Data;

@Data
public class AccountCheckRespSingleUserStatus {
    private String userID;
    private String accountStatus;
}
