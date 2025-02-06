package com.anyex.apps.openim.chat.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
public class SearchFriendReq{

    @ApiModelProperty(value = "IM userId")
    private String userID;
}
