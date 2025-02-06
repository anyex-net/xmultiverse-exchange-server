package com.anyex.apps.openim.chat.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LiveKitTokenReq {

    @NotNull(message = "房间号 不能为空")
    @ApiModelProperty(value = "房间号",required = true)
    private String room;

    @NotNull(message = "用户id 不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private String identity;
}
