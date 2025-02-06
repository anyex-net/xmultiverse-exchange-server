package com.anyex.apps.openim.admin.defaultUser.resp;

import lombok.Data;

import java.util.List;

@Data
public class FindDefaultFriendResp {
    private List<String> userIDs;
}
