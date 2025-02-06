package com.anyex.openim.api.group.req;

import lombok.Data;
import com.anyex.openim.api.vo.GroupInfoForSet;

import javax.validation.constraints.NotNull;

@Data
public class SetGroupInfoReq {
    @NotNull
    private GroupInfoForSet groupInfoForSet;
}
