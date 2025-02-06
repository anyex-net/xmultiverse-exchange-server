package com.anyex.openim.api.group.req;

import java.util.List;

public class InviteToGroup {

    /**
     * groupID : 2759960147
     * invitedUserIDs : ["1225441072","9906953281"]
     * reason : your reason
     */

    private String groupID;
    private String reason;
    private List<String> invitedUserIDs;

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getInvitedUserIDs() {
        return invitedUserIDs;
    }

    public void setInvitedUserIDs(List<String> invitedUserIDs) {
        this.invitedUserIDs = invitedUserIDs;
    }
}
