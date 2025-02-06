package com.anyex.openim.api.user.resp;

import lombok.Data;
import com.anyex.openim.api.vo.OnlineStatus;

import java.util.List;

@Data
public class SubscribeOrCancelUsersStatusResp {
    private List<OnlineStatus> statusList;
}
