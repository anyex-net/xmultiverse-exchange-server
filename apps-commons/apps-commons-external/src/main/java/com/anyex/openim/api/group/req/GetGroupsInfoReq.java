package com.anyex.openim.api.group.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetGroupsInfoReq {
    @NotEmpty
    private List<String> groupIDs = new ArrayList<>();
}
