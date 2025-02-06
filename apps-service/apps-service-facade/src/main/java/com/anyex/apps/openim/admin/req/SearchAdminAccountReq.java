package com.anyex.apps.openim.admin.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

@Data
public class SearchAdminAccountReq {
    private RequestPagination pagination = new RequestPagination();
}
