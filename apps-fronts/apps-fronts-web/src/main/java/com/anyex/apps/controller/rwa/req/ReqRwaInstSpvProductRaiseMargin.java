package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品保证金请求对象")
public class ReqRwaInstSpvProductRaiseMargin extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**机构SPV产品ID*/
    @NotNull(message = "机构SPV产品保证金")
    @ApiModelProperty(value = "机构SPV产品保证金", position = 3, required = true)
    private BigDecimal raiseMargin;
}
