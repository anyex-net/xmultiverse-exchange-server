package com.anyex.openim.api.group.resp;

import lombok.Data;
import com.anyex.openim.api.vo.GroupInfo;

@Data
public class CMSGroup {
    private GroupInfo groupInfo;
    private String groupOwnerUserName;
    private String groupOwnerUserID;
}
