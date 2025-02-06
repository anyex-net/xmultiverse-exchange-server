package com.anyex.openim.api.msg.req;

import com.anyex.openim.api.msg.vo.SendMsg;
import lombok.Data;

@Data
public class SendMsgReq extends SendMsg {
    private String recvID;
}
