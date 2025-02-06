package com.anyex.openim.api.statistics.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupInfo;

@Data
public class ActiveGroup {
    private GroupInfo groupInfo;
    private Long count;
}
