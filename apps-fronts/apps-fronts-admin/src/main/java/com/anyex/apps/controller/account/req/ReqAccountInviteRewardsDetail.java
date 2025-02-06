/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

/**
 * 账户邀请奖励 表单提交
 * <p>File：AccountInviteRewardsDetail.java</p>
 * <p>Title: AccountInviteRewardsDetail</p>
 * <p>Description:AccountInviteRewardsDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户邀请奖励表单提交")
public class ReqAccountInviteRewardsDetail extends GenericEntity
{
    @ApiModelProperty(value = "账户ID", position = 2 , required = true )
    @NotNull(message = "账户ID不能为空")
    private Long accountId;

    @ApiModelProperty(value = "邀请人数", position = 3 , required = true )
    @NotNull(message = "邀请人数不能为空")
    private Integer inviteCnt;

    @ApiModelProperty(value = "当前奖励级别(1、2、3)", position = 4 , required = true )
    @NotNull(message = "当前奖励级别(1、2、3)不能为空")
    private Integer rewardsLevel;

    @ApiModelProperty(value = "当前奖励金额", position = 5 , required = true )
    @NotNull(message = "当前奖励金额不能为空")
    private BigDecimal inviteAward;

    @ApiModelProperty(value = "状态(0未发放、1已发放)", position = 6 , required = true )
    @NotNull(message = "状态(0未发放、1已发放)不能为空")
    private Integer status;

    @ApiModelProperty(value = "备注", position = 7 )
    private String remark;

    @ApiModelProperty(value = "创建时间", position = 8 , required = true )
    @NotNull(message = "创建时间不能为空")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", position = 9 )
    private Long updateTime;

}
