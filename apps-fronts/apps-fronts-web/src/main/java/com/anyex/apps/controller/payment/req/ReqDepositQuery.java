package com.anyex.apps.controller.payment.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 充值查询
 */
@Data
public class ReqDepositQuery {

    @ApiModelProperty(value = "depositRecordId",required = true)
    @NotNull(message = "depositRecordId is not null")
    private Long depositRecordId;
}
