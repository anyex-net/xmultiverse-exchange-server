package com.anyex.apps.openim.chat.user.req;

import com.anyex.openim.base.RequestPagination;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchUserPublicInfoReq {

    @ApiModelProperty(value = "性别")
    private Integer genders;
    @ApiModelProperty(value = "用户id")
    private String keyword;

    @NotNull(message = "分页对象 不能为空")
    @ApiModelProperty(value = "分页对象",required = true)
    private RequestPagination pagination = new RequestPagination();
}
