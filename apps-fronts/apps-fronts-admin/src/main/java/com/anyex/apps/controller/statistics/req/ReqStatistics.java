package com.anyex.apps.controller.statistics.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ReqStatistics {

    @ApiModelProperty(name = "类型 1按天 2上一周 3上一个月 4前三个月 5前六个月",required = true)
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty(name = "日期 yyyy-MM-dd 按天统计必传",required = true)
    private String date;
}
