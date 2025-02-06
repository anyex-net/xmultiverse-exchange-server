package com.anyex.openim.api.msg.req;

import lombok.Data;
import com.anyex.openim.api.msg.vo.SendMsg;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BatchSendMsgReq extends SendMsg {
    private Boolean isSendAll;
    @NotEmpty
    private List<String> recvIDs;
}
