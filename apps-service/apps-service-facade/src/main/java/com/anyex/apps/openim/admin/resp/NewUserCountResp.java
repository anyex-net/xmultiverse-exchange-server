package com.anyex.apps.openim.admin.resp;

import lombok.Data;

import java.util.Map;

@Data
public class NewUserCountResp {
    private Long total;
    private Map<String, Long> date_count;
}
