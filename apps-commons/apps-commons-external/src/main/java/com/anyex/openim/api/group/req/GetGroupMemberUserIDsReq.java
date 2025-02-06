package com.anyex.openim.api.group.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetGroupMemberUserIDsReq {
    @NotNull
    private String groupID;
}
