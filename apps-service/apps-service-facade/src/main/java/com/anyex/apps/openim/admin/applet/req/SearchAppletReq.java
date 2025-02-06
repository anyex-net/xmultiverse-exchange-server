package com.anyex.apps.openim.admin.applet.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchAppletReq {
    private String keyword;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
