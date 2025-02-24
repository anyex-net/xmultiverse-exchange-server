package com.anyex.exchange.viabtc.config;

public class ViabtcConfig {
    // base
    public static String baseUrl = "http://114.55.147.64:8080/"; //  WiabtcEx服务器地址

    // Asset API
    public static String asset_balance_query = "balance.query"; //  注册云信IM账号
    public static String userTokenUrl = baseUrl+"user/update.action"; //  刷新Token 指定token
    public static String userrefreshTokenUrl = baseUrl+"user/refreshToken.action"; //  刷新Token 不指定token
    public static String userBlockUrl = baseUrl+"user/block.action"; //  封禁账户
    public static String userMuteUrl = baseUrl+"user/mute.action"; //  账号全局禁言
    public static String userMuteModuleUrl = baseUrl+"user/muteModule.action"; //  账号功能模块禁言
    public static String usersetDonnopUrl = baseUrl+"user/setDonnop.action"; //  设置移动端是否需要推送(桌面端在线时)
    public static String userOnlineStatusUrl = baseUrl+"user/userOnlineStatus.action"; //  批量查询账号在线状态
}
