/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

/**
 * 账户签到明细 实体对象
 * <p>File：AccountSignInDetail.java</p>
 * <p>Title: AccountSignInDetail</p>
 * <p>Description:AccountSignInDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户签到明细")
public class AccountSignInDetail extends GenericEntity
{
    @ApiModelProperty(value = "账户ID", position = 2 , required = true )
    @NotNull(message = "账户ID不能为空")
    private Long accountId;

    @ApiModelProperty(value = "签到对应的日期", position = 3 , required = true )
    @NotNull(message = "签到对应的日期不能为空")
    private Date signinDate;

    @ApiModelProperty(value = "是否连续签到", position = 4 , required = true )
    @NotNull(message = "是否连续签到不能为空")
    private Integer isContinuous;

    @ApiModelProperty(value = "连续签到次数", position = 5 , required = true )
    @NotNull(message = "连续签到次数不能为空")
    private Integer currentSigninTimes;

    @ApiModelProperty(value = "本次签到积分", position = 6 , required = true )
    @NotNull(message = "本次签到积分不能为空")
    private Integer pointsAwarded;

    @ApiModelProperty(value = "签到时间", position = 7 , required = true )
    @NotNull(message = "签到时间不能为空")
    private Date signinTime;

    @ApiModelProperty(value = "奖励发放状态 0未发放，1已发放", position = 8 , required = true )
    @NotNull(message = "奖励发放状态 0未发放，1已发放不能为空")
    private Integer status;

    @ApiModelProperty(value = "创建时间", position = 9 , required = true )
    @NotNull(message = "创建时间不能为空")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", position = 10 )
    private Long updateTime;

}
