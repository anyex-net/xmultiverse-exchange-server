package com.anyex.openim.api.statistics.req;

import lombok.Data;
import com.anyex.openim.base.RequestPagination;

import javax.validation.constraints.NotNull;

@Data
public class GetActiveUserReq {
    private Long start;
    private Long end;
    private Boolean ase;
    private Boolean group;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
