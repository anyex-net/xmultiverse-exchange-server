/*
 * @(#)ZttxConst.java 2015-4-14 下午2:02:23
 * Copyright 2015 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.consts;

/**
 * <p>File：GlobalConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-14 下午2:02:23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class GlobalConst
{
    private GlobalConst()
    {// 防止实例化
    }

    /**
     * 分割符
     */
    public static final char    SEPARATOR             = ':';

    public static final String  DEFAULT_COUNTRY       = "92";

    /**
     * 默认语言
     */
    public static final String  DEFAULT_LANG          = "en_US";

    /**
     * 默认值
     */
    public static final String  DEFAULT_VALUE         = "object";

    /**
     * 官方账户NickName (哔哔News)
     */
    public static final String  DEFAULT_NICKNAME      = "哔哔News";

    /**
     * 默认UNID
     */
    public static final Long    DEFAULT_UNID          = 10000L;

    /**
     * 请求类型
     */
    public static final String  POST                  = "post";

    public static final String  GET                   = "get";

    /**
     * 当前页面
     */
    public static final Integer DEFAULT_CURRENT_PAGE  = 1;

    /**
     * 分页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE     = 10;

    /**
     * 分页起始大小
     */
    public static final Integer DEFAULT_START_INDEX   = 0;

    /**
     * 批处理大小
     */
    public static final Integer DEFAULT_BATCH_SIZE    = 1000;

    /**
     * 默认限制次数
     */
    public static final Integer DEFAULT_REQUEST_LIMIT = 10;

    /**
     * 模块
     */
    public static final String  AUTH                  = "/auth";

    public static final String  IM                    = "/openim";

    public static final String  SOCIAL                = "/social";

    public static final String  SYSTEM                = "/system";

    public static final String  COMMON                = "/common";

    public static final String  USER                  = "/user";

    public static final String  ACCOUNT               = "/account";

    public static final String  MOMENT                = "/moment";

    public static final String  BUSINESS              = "/business";

    public static final String  ADVERT                = "/advert";

    public static final String  COMMENT               = "/comment";

    public static final String  SEARCH                = "/search";

    public static final String  LIVE                  = "/live";

    public static final String  REPLY                 = "/reply";

    public static final String  NOTICE                = "/notice";

    public static final String  PRAISE                = "/praise";

    public static final String  FAVORITE              = "/favorite";

    public static final String  FLASH                 = "/flash";

    public static final String  OAUTH                 = "/oauth";

    public static final String  NEWS                  = "/news";

    public static final String  VOTE                  = "/vote";

    public static final String  VIDEO                 = "/video";

    public static final String  IM_ALLOW_SEND_MSG_NOT_FRIEND                        = "1";
    public static final String  IM_NEED_INVITATION_CODE_REGISTER                    = "0";

    // 币种
    public static final String  CURRENCY_USD                                        = "USD";
    public static final String  CURRENCY_USDT                                       = "USDT";
    public static final String  CURRENCY_PKR                                        = "PKR";

    // 状态(成功success、处理中pending、失败failed)
    public static final String  STATUS_SUCCESS                                      = "success";
    public static final String  STATUS_PENDING                                      = "pending";
    public static final String  STATUS_FAILED                                       = "failed";

    // 业务分类 收入revenue、支出expend
    public static final String  BUSINESS_CATEGORY_REVENUE                           = "revenue";
    public static final String  BUSINESS_CATEGORY_EXPEND                            = "expend";

    // 业务类型 充值deposit、提现withDraw
    public static final String  BUSINESS_TYPE_DEPOSIT                               = "deposit";
    public static final String  BUSINESS_TYPE_WITHDRAW                              = "withDraw";
    public static final String  BUSINESS_TYPE_WITHDRAW_ROLLBACK                     = "withDrawRollBack";

    public static final String  BUSINESS_TYPE_INVITE_REWARD                         = "inviteReward";

    public static final String  BUSINESS_TYPE_SIGNIN_REWARD                         = "signInReward";

    public static final String  BUSINESS_TYPE_SOCIAL_TIPGIFT                        = "socialTipGift"; // 社交打赏礼物

    public static final String  BUSINESS_TYPE_GAME_SPIN                             = "gameSpin";
    public static final String  BUSINESS_TYPE_GAME_SPIN_REWARD                      = "gameSpinReward";
    public static final String  BUSINESS_TYPE_ACTIVITY_TREASUREHUNT                 = "activityTreasureHunt";
    public static final String  BUSINESS_TYPE_ACTIVITY_TREASUREHUNT_REWARD          = "activityTreasureHuntReward";
    public static final String  BUSINESS_TYPE_ACTIVITY_HOTDEALS                     = "activityHotDeals";
    public static final String  BUSINESS_TYPE_ACTIVITY_HOTDEALS_BALANCEPAYMENT      = "activityHotDealsBalancePayment";
    public static final String  BUSINESS_TYPE_ACTIVITY_HOTDEALS_REWARD              = "activityHotDealsReward";
    public static final String  BUSINESS_TYPE_ACTIVITY_HOTDEALS_REFUND_REWARD       = "activityHotDealsRefundReward";
    public static final String  BUSINESS_TYPE_ASSET_ADJUSTADD                       = "assetAdjustAdd";// 钱包资产强增
    public static final String  BUSINESS_TYPE_ASSET_ADJUSTSUB                       = "assetAdjustSub";// 钱包资产强减

    public static final String  BUSINESS_TYPE_ASSET_SENDGITF                        = "assetGiftSend";// 发送礼物
    public static final String  BUSINESS_TYPE_ASSET_GETGIFT                         = "assetGiftGet";// 接收礼物

    public static final String  BUSINESS_TYPE_FEE                                   = "fee";

    // 活动类型(TreasureHunt、HotDeals)
    public static final String  ACTIVITY_TYPE_TREASUREHUNT                          = "TreasureHunt";
    public static final String  ACTIVITY_TYPE_HOTDEALS                              = "HotDeals";

    // 操作类型(浏览browse、favorite收藏、praise点赞、comment评论)
    public static final String  ACTIVITY_OPERTYPE_BROWSE                            = "browse";
    public static final String  ACTIVITY_OPERTYPE_FAVORITE                          = "favorite";
    public static final String  ACTIVITY_OPERTYPE_LIKE                              = "like";
    public static final String  ACTIVITY_OPERTYPE_COMMENT                           = "comment";

    // 账户类型
    public static final String  PAYMENT_ACCOUNTTYPE_BANK                            = "BANK";
    public static final String  PAYMENT_ACCOUNTTYPE_WALLET                          = "WALLET";

    // 账户实际类型
    public static final String  PAYMENT_ACCOUNTACTUALTYPE_BANK                      = "BANK";
    public static final String  PAYMENT_ACCOUNTACTUALTYPE_EASYPAISA                 = "EASYPAISA";
    public static final String  PAYMENT_ACCOUNTACTUALTYPE_JAZZCASH                  = "JAZZCASH";

    // 收付款渠道
    public static final String  PAYMENT_CHANNEL_GLOBALPAY                           = "GlobalPay";
    public static final String  PAYMENT_CHANNEL_WIVPAY                              = "WivPay";

    // GP代付业务异常 KEY
    public static final String  PAYMENT_CHANNEL_GLOBALPAY_SYS_ERROR                 = "GlobalPaySysError";

    // GP代付业务异常 交易暂缓20分钟
    public static final Integer  PAYMENT_CHANNEL_GLOBALPAY_SYS_ERROR_LOCKTIME       = 1200;



    /**
     * 操作频率限制
     * 默认30次
     */
    public static final Integer  LOCK_INTERVAL_COUNT            = 30;

    /**
     * 操作标识
     */
    public static final String   OP                             = "op";

    /**
     * 消息
     */
    public static final String   MESSAGE                        = "message";

    /**
     * 登陆操作
     */
    public static final String   OP_LOGIN                       = "login";

    /**
     * 找回密码操作
     */
    public static final String   OP_FINDPWD                     = "findpwd";

    /**
     * 帐户模块
     */
    public static final String   OP_ACCOUNT_BIND_PHONE          = "account:bindPhone";

    public static final String   OP_ACCOUNT_BIND_EMAIL          = "account:bindEmail";

    /**
     * 强增强减模块
     */
    public static final String   OP_FUND_ADJUST                 = "fundAdjust";

    /**
     * 撮合交易模块
     */
    public static final String   OP_ENTRUSTVCOOINMONEY          = "entrustVcoinMoney";

    /**
     * 资金流水模块
     */
    public static final String   OP_FUND_CURRENT                = "fundCurrent";

    /**
     * 帳戶資產
     */
    public static final String   OP_FUND_ASSET                  = "fundAsset";

    /**
     * 提币申请模块-用于短信或GA次数判定
     */
    public static final String   OP_RAISE_DO_RAISE              = "raise:doRaise";

    /**
     * 内部行情撮合成交价--用于计算行情涨跌幅
     */
    public static final String   OP_RTQUOTATIONINFO             = "RtQuotationInfo";

}
