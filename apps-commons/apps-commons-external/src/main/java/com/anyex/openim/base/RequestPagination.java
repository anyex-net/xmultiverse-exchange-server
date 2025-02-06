package com.anyex.openim.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPagination {
    /**
     * 页数
     */
    @NotNull(message = "页数 不能为空")
    @ApiModelProperty(value = "页数",required = true)
    private Integer pageNumber=1;
    /**
     * 每页行数
     */
    @NotNull(message = "每页行数 不能为空")
    @ApiModelProperty(value = "每页行数",required = true)
    private Integer showNumber=10;
}
