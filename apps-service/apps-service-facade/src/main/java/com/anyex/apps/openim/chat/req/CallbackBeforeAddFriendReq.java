package com.anyex.apps.openim.chat.req;

import lombok.Data;

@Data
public class CallbackBeforeAddFriendReq {
    private String fromUserID;
    private String toUserID;
    private String reqMsg;
    private String reqoperationIDMsg;
}
