package com.anyex.apps.rwa.model;


import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "申购结果对象")
@AllArgsConstructor
@NoArgsConstructor
public class RwaInstSpvProductPurchaseResultModel extends RwaInstSpvProductPurchase {

    @ApiModelProperty(value = "代币名称", position = 10, required = true)
    private java.lang.String tokenName;
}
