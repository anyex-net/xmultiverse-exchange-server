package com.anyex.apps.statistics.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsForLineModel {

    @ApiModelProperty(name = "x坐标")
    private String[] xx;

    @ApiModelProperty(name = "y坐标")
    private BigDecimal[] yy;

}
