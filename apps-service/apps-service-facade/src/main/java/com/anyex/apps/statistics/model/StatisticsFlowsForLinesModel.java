package com.anyex.apps.statistics.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsFlowsForLinesModel {

    @ApiModelProperty(name = "x坐标")
    private String[] xx;

    @ApiModelProperty(name = "y坐标(转盘)")
    private BigDecimal[] gameSpinRewardYy;

    @ApiModelProperty(name = "y坐标(一元夺宝)")
    private BigDecimal[] activityTreasureHuntYy;

    @ApiModelProperty(name = "y坐标(活动半价)")
    private BigDecimal[] activityHotDealsYy;
}
