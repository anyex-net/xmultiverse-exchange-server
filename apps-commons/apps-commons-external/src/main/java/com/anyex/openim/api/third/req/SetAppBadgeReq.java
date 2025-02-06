package com.anyex.openim.api.third.req;

import lombok.Data;

@Data
public class SetAppBadgeReq {
    private String userID;
    private Integer appUnreadCount;
}
