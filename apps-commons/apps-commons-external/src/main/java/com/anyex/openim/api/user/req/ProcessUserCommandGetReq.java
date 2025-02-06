package com.anyex.openim.api.user.req;

import lombok.Data;

@Data
public class ProcessUserCommandGetReq {
    private String userID;
    private Integer type;
}
