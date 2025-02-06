package com.anyex.apps.controller.payment.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 提现查询
 */
@Data
public class ReqWithdrawQuery {

    @ApiModelProperty(value = "withdrawRecordId",required = true)
    @NotNull(message = "withdrawRecordId is not null")
    private Long withdrawRecordId;
}
