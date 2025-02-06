package com.anyex.openim.api.statistics.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetActiveGroupReq {
    private Long start;
    private Long end;
    private Boolean ase;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
