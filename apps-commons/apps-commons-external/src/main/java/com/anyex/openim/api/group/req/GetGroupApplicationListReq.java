package com.anyex.openim.api.group.req;

import lombok.Data;
import com.anyex.openim.base.RequestPagination;

import javax.validation.constraints.NotNull;

@Data
public class GetGroupApplicationListReq {
    @NotNull
    private String fromUserID;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
