package com.anyex.apps.statistics.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsAccountForLinesModel {

    @ApiModelProperty(name = "x坐标")
    private String[] xx;

    @ApiModelProperty(name = "y坐标(google)")
    private BigDecimal[] googleYy;

    @ApiModelProperty(name = "y坐标(transsion)")
    private BigDecimal[] transsionYy;

    @ApiModelProperty(name = "y坐标(luckybox)")
    private BigDecimal[] luckyboxYy;

}
