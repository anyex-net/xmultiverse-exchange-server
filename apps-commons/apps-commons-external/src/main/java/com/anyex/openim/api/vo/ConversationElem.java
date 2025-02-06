package com.anyex.openim.api.vo;

import lombok.Data;

@Data
public class ConversationElem {
    private String conversationID;
    private Integer recvMsgOpt;
    private Long unreadCount;
    private Boolean IsPinned;
    private MsgInfo msgInfo;
}
