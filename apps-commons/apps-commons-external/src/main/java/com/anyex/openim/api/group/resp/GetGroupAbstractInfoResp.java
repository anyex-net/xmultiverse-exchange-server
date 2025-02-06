package com.anyex.openim.api.group.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupAbstractInfo;

import java.util.List;

@Data
public class GetGroupAbstractInfoResp {
    private List<GroupAbstractInfo> groupAbstractInfos;
}
