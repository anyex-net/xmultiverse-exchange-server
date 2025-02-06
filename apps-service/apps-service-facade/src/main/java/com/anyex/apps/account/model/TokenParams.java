//package com.anyex.apps.account.model;
//
//import com.anyex.apps.consts.GlobalConst;
//import com.anyex.apps.common.consts.MessageConst;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 短信验证码参数接收对象
// * <p>File：TokenParams.java</p>
// * <p>Title: TokenParams</p>
// * <p>Description: TokenParams</p>
// * <p>Copyright: Copyright (c) 2017/8/3</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class TokenParams implements Serializable
//{
//    private static final long serialVersionUID = -3891293361876559333L;
//
//    @NotNull(message = "国家代码不可为空！")
//    private String            country          = GlobalConst.DEFAULT_COUNTRY;
//
//    @NotNull(message = "验证类型不可为空！")
//    private String            type             = MessageConst.SMS_VALID_OTHER;
//
//    @NotNull(message = "手机号码不可为空！")
//    private String            phone;
//
//    @NotNull(message = "验证码不可为空！")
//    private String            code;
//
//    /** 昵称**/
//    private String            nickName;
//}
