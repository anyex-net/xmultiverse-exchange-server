package com.anyex.openim.api.user.resp;

import lombok.Data;
import com.anyex.openim.api.vo.OnlineStatus;

import java.util.List;

/**
 * @author  anyex
 */
@Data
public class GetUserStatusResp {
    private List<OnlineStatus> statusList;
}
