//package com.anyex.apps.account.model.req;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * GA绑定请求对象
// * <p>File：ReqBindGA.java</p>
// * <p>Title: ReqBindGA</p>
// * <p>Description: ReqBindGA</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class ReqBindGA implements Serializable
//{
//    /**
//     * GA私钥
//     */
//    @NotNull(message = "GA私钥不可为空")
//    @ApiModelProperty(value = "GA私钥", required = true)
//    private String            gaSecretKey;
//
//    /**
//     * GA验证码
//     */
//    @NotNull(message = "GA验证码不可为空")
//    @ApiModelProperty(value = "GA验证码", required = true)
//    private String            gaCode;
//
//    /**
//     * 短信验证码
//     */
//    @NotNull(message = "短信验证码不可为空")
//    @ApiModelProperty(value = "短信验证码", required = true)
//    private String            smsCode;
//}
