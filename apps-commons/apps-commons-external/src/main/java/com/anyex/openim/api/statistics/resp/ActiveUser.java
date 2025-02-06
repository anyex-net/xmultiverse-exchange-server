package com.anyex.openim.api.statistics.resp;

import lombok.Data;
import com.anyex.openim.api.vo.UserInfo;

@Data
public class ActiveUser {
    private UserInfo user;
    private Long count;
}
