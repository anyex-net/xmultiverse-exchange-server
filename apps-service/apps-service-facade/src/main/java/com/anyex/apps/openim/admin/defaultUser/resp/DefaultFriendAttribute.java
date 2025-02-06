package com.anyex.apps.openim.admin.defaultUser.resp;

import com.anyex.apps.openim.chat.vo.UserPublicInfo;
import lombok.Data;

@Data
public class DefaultFriendAttribute {
    private String userID;
    private Long createTime;
    private UserPublicInfo user;
}
