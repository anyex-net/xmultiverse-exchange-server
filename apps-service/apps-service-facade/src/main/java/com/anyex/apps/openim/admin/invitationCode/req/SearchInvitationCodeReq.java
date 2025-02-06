package com.anyex.apps.openim.admin.invitationCode.req;

import com.anyex.openim.base.RequestPagination;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SearchInvitationCodeReq {
    private Integer status;
    private List<String> userIDs;
    private List<String> codes;

    private String keyword;
    @NotNull
    private RequestPagination pagination = new RequestPagination();
}
