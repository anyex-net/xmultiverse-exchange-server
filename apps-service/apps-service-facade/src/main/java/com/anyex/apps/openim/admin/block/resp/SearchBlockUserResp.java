package com.anyex.apps.openim.admin.block.resp;

import lombok.Data;

import java.util.List;

@Data
public class SearchBlockUserResp {
    private Integer total;
    private List<BlockUserInfo> users;
}
