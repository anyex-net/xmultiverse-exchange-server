package com.anyex.openim.api.group.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupMemberFullInfo;

import java.util.List;

@Data
public class GetGroupMembersInfoResp {
    private List<GroupMemberFullInfo> members;
}
