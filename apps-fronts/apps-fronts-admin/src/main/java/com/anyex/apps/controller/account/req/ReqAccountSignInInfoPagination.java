/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

/**
 * 账户签到信息 分页查询
 * <p>File：AccountSignInInfo.java</p>
 * <p>Title: AccountSignInInfo</p>
 * <p>Description:AccountSignInInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户签到信息分页查询")
public class ReqAccountSignInInfoPagination extends Pagination
{
    @ApiModelProperty(value = "账户ID", position = 2 , required = true )
    @NotNull(message = "账户ID不能为空")
    private Long accountId;

    @ApiModelProperty(value = "总积分", position = 3 , required = true )
    @NotNull(message = "总积分不能为空")
    private Integer totalPoints;

    @ApiModelProperty(value = "剩余积分", position = 4 , required = true )
    @NotNull(message = "剩余积分不能为空")
    private Integer remainingPoints;

    @ApiModelProperty(value = "积分等级", position = 5 , required = true )
    @NotNull(message = "积分等级不能为空")
    private Integer pointsLevel;

    @ApiModelProperty(value = "总签到次数", position = 6 , required = true )
    @NotNull(message = "总签到次数不能为空")
    private Integer totalSigninTimes;

    @ApiModelProperty(value = "最高签到次数", position = 7 , required = true )
    @NotNull(message = "最高签到次数不能为空")
    private Integer maxsSigninTimes;

    @ApiModelProperty(value = "最后一次签到日期", position = 8 , required = true )
    @NotNull(message = "最后一次签到日期不能为空")
    private Date lastSigninDate;

    @ApiModelProperty(value = "本轮累计签到次数", position = 9 , required = true )
    @NotNull(message = "本轮累计签到次数不能为空")
    private Integer currentSigninTimes;

    @ApiModelProperty(value = "创建时间", position = 10 , required = true )
    @NotNull(message = "创建时间不能为空")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", position = 11 )
    private Long updateTime;

}
