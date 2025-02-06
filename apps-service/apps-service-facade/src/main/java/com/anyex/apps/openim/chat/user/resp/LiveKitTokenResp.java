package com.anyex.apps.openim.chat.user.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LiveKitTokenResp {

    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "服务地址")
    private String serverUrl;
}
