package com.anyex.apps.openim.admin.invitationCode.resp;

import com.anyex.apps.openim.chat.vo.UserPublicInfo;
import lombok.Data;

@Data
public class InvitationRegister {
    private String invitationCode;
    private Long createTime;
    private String usedUserID;
    private UserPublicInfo usedUser;
}
