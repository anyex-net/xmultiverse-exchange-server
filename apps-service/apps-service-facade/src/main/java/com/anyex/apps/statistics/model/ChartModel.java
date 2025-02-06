package com.anyex.apps.statistics.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChartModel {

    @ApiModelProperty(name = "日期")
    private String dt;

    @ApiModelProperty(name = "数量")
    private BigDecimal num;
}
