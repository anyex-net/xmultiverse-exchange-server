package com.anyex.openim.api.group.req;

import lombok.Data;
import com.anyex.openim.base.RequestPagination;

import javax.validation.constraints.NotNull;

@Data
public class GetUserReqApplicationListReq {
    @NotNull
    private String userID;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
