/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 钱包资产流水监控 实体对象
 * <p>File：MonitorWalletAssetFlows.java</p>
 * <p>Title: MonitorWalletAssetFlows</p>
 * <p>Description:MonitorWalletAssetFlows</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产流水监控")
public class MonitorWalletAssetFlows extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private Long accountId;

	/**币种*/
	@NotEmpty(message = "币种不可为空")
	@ApiModelProperty(value = "币种", required = true)
	private String currency;

	/**最后监控时间*/
	@NotNull(message = "最后监控时间不可为空")
	@ApiModelProperty(value = "最后监控时间", required = true)
	private Long lastMonitorTime;

	/**监控状态(0异常、1正常)*/
	@NotNull(message = "监控状态(0异常、1正常)不可为空")
	@ApiModelProperty(value = "监控状态(0异常、1正常)", required = true)
	private Boolean monitorStatus;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;
}

