package com.anyex.apps.openim.chat.user.req;

import com.anyex.openim.base.RequestPagination;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchUserFullInfoReq {

    @ApiModelProperty(value = "用户id")
    private String keyword;

    @NotNull(message = "分页对象 不能为空")
    @ApiModelProperty(value = "分页对象",required = true)
    private RequestPagination pagination = new RequestPagination();
}
