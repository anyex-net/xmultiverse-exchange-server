package com.anyex.apps.openim.admin.applet.resp;

import com.anyex.apps.openim.chat.vo.AppletInfo;
import lombok.Data;

import java.util.List;

@Data
public class SearchAppletResp {
    private Integer total;
    private List<AppletInfo> applets;
}
