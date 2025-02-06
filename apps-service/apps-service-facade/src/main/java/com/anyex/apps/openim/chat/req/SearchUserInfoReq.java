package com.anyex.apps.openim.chat.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import java.util.List;

@Data
public class SearchUserInfoReq {
    private String keyword;
    private RequestPagination pagination = new RequestPagination();
    private List<Integer> genders;
    private List<String> userIDs;
}
