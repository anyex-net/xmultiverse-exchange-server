package com.anyex.openim.api.user.resp;

import com.anyex.openim.api.vo.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class GetPaginationUsersResp {
    private List<UserInfo> users;
    private Integer total;
}
