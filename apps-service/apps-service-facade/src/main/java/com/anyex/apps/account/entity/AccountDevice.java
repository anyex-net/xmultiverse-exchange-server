///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.entity;
//
//import javax.validation.constraints.NotNull;
//
//import com.anyex.fhsc.bean.GenericEntity;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * 账户设备表 实体对象
// * <p>File：AccountDevice.java</p>
// * <p>Title: AccountDevice</p>
// * <p>Description:AccountDevice</p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//@ApiModel(description = "账户设备表")
//public class AccountDevice extends GenericEntity
//{
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 账户id
//     */
//    @NotNull(message = "账户id不可为空")
//    @JsonSerialize(using = ToStringSerializer.class)
//    @ApiModelProperty(value = "账户id", required = true)
//    private Long              accountId;
//
//    /**
//     * 设备类型
//     */
//    @NotNull(message = "设备类型不可为空")
//    @ApiModelProperty(value = "设备类型", required = true)
//    private String            deviceType;
//
//    /**
//     * 设备名称
//     */
//    @NotNull(message = "设备名称不可为空")
//    @ApiModelProperty(value = "设备名称", required = true)
//    private String            deviceName;
//
//    /**
//     * 设备号
//     */
//    @NotNull(message = "设备号不可为空")
//    @ApiModelProperty(value = "设备号", required = true)
//    private String            deviceNum;
//
//    /**
//     * ip地址
//     */
//    @NotNull(message = "ip地址不可为空")
//    @ApiModelProperty(value = "ip地址", required = true)
//    private String            ipAddress;
//
//    /**
//     * 创建时间
//     */
//    @NotNull(message = "创建时间不可为空")
//    @ApiModelProperty(value = "创建时间", required = true)
//    private Long              createDate;
//
//    /**
//     * 最新登录时间
//     */
//    @NotNull(message = "最新登录时间不可为空")
//    @ApiModelProperty(value = "最新登录时间", required = true)
//    private Long              latestLoginDate;
//
//    /**
//     * app版本号
//     */
//    @NotNull(message = "app版本号不可为空")
//    @ApiModelProperty(value = "app版本号", required = true)
//    private String            appVersion;
//
//    /**
//     * 账户名
//     */
//    private String            loginName;
//}
