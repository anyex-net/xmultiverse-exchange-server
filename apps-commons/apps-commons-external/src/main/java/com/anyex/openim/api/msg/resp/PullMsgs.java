package com.anyex.openim.api.msg.resp;

import lombok.Data;
import com.anyex.openim.api.msg.vo.MsgData;

import java.util.List;

@Data
public class PullMsgs {
    private List<MsgData> Msgs;
    private Boolean isEnd;

}
