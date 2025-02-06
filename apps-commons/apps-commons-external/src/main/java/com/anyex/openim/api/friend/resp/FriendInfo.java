package com.anyex.openim.api.friend.resp;

import com.anyex.openim.api.vo.UserInfo;
import lombok.Data;

@Data
public class FriendInfo {
    private String ownerUserID;
    private String remark;
    private Long createTime;
    private UserInfo friendUser;
    private Integer addSource;
    private String operatorUserID;
    private String ex;
}
