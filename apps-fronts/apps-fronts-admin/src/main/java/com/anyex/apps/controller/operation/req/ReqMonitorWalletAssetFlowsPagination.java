/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 钱包资产流水监控 分页请求对象
 * <p>File：ReqMonitorWalletAssetFlows.java</p>
 * <p>Title: ReqMonitorWalletAssetFlows</p>
 * <p>Description:ReqMonitorWalletAssetFlows</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产流水监控分页请求对象")
public class ReqMonitorWalletAssetFlowsPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@ApiModelProperty(value = "账户ID")
	private Long accountId;

	/**币种*/
	@ApiModelProperty(value = "币种")
	private String currency;

	/**最后监控时间*/
	@ApiModelProperty(value = "最后监控时间")
	private Long lastMonitorTime;

	/**监控状态(0异常、1正常)*/
	@ApiModelProperty(value = "监控状态(0异常、1正常)")
	private Boolean monitorStatus;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;


}

