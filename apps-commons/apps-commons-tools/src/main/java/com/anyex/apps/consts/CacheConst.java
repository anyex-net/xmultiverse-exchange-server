package com.anyex.apps.consts;

/**
 * <p>File：CacheConst.java </p>
 * <p>Title: 缓存前缀声明 </p>
 * <p>Description: CacheConst </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class CacheConst
{
    private CacheConst()
    {
    }

    /**
     * 1分钟
     */
    public static final Integer ONE_MINUTE_CACHE_TIME      = 60;

    /**
     * The default interval: 3000 ms = 3 seconds.
     */
    public static final long    DEFAULT_INTERVAL           = 3000;

    /**
     * APP参数默认缓存时间(5分钟)
     */
    public static final Integer DEFAULT_CACHE_TIME         = 300;

    /**
     * 15分钟
     */
    public static final Integer FIFTEEN_MINUTE_CACHE_TIME  = 900;

    /**
     * 30分钟
     */
    public static final Integer THIRTY_MINUTE_CACHE_TIME   = 1800;

    /**
     * 60分钟
     */
    public static final Integer ONE_HOUR_CACHE_TIME        = 3600;

    /**
     * 12小时
     */
    public static final Integer TWELVE_HOUR_CACHE_TIME     = 43200;

    /**
     * 16小时
     */
    public static final Integer SIXTEEN_HOUR_CACHE_TIME    = 57600;

    /**
     * 24小时
     */
    public static final Integer TWENTYFOUR_HOUR_CACHE_TIME = 86400;

    public static final String  LOGIN_PERFIX               = "login:perf";

    /**
     * GOOGLE CODE
     */
    public static final String  GOOGLE_CODE_PERFIX         = "google:code";

    /**
     * 找回密码
     */
    public static final String  FIND_PASSWD_PERFIX         = "cache:findpass";

    /**
     * 找回密码
     */
    public static final String  CHANGE_PHONE_PERFIX        = "cache:change";

    /**
     * 注册密码
     */
    public static final String  REGISTER_PERFIX            = "cache:register";

    /**
     * 消息发送
     */
    public static final String  CACHE_SEND_SMS_PERFIX      = "message:phone";

    /**
     * 消息邮件
     */
    public static final String  CACHE_SEND_EMAIL_PERFIX    = "message:email";

    /**
     * 消息过期
     */
    public static final String  CACHE_EXPIRE_SMS_PERFIX    = "message:expire";

    /**
     * ADMIN会话对象
     */
    public static final String  ADMIN_SHIRO_CACHE_PREFIX   = "session:admin";

    /**
     * WEB会话对象
     */
    public static final String  WEB_SHIRO_CACHE_PREFIX     = "session:web";

    /**
     * 微信ticker
     */
    public static final String  WEIXIN_TICKET_PERFIX       = "wx|ticket|expire";

    /**
     * COOKIE
     */
    public static final String  ADMIN_COOKIE_ID            = "bid";

    public static final String  WEB_COOKIE_ID              = "sid";

    public static final String  WEB_IM_ID                  = "token";

    public static final String  APP_COOKIE_ID              = "aid";


    // 以下业务相关
    /**
     * redis分布式锁-游戏-游戏ID纬度
     */
    public static final String  REDISLOCK_GAME_GAME_PREFIX                                = "redislock:game:gameId:";
    /**
     * redis分布式锁-活动-一元夺宝活动ID纬度
     */
    public static final String  REDISLOCK_ACTIVITY_TREASUREHUNT_PREFIX                    = "redislock:activity:treasureHuntId:";
    /**
     * redis分布式锁-活动-半价商品活动ID纬度
     */
    public static final String  REDISLOCK_ACTIVITY_HOTDEALS_PREFIX                        = "redislock:activity:hotDealsId:";
    /**
     * redis分布式锁-钱包资产-账户ID纬度
     */
    public static final String  REDISLOCK_WALLETASSET_ACCOUNT_PREFIX                      = "redislock:walletasset:accountId:";

    public static final String  REDISLOCK_SIGNIN_ACCOUNT_PREFIX                           = "redislock:signin:accountId:";

    /**
     * 游戏开奖
     */
    public static final String  GAME_LOTTERY_GAME_PREFIX                                  = "game:lottery:gameId:";
}
