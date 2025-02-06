package com.anyex.apps.openim.admin.forbidden.resp;

import com.anyex.apps.openim.chat.vo.UserPublicInfo;
import lombok.Data;

@Data
public class LimitUserLoginIP {
    private String userID;
    private String ip;
    private Long createTime;
    private UserPublicInfo user;
}
