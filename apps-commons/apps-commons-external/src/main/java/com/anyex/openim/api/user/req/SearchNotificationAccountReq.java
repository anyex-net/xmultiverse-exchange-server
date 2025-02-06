package com.anyex.openim.api.user.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

@Data
public class SearchNotificationAccountReq {
    private String keyword;
    private RequestPagination pagination = new RequestPagination();
}
