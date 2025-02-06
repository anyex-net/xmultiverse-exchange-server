package com.anyex.apps.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.anyex.apps.config.GlobalProperies;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

/**
 * 极光推送客户端
 * <p>File：JpushClient.java</p>
 * <p>Title: JpushClient</p>
 * <p>Description: JpushClient</p>
 * <p>Copyright: Copyright (c) 2019/10/24</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Setter
public class JpushUtils
{
    private GlobalProperies properies;
    
    private JPushClient     jPushClient;
    
    /**
     * 推送给设备标识参数的用户
     *
     * @param registrationId     设备标识
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public boolean sendToRegistrationId(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam)
    {
        boolean result = false;
        try
        {
            PushPayload pushPayload = buildPushObject_all_registrationId_alertWithTitle(registrationId, notification_title, msg_title, msg_content, extrasparam);
            PushResult pushResult = getjPushClient().sendPush(pushPayload);
            if (pushResult.getResponseCode() == 200)
            {
                result = true;
            }
        }
        catch (APIConnectionException e)
        {
            e.printStackTrace();
        }
        catch (APIRequestException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 发送给所有安卓用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public boolean sendToAllAndroid(String notification_title, String msg_title, String msg_content, Map<String, String> extrasparam)
    {
        boolean result = false;
        try
        {
            PushPayload pushPayload = buildPushObject_android_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam, null);
            System.out.println(pushPayload);
            PushResult pushResult = getjPushClient().sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200)
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 发送给所有IOS用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public boolean sendToAllIos(String notification_title, String msg_title, String msg_content, Map<String, String> extrasparam)
    {
        boolean result = false;
        try
        {
            PushPayload pushPayload = buildPushObject_ios_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam, null);
            System.out.println(pushPayload);
            PushResult pushResult = getjPushClient().sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200)
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有用户
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @param alias
     * @return
     */
    public boolean sendToAllAndroidAndIos(String notification_title, String msg_title, String msg_content, Map<String, String> extrasparam, List<String> alias)
    {
        boolean result = false;
        try
        {
            PushPayload pushPayload = buildPushObject_android_and_ios(notification_title, msg_title, msg_content, extrasparam, alias);
            System.out.println(pushPayload);
            PushResult pushResult = getjPushClient().sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200)
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     *
     * @param notification_title    大标题
     * @param msg_title
     * @param msg_content   消息内容
     * @param extrasparam 推送给App参数 {type: 1, id: 123456789}
     *                    type : 1 文章
     *                    type : 2 快讯
     *                    type : 3 消息
     * @param alias 指定发送的账户集合
     * @return
     */
    @Async
    public PushPayload buildPushObject_android_and_ios(String notification_title, String msg_title, String msg_content, Map<String, String> extrasparam, List<String> alias)
    {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                .setNotification(Notification.newBuilder().setAlert(notification_title)
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(msg_content).setTitle(notification_title)
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extrasparam).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                // 传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(IosAlert.newBuilder().setTitleAndBody(msg_title, "", msg_content).setActionLocKey("PLAY").build())
                                // 直接传alert
                                // 此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                // 此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("happy")
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extrasparam)
                                // 此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)
                                .build())
                        .build())
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtras(extrasparam).build()).setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(true)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400).build())
                .build();
    }
    
    private PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId, String notification_title, String msg_title, String msg_content,
            String extrasparam)
    {
        System.out.println("----------buildPushObject_all_all_alert");
        // 创建一个IosAlert对象，可指定APNs的alert、title等字段
        // IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();
        return PushPayload.newBuilder()
                // 指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                // 指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.registrationId(registrationId))
                // jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        // 指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(notification_title).setTitle(notification_title)
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key", extrasparam).build())
                        // 指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                // 传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notification_title)
                                // 直接传alert
                                // 此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                // 此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("happy")
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("iosNotification extras key", extrasparam)
                                // 此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // 取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)
                                .build())
                        .build())
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtra("message extras key", extrasparam).build())
                .setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400).build())
                .build();
    }
    
    public PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, Map<String, String> extrasparam,
            List<String> alias)
    {
        System.out.println("----------buildPushObject_android_registrationId_alertWithTitle");
        return PushPayload.newBuilder()
                // 指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                // 指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                // jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        // 指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(msg_content).setTitle(notification_title)
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extrasparam).build())
                        .build())
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtras(extrasparam).build()).setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400).build())
                .build();
    }
    
    public PushPayload buildPushObject_ios_all_alertWithTitle(String notification_title, String msg_title, String msg_content, Map<String, String> extrasparam,
            List<String> alias)
    {
        System.out.println("----------buildPushObject_ios_registrationId_alertWithTitle");
        return PushPayload.newBuilder()
                // 指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.ios())
                // 指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                // jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        // 指定当前推送的android通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                // 传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(IosAlert.newBuilder().setTitleAndBody(notification_title, "", msg_content).setActionLocKey("PLAY").build())
                                // 直接传alert
                                // 此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                // 此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("happy")
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extrasparam)
                                // 此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)
                                .build())
                        .build())
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtras(extrasparam).build()).setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400).build())
                .build();
    }
    
    public JPushClient getjPushClient()
    {
        if (null == jPushClient)
        {// 初始化
            jPushClient = new JPushClient(properies.getJPush().getSecret(), properies.getJPush().getAppKey());
        }
        return jPushClient;
    }
}
