package com.anyex.openim.api.vo;

import lombok.Data;

@Data
public class UserInfo {
    private String userID;
    private String nickname;
    private String faceURL;
    private String ex;
    private Long createTime;
    private Integer appMangerLevel;
    private Integer globalRecvMsgOpt;

    private boolean online = false;
}
