package com.anyex.apps.controller.business.luckybox.game.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "玩游戏请求对象")
public class ReqPlayGame implements Serializable
{
    /**游戏Id*/
    @NotNull(message = "游戏Id不可为空")
    @ApiModelProperty(value = "游戏Id", required = true)
    private java.lang.Long gameId;
}
