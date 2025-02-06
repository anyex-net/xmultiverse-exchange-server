package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  anyex
 */
@Data
public class LogoutReq implements Serializable {

    @NotNull(message = "平台ID 不能为空")
    @ApiModelProperty(value = "平台ID, 1：IOS，2：Android，3：Windows，4：OSX，5：Web，6：MiniWeb，7：Linux，8：Android Pad，9：IPad，10：admin", required = true)
    private Integer platform;
}
