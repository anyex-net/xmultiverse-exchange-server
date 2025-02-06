package com.anyex.openim.api.friend.req;

import lombok.Data;
import com.anyex.openim.base.RequestPagination;

import javax.validation.constraints.NotNull;

@Data
public class GetPaginationFriendsApplyToReq {
    @NotNull
    private String userID;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
