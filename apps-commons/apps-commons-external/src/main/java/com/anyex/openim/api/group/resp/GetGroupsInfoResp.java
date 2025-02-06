package com.anyex.openim.api.group.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupInfo;

import java.util.List;

@Data
public class GetGroupsInfoResp {
    private List<GroupInfo> groupInfos;
}
