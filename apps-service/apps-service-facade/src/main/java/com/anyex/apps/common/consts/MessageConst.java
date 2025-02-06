package com.anyex.apps.common.consts;

/**
 * 消息模版常量
 * <p>File：MessageConst.java</p>
 * <p>Title: MessageConst</p>
 * <p>Description:MessageConst</p>
 * <p>Copyright: Copyright (c) 2017/7/21</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public abstract class MessageConst
{
    // 消息类型 邮件
    public static final String MESSAGE_EMAIL                    = "email";

    // 消息类型 短信
    public static final String MESSAGE_SMS                      = "sms";

    // ADMIN端
    // 登录类型
    public static final String ADMIN_VALID_LOGIN                = "login";

    // APP端
    // 注册类型邮箱码
    public static final String EMAIL_VALID_REGISTER             = "email_register";

    // 登录类型短信码
    public static final String EMAIL_VALID_LOGIN                = "email_login";

    // 忘记密码邮箱码
    public static final String EMAIL_VALID_FORGETPASS           = "email_forgetpass";

    // 修改密码邮箱码
    public static final String EMAIL_VALID_MODIFYPASS           = "email_modifypass";

    // 注销类邮箱码
    public static final String EMAIL_VALID_LOGOFF               = "email_logoff";

    // 其它类型邮箱码
    public static final String EMAIL_VALID_OTHER                = "email_other";

    // 注册类型短信码
    public static final String SMS_VALID_REGISTER               = "sms_register";

    // 登录类型短信码
    public static final String SMS_VALID_LOGIN                  = "sms_login";

    // 忘记密码短信码
    public static final String SMS_VALID_FORGETPASS             = "sms_forgetpass";

    // 修改密码短信码
    public static final String SMS_VALID_MODIFYPASS             = "sms_modifypass";

    // 其它类型短信码
    public static final String SMS_VALID_OTHER                  = "sms_other";


    // 邮箱注册
    public static final String TEMPLATE_EMAIL_REGISTERCODE      = "tpl_email_send_register_code";

    // 邮箱登录
    public static final String TEMPLATE_EMAIL_LOGINCODE         = "tpl_email_send_login_code";

    // 找回密码
    public static final String TEMPLATE_EMAIL_FORGETPASSCODE    = "tpl_email_forget_pass_code";

    // 重置密码
    public static final String TEMPLATE_EMAIL_RESETPASSCODE     = "tpl_email_reset_pass_code";

    // 绑定邮件发送
    public static final String TEMPLATE_EMAIL_BINDSENDCODE      = "tpl_email_bind_send_code";

    // 绑定邮件确认
    public static final String TEMPLATE_EMAIL_BINDCONFIRMCODE   = "tpl_email_bind_confirm_code";

    // 邮箱其他
    public static final String TEMPLATE_EMAIL_OTHERCODE         = "tpl_email_send_other_code";

    // 发送手机验证码
    public static final String TEMPLATE_SMS_SENDVALICODE        = "tpl_sms_send_valid_code";
}
