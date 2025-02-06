package com.anyex.apps.openim.chat.resp;

import com.anyex.apps.openim.chat.vo.AppletInfo;
import lombok.Data;

import java.util.List;

@Data
public class FindAppletResp {
    private List<AppletInfo> applets;
}
