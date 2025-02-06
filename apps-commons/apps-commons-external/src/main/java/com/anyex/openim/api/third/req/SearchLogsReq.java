package com.anyex.openim.api.third.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchLogsReq {
    private String keyword;
    private Long startTime;
    private Long endTime;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
