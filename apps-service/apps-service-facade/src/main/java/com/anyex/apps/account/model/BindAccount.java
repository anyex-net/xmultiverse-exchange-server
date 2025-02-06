//package com.anyex.apps.account.model;
//
//import com.anyex.apps.consts.GlobalConst;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 绑定平台账号
// * <p>File：ThirdLogin.java</p>
// * <p>Title: ThirdLogin</p>
// * <p>Description: ThirdLogin</p>
// * <p>Copyright: Copyright (c) 2019/11/6</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//@ApiModel(description = "绑定平台账号参数对象")
//public class BindAccount implements Serializable
//{
//    @ApiModelProperty(value = "国家代码")
//    @NotNull(message = "国家代码不可为空")
//    private String country = GlobalConst.DEFAULT_COUNTRY;
//
//    @NotNull(message = "手机号不可为空")
//    @ApiModelProperty(value = "手机号", required = true)
//    private String phone;
//
//    @NotNull(message = "绑定对象不可为空")
//    @ApiModelProperty(value = "绑定对象", required = true)
//    private Long   objectId;
//
//    @NotNull(message = "短信码不可为空")
//    @ApiModelProperty(value = "短信码", required = true)
//    private String code;
//
//    @ApiModelProperty(value = "验证码")
//    @NotNull(message = "验证码不可为空")
//    private String kaptcha;
//}
