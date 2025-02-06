package com.anyex.openim.api.group.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupMemberFullInfo;

import java.util.List;

@Data
public class GetGroupMemberListResp {
    private Integer total;
    private List<GroupMemberFullInfo> members;
}
