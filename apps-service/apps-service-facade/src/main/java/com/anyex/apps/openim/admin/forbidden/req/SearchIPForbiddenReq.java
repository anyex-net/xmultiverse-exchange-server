package com.anyex.apps.openim.admin.forbidden.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchIPForbiddenReq {
    private String keyword;
    private Integer status;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
