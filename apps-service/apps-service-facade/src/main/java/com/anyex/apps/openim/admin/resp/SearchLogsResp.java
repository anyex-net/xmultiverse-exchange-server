package com.anyex.apps.openim.admin.resp;

import com.anyex.apps.openim.admin.vo.LogInfo;
import lombok.Data;

import java.util.List;

@Data
public class SearchLogsResp {
    private List<LogInfo> LogsInfos;
    private Long total;
}
