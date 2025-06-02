package com.anyex.apps.user.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ApiModel("邀请用户分别统计")
public class UserInviteRebateModel implements Serializable {

    private Long inviteeId;

    private Long uid;

    private Integer isValid;

    private BigDecimal totalRebateAmount;

    private Long createTime;
}
