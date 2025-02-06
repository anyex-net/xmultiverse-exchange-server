//package com.anyex.apps.account.model;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * AccountParams
// * <p>File：AccountParams.java</p>
// * <p>Title: AccountParams</p>
// * <p>Description: AccountParams</p>
// * <p>Copyright: Copyright (c) 2019/10/23</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class AccountParams implements Serializable
//{
//    @NotNull(message = "头像不可为空")
//    @ApiModelProperty(value = "头像", required = true)
//    private String headLogo;
//
//    @NotNull(message = "昵称不可为空")
//    @ApiModelProperty(value = "昵称", required = true)
//    private String nickName;
//
//    /*** 用户简介*/
//    @ApiModelProperty(value = "用户简介", required = true)
//    private String summary;
//
//    @NotNull(message = "性别不可为空")
//    @ApiModelProperty(value = "性别=M:F", required = true)
//    private String sex;
//}
