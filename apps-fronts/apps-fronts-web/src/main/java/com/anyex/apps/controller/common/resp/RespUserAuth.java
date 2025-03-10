package com.anyex.apps.controller.common.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RespUserAuth implements Serializable
{
    /**user_id*/
    @ApiModelProperty(value = "user_id")
    private Long user_id;
}
