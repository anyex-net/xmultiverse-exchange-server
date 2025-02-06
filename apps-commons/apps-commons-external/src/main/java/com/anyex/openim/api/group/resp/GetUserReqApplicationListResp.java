package com.anyex.openim.api.group.resp;

import com.anyex.openim.api.vo.GroupRequest;
import lombok.Data;

import java.util.List;

@Data
public class GetUserReqApplicationListResp {
    private Integer total;
    private List<GroupRequest> groupRequests;
}
