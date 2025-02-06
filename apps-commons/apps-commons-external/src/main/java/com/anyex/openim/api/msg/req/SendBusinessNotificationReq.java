package com.anyex.openim.api.msg.req;

import lombok.Data;
import com.anyex.openim.api.msg.vo.SendMsg;

@Data
public class SendBusinessNotificationReq extends SendMsg {
    private String key;
    private String data;
    private String sendUserID;
    private String recvUserID;
}
