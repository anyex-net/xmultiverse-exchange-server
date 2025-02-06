///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.entity;
//
//import javax.validation.constraints.NotNull;
//
//import com.anyex.fhsc.bean.GenericEntity;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * AccountCertification 实体对象
// * <p>File：AccountCertification.java</p>
// * <p>Title: AccountCertification</p>
// * <p>Description:AccountCertification</p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//@ApiModel(description = "帐户认证")
//public class AccountCertification extends GenericEntity
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
//     * 姓名
//     */
//    @NotNull(message = "姓名不可为空")
//    @ApiModelProperty(value = "姓名", required = true)
//    private String            realName;
//
//    /**
//     * 证件号码
//     */
//    @NotNull(message = "证件号码不可为空")
//    @ApiModelProperty(value = "证件号码", required = true)
//    private String            idcard;
//
//    /**
//     * 证件类型(1 身份证, 2 港澳居民来往内地通行证, 3 台湾居民来往大陆通行证, 4 护照)
//     */
//    @NotNull(message = "证件类型不可为空")
//    @ApiModelProperty(value = "证件类型(1 身份证, 2 港澳居民来往内地通行证, 3 台湾居民来往大陆通行证, 4 护照)", required = true)
//    private Long              cardType;
//
//    /**
//     * 附件信息以json形式
//     */
//    @NotNull(message = "附件信息以json形式不可为空")
//    @ApiModelProperty(value = "附件信息以json形式", required = true)
//    private String            attachments;
//
//    /**
//     * 拒绝原因
//     */
//    @ApiModelProperty(value = "拒绝原因")
//    private String            reason;
//
//    /**
//     * 审核状态(submit 待审核, reject 审核拒绝, approve 审核通过)
//     */
//    @NotNull(message = "审核状态不可为空")
//    @ApiModelProperty(value = "审核状态(submit 待审核, reject 审核拒绝, approve 审核通过)", required = true)
//    private String            status;
//
//    @ApiModelProperty(value = "是否推荐")
//    private boolean           recommend;
//
//    /**
//     * 创建时间
//     */
//    @NotNull(message = "创建时间不可为空")
//    @ApiModelProperty(value = "创建时间", required = true)
//    private Long              createDate;
//
//    @ApiModelProperty(value = "用户昵称", required = true)
//    private String            nickName;
//}
