package com.anyex.openim.api.group.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetGroupMemberListReq {
    @NotNull
    private String groupID;
    private Integer filter;
    private String userId;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
