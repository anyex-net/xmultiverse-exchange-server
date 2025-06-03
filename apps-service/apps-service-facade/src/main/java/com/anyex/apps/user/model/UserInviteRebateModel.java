package com.anyex.apps.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ApiModel("邀请用户分别统计")
public class UserInviteRebateModel implements Serializable {

    @ApiModelProperty(value = "被邀请者ID")
    private Long inviteeId;

    @ApiModelProperty(value = "用户UID")
    private Long uid;

    @ApiModelProperty(value = "是否有效")
    private Integer isValid;

    @ApiModelProperty(value = "累计返佣")
    private BigDecimal totalRebateAmount;

    @ApiModelProperty(value = "注册时间")
    private Long createTime;
}
