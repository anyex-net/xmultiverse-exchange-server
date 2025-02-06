package com.anyex.apps.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Date 2023/2/24 17:14
 * @Version 1.00
 */
@ApiModel(description = "查询条件")
@Data
@Accessors(chain = true)
public class Query {
    @ApiModelProperty("当前页")
    private Integer pageNo;

    @ApiModelProperty("每页的数量")
    private Integer pageSize;

//    @ApiModelProperty(hidden = true)
//    private String ascs;
//
//    @ApiModelProperty(hidden = true)
//    private String descs;
}
