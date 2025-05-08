/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 平台交易产品分区
 * <p>File：InstrumentsPartitionModel.java</p>
 * <p>Title: InstrumentsPartitionModel</p>
 * <p>Description:InstrumentsPartitionModel</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "平台交易产品分区")
public class InstrumentsTagsModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**分区*/
	@ApiModelProperty(value = "分区")
	private String tags;

	/**分区名*/
	@ApiModelProperty(value = "分区名")
	private String tagsName;
}

