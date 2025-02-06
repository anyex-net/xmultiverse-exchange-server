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
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
///**
// * 第三方登录 实体对象
// * <p>File：Thirdparty.java</p>
// * <p>Title: Thirdparty</p>
// * <p>Description:Thirdparty</p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//@Data
//@EqualsAndHashCode(callSuper = true)
//@ApiModel(description = "第三方登录")
//public class Thirdparty extends GenericEntity
//{
//    private static final long serialVersionUID = 1L;
//
//    /**本平台账户id*/
//    @ApiModelProperty(value = "本平台账户id")
//    private Long              accountId;
//
//    /**第三方返回id*/
//    @ApiModelProperty(value = "第三方返回id")
//    private String            openId;
//
//    /**创建时间*/
//    @NotNull(message = "创建时间不可为空")
//    private Long              createDate;
//
//    /**平台类型*/
//    @NotNull(message = "平台类型不可为空")
//    @ApiModelProperty(value = "平台类型", required = true)
//    private String            type;
//
//    /**头像*/
//    @ApiModelProperty(value = "头像")
//    private String            accountLogo;
//
//    /**nickName*/
//    @ApiModelProperty(value = "昵称")
//    private String            nickName;
//}
