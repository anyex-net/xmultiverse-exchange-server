package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ReqAccountInviteDetail implements Serializable
{
    @NotNull(message = "一级级返佣账户id 不能为空")
    @ApiModelProperty(value = "一级级返佣账户id",required = true)
    private Long firstAccountId;

}
