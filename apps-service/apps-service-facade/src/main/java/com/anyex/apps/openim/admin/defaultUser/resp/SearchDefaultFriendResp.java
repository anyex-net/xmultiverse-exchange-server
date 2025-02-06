package com.anyex.apps.openim.admin.defaultUser.resp;

import lombok.Data;

import java.util.List;

@Data
public class SearchDefaultFriendResp {
    private List<DefaultFriendAttribute> users;
    private Integer total;
}
