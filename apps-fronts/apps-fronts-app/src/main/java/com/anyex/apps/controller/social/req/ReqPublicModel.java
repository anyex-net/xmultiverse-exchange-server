package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "通用")
public class ReqPublicModel {

    @ApiModelProperty(value = "时间戳")
    private Long timestamp;
}
