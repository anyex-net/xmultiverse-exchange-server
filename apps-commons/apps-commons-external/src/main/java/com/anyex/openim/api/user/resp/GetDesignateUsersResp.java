package com.anyex.openim.api.user.resp;

import com.anyex.openim.api.vo.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class GetDesignateUsersResp {
    private List<UserInfo> usersInfo;
}
