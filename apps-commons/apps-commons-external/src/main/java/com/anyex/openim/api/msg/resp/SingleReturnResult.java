package com.anyex.openim.api.msg.resp;

import lombok.Data;

@Data
public class SingleReturnResult {
    private String serverMsgID;
    private String clientMsgID;
    private String sendTime;
    private String recvID;
}
