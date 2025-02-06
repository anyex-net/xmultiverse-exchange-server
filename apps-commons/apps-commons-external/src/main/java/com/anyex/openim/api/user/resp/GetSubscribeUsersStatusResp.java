package com.anyex.openim.api.user.resp;

import com.anyex.openim.api.vo.OnlineStatus;
import lombok.Data;

import java.util.List;

@Data
public class GetSubscribeUsersStatusResp {
    private List<OnlineStatus> statusList;
}
