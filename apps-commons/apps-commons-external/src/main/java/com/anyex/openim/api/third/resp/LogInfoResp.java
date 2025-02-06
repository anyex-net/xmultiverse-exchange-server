package com.anyex.openim.api.third.resp;

import lombok.Data;

import java.util.List;

@Data
public class LogInfoResp {
    private List<LogInfo> logsInfos;
    private Long total;

    @Data
    public class LogInfo {

        private String userID;
        private Integer platform;
        private String url;
        private Long createTime;
        private String nickname;
        private String logID;
        private String filename;
        private String systemType;
        private String ex;
        private String version;
    }

}
