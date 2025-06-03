package com.anyex.apps.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("邀请统计")
public class InviteRebateSummaryModel implements Serializable {

    @ApiModelProperty(value = "邀请人数")
    private Integer totalInvites;   // 邀请人数

    @ApiModelProperty(value = "交易人数")
    private Integer tradedUsers;    // 交易人数

    @ApiModelProperty(value = "累计返佣金额")
    private BigDecimal totalRebate; // 累计返佣金额
}
