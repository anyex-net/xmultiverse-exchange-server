package com.anyex.apps.user.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("邀请统计")
public class InviteRebateSummaryModel implements Serializable {

    private Integer totalInvites;   // 邀请人数
    private Integer tradedUsers;    // 交易人数
    private BigDecimal totalRebate; // 累计返佣金额
}
