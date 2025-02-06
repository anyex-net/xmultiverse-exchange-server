package com.anyex.openim.api.group.req;

import lombok.Data;
import com.anyex.openim.api.vo.SetGroupMemberInfo;

import java.util.List;

@Data

public class SetGroupMemberInfoReq {
    private List<SetGroupMemberInfo> members;
}
