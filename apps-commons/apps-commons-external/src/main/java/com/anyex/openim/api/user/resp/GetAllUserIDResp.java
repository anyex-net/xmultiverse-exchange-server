package com.anyex.openim.api.user.resp;

import lombok.Data;
import com.anyex.openim.api.vo.UserInfo;

import java.util.List;

/**
 * @author  anyex
 */
@Data
public class GetAllUserIDResp {
    private Integer total;
    private List<UserInfo> usersInfo;
//    private List<String> userIDs;
}
