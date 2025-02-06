package com.anyex.openim.api.msg.resp;

import lombok.Data;

import java.util.List;

@Data
public class SearchMessageResp {
    private List<ChatLog> chatLogs;
    private Integer chatLogsNum;
}
