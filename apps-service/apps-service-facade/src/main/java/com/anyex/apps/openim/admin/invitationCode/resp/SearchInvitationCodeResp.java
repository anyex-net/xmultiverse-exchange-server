package com.anyex.apps.openim.admin.invitationCode.resp;

import lombok.Data;

import java.util.List;

@Data
public class SearchInvitationCodeResp {
    private Integer total;
    private List<InvitationRegister> list;
}
