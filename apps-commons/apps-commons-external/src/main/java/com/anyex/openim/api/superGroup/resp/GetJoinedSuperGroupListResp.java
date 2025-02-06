package com.anyex.openim.api.superGroup.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupInfo;

import java.util.List;

@Data
public class GetJoinedSuperGroupListResp {
    private List<GroupInfo> groups;
}
