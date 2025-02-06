package com.anyex.openim.api.user.req;

import lombok.Data;
import com.anyex.openim.base.RequestPagination;

import javax.validation.constraints.NotNull;

@Data
public class GetPaginationUsersReq {
    @NotNull
    private RequestPagination pagination= new RequestPagination();
}
