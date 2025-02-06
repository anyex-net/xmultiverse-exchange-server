package com.anyex.openim.api.friend.resp;

import lombok.Data;
import com.anyex.openim.api.vo.UserInfo;

@Data
public class GetSpecifiedFriendsInfoInfo {
    private UserInfo userInfo;
    private FriendInfo friendInfo;
    private BlackInfo blackInfo;
}
