package com.anyex.openim.api.user.resp;

import com.anyex.openim.api.vo.NotificationAccountInfo;
import lombok.Data;

import java.util.List;

@Data
public class SearchNotificationAccountResp {
    private List<NotificationAccountInfo> notificationAccounts;
}
