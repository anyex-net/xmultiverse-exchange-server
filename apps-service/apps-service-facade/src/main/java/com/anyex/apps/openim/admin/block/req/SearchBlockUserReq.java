package com.anyex.apps.openim.admin.block.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchBlockUserReq {
    private String keyword;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
