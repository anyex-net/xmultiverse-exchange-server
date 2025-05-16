package com.anyex.apps.enums;

import com.anyex.apps.bean.EnumDescribable;

/**
 * <p>File：CommonEnums.java </p>
 * <p>Title: CommonEnums </p>
 * <p>Description: CommonEnums </p>
 * <p>Copyright: Copyright (c) 15/9/15</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum CommonEnums implements EnumDescribable
{
    SUCCESS(200, "The operation successful"), // 操作成功
    FAIL(400, "The operation failed"), // 操作失败
    UNAUTHORIZED(401, "unauthorized"), // 未认证（签名错误）
    NOT_FOUND(404, "request url not fond"), // 接口不存在
    INTERNAL_SERVER_ERROR(500, "internal server error"), // 服务器内部错误
    SERVICE_BUSY_ERROR(501, "The service is busy, please try again later"), // 服务繁忙请稍后再试错误
    //
    ERROR_NO_PERMISSION(1000, "No permission to access"), // 没权限访问
    ERROR_PARSE_JSON(1001, "Parameter parsing failed"), // 参数解析失败
    ERROR_DATA_VALID_ERR(1002, "Data verification failed"), // 数据校验失败
    ERROR_DATA_NO_FOUND_ERR(1003, "The data does not exist"), // 数据不存在
    ERROR_OVERSHOOT_MAXIMUM_LIMIT(1004, "The request exceeds the maximum throttling limit"), // 请求超过最大流控限流
    ERROR_DB_UNIQUE_ERROR(1005, "Data Duplication Anomalies (Database Constraints)"), // 数据库约束异常、重复数据异常
    ERROR_BLACK_WHITE_IP_LIST(1100, "BlackWhite IP List Limit"), // IP黑白名单限制
    ERROR_BLACK_CSRF_LIMIT(1101, "Csrf Limit"), // Csrf限制
    //
    RISK_TRADE_OFF(1200, "The service suspend trading, please try again later"), // 暂停交易
    //
    ERROR_QRCODE_NOTSCANNED(1996, "QR code app not scanned"), // 二维码APP未扫描
    ERROR_QRCODE_NOTCONFIRM(1997, "QR code app not confirm"), // 二维码APP已扫描未确认
    ERROR_QRCODE_INVAILID(1998, "QR code is invalid"), // 二维码已经失效
    ERROR_NEED_QRCODE(1999, "Need QR code"), // 需要传入qrCode
    USER_NOT_LOGIN(2000, "The user is not logged in"), // 账户未登录
    NEED_POLICY_CHECK(2001, "Security Verification Policy"), // 安全验证策略
    ERROR_DATA_VALID(2002, "Data verification failed"), //数据校验失败
    ERROR_PARAMS_VALID(2003, "Parameter validation error"), // 参数验证错误
    ERROR_USER_NOT_EXIST(2004, "The user does not exist"), // 账户不存在(用户或密码错误)
    ERROR_FROZEN_USER(2004, "Login Exception,Please contact us."), // 账户被冻结
    ERROR_LOGIN_PASSWORD(2005, "Wrong password"), // 密码错误
    ERROR_LOGIN_TIMEOUT(2006, "Session timed out"), // 会话超时
    ERROR_VALID_CAPTCHA(2007, "The verification code is incorrect"), // 验证码错误
    ERROR_LOGIN_LOCK(2008, "The user is locked"), // 帐户已锁
    ERROR_CSRF_VALID(2009, "CSRF verification failed"), // CSRF验证失败
    ERROR_EMAIL_EXIST(2010, "The mailbox already exists"), // 邮箱已存在
    ERROR_ILLEGAL_REQUEST(2011, "Illegal Requests"), // 非法请求
    ERROR_DES_CHECK_FAILED(2012, "DES verification failed"), // DES校验失败
    ERROR_DATA_LENGTH_FAILED(2013, "Failed to verify the data length"), // 数据长度校验失败
    ERROR_AUTHER_FAILED(2014, "Authentication failed"), // 身份认证失败
    ERROR_DB_ACCESS_FAILED(2015, "Data access failed"), // 数据访问失败
    ERROR_EMAIL_VALID_FAILED(2016, "The email verification code failed"), // 邮箱验证码失败
    ERROR_SMSCODE_VALID_FAILED(2017, "SMS verification failed"), // 短信验证失败
    ERROR_EMAILCODE_VALID_FAILED(2017, "The email code verification failed"), // 邮箱码验证失败
    ERROR_PHONE_FORMAT_FAILED(2018, "The number format is incorrect"), //号码格式错误
    ERROR_EMAIL_FORMAT_FAILED(2019, "The mailbox is malformed"), // 邮箱格式错误
    ERROR_GA_VALID_FAILED(2020, "Google verification failed"), // 谷歌验证失败
    ERROR_TRADEPWD_VALID_FAILED(2021, "Failed to verify the trade password"), // 资金密码验证失败
    ERROR_TRADEPWD_VALID_NOEXIST(2022, "No trade password is set"), // 未设置资金密码
    ERROR_SESSION_TIME_OUT(2023, "The session has expired"), // 会话已过期
    ERROR_SESSION_TIME_OUT2(2023, "The login has expired, please log in again"), // 登录已失效,请重新登录
    ERROR_GT_DAY_MAX_AMT(2024, "The maximum limit for the day has been exceeded"), // 已超过当天最大限额
    ERROR_GT_MAX_AMT(2025, "The maximum limit has been exceeded"), // 已超过最大限额
    ERROR_MOBILE_NOT_BIND(2026, "mobile is not bound"), //手机未绑定
    ERROR_GA_NOT_BIND(2026, "GA is not bound"), //GA未绑定
    ERROR_MOBILEORGA_NOT_BIND(2026, "mobile or GA is not bound"), //手机或GA未绑定
    ERROR_REGISTER_EXIST(2027, "The user has been registered"), //账号已被注册
    WAIT_ONE_MINUTE(2028, "Review in a minute"), //一分钟后再评论
    WAIT_ONE_HOUR(2029, "Newly registered users will review in 1 hour"), //新注册用户1小时后再评论
    SWITCH_ROLES(2030, "Requires the administrator to switch roles when working on Admin and Website"), //要求管理员在Admin和Website操作时角色切换
    ERROR_PHONE_BIND(2031, "The mobile phone number has been bound"), //手机号已经被绑定
    ERROR_EMAIL_BIND(2031, "The email has been bound"), //邮箱已经被绑定
    BIND_YOUR_PHONE(2032, "When logging in to a third-party app, please bind your mobile phone number"), //app端第三方登录时,请绑定手机号
    ERROR_PASSWORD_TYPE(2033,"Please use a combination of numbers and letters from 6-12 for your password"), //密码请使用6-12的数字和字母组合
    ERROR_BINDPHONE_VALID_FAILED(2034, "Please enter a valid original mobile phone number"),//手机号验证失败 请输入正确的原手机号
    ERROR_AFS_VALID_FAILED(2035, "Slider validation failed"), // AFS失败 滑块验证失败
    ERROR_MESSAGE_SENT(2036, "The message has been sent, please try again later"), // Message sent, please try again later
    //
    RISK_ENABLE_BALANCE_NOTAVAILABLE(3000, "Insufficient balance"), // 可用资金余额不足
    RISK_ENABLE_QUANTITY_NOTAVAILABLE(3001, "Insufficient quantity"), // 可用数量余额不足
    RISK_CHECK_ASSETBALANCE_NOTEQ_ASSETFLOWAFTERBALANCE(3002, "Balance of the asset is not eq the balance after the occurrence of the asset flow"), // 资产余额与资产流水发生后余额不相同等
    ERROR_WITHDRAW_LIMIT_ACCOUNTID(3003, "Your account %s has accumulated withdrawals over the limit %s"), // 平台账户累计限额
    ERROR_WITHDRAW_LIMIT_ACCOUNTNO(3004, "Your receiving account %s has accumulated withdrawals that exceed your limit %s"), // 收款账户累计限额
    ERROR_WITHDRAW_INTERFACE(3100, "The withdrawal interface is abnormal"), // 提现接口异常//
    ERROR_REQUEST_EXPIRED(5007, "The request has expired"), // request请求已过期
    ERROR_IMAGE_TYPE(5008, "Unsupported picture formats"), // 不支持的图片格式
    ERROR_DEVICE_TYPE(5101, "Unknown terminal type"), // 未知的终端类型
    ERROR_DEVICE_TYPE_UNSUPPORT(5102, "Unsupported terminal type"), // 不支持的终端类型
    ERROR_VERSION_TOLOW(5103,"The current version is too low and needs to be upgraded!"), // 版本太低需要升级
    ERROR_VERSION_TOUPDATE(5108,"The current version is too low and must upgrade!"), //版本不支持，强制升级
    ERROR_WEIXINGRANT_FAIL(5019,"Wechat authorization fail"), //微信授权失败
    ERROR_QUOTATION_NOT_OPEN(5020,"The market is temporarily closed~"), //行情暂时未开放
    ERROR_EXTERNALINTERFACE_NOT_OPEN(5021,"The external interface is not available"), //外部接口不通
    ERROR_DEPOSIT_AMOUNT_ERR(5022, "Deposit amount error"), // 充值金额错误
    ERROR_DATA_NO_EXIST(5023, "Data does not exist"), // 数据不存在
    ERROR_STATUS(5024, "Data status error"), // 数据状态错误
    ERROR_BUSINESS(5025, "Business error"), // 业务异常 常用于 状态和分类错误时
    ERROR_AMOUNT_RANGE(5026, "amount [%s,%s]"), // 金额范围错误

    ERROR_LOCATION(5027, "Please open your location permissions"), // 未知坐标


    ERROR_HAS_BEAN_SIGNIN(5028, "Signed in"), // 已签到
    //
    //admin端从8200开始
    ERROR_NOTICE_BEYOND(8200,"发布数量超过限制"),
    ERROR_EXIST_SUBNODE(8201,"存在子节点，当前节点无法删除!"),



    ERROR_MOBILE_VALID_FAILED(9001, "The phone number verification failed"),
//    ERROR_USER_NOT_EXIST(9002, "The account is not exist"),
    FOLLOW_SELF_ERR(9003, "flowed yourself error"),
    NO_AUTH_OR_DELETED(9004, "NO AUTHRIZED OR DELETED"),

    ERROR_USER_CERT_STATE_ALREADY_CERT(10001, "The user certState already cert"),//戶认证状态已被认证
    ERROR_USER_NOT_CERT(10002, "The user not cert"), //用户认证状态未认证
    ERROR_USER_CERT_STATE_NOT_CERT_INST_SPV(10003, "The user certState not certInstSpv"), //用户认证状态不是SPV发起人认证
    ERROR_USER_CERT_STATE_NOT_PURCHASE(10004, "The user certState not purchase"), //该用户的状态不能进行申购
    ERROR_RWA_INST_SPV_PRODUCT_OPERATION_DATE_ERROR(10005, "The operation date of the RWA institution SPV product is not within the specified period"),
    ERROR_RWA_USER_BALANCE_NOT_FOUND(10006, "The Rwa user balance not found"), //用户资产不存在
    ERROR_RWA_RAISE_USER_BALANCE_NOT_FOUND(10007, "The Rwa raise user balance not found"), //募集用户资产不存在
    ERROR_RWA_USER_INSUFFICIENT_AVAILABLE_BALANCE(10008, "insufficient available balance"),// 资产可用余额不足
    ERROR_RWA_INST_SPV_PRODUCT_NOT_FOUND(10009, "The RWA institution SPV product not found"),// RWA产品不存在
    ERROR_RWA_USER_PURCHASE_AMOUNT_OVER_LIMIT(10010, "The purchase amount exceeds the limit"), //申购金额超过限制
    ERROR_RWA_USER_ASSET_AMOUNT_OVER_LIMIT(10011, "The asset amount exceeds the limit"),
    ERROR_RWA_CONTRACT_DIVIDEND_DEPOSIT_NOT_ENOUGH(10012, "The deposit amount is not enough"), // 合约分红的保证金不足
    ERROR_RWA_TOKEN_MINT_FAIL(10013, "The token mint fail"),// token mint失败
    ERROR_RWA_TOKEN_CONTRACT_ADDRESS_NOT_FOUND(10014, "The token contract address not found"), // token合约地址不存在
    ;
    public Integer code;

    public String  message;

    CommonEnums(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据状态码获取状态码描述
     *
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (CommonEnums c : CommonEnums.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer getCode()
    {
        return this.code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
