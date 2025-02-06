package com.anyex.apps.websocket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
        System.out.println("启动多线程");
//        new Thread(new WebSocketThread()).start();
    }

    //记录连接的客户端
    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    //记录所有车辆列表定时推送
    public static Map<String,List<String>> vehicleCodings=new HashMap<>();
    //生命保障系统
    public static Map<String,List<String>> lifeSupportSystem=new HashMap<>();
    //大屏推送所有数据
    public static Map<String,String> largeScreen=new HashMap<>();
    //沃尔沃车辆定位
    public static Map<String,String> volvoPush=new HashMap<>();

    /**
     * 连接成功后调用的方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        clients.put(userId, session);
        log.info(userId + "连接开启！");
    }

    /**
     * 连接关闭调用的方法
     */
    @SneakyThrows
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        log.info("连接断开userId：{} 的连接",userId);
        clients.remove(userId);
        vehicleCodings.remove(userId);
        lifeSupportSystem.remove(userId);
        largeScreen.remove(userId);
        clients.remove(userId);
        volvoPush.remove(userId);
    }

    /**
     * 判断是否连接的方法
     * @return
     */
    public static boolean isServerClose() {
        if (WebSocketServer.clients.values().size() == 0) {
            log.info("已断开");
            return true;
        }else {
            log.info("已连接");
            return false;
        }
    }

    /**
     * 发送给所有用户
     * @param message
     */
    public static void sendMessage(String message){
        for (Session session1 : WebSocketServer.clients.values()) {
            try {
                session1.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据用户id发送给某一个用户
     * **/
    @SneakyThrows
    public static void sendMessageByUserId(String userId, String message) {
        if (!StringUtils.isEmpty(userId)) {
            Session session = clients.get(userId);
            try {
                session.getBasicRemote().sendText(message);
//                log.info("发送成功：{}  内容：{}",userId,message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("userId") String userId) {
//        log.info("收到来自窗口:{}:的信息:{}",userId,message);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(message)){
            JSONObject parsed = JSONObject.parseObject(message);
            switch (parsed.getString("type")){
                case "0":
//                    log.info("订阅消防车定位userId:{}",parsed.getString("userId"));
                    vehicleCodings.put(parsed.getString("userId"), JSONArray.parseArray(parsed.getString("indexCodes"),String.class));
                    break;
                case "1":
//                    log.info("订阅生命保障定位userId:{}",parsed.getString("userId"));
                    lifeSupportSystem.put(parsed.getString("userId"), JSONArray.parseArray(parsed.getString("ids"),String.class));
                    break;
                case "2":
//                    log.info("大屏所有数据推送userId:{}",parsed.getString("userId"));
                    largeScreen.put(parsed.getString("userId"),parsed.getString("userId"));
                    break;
                case "3":
//                    log.info("沃尔沃数据推送userId:{}",parsed.getString("userId"));
                    volvoPush.put(parsed.getString("userId"),parsed.getString("userId"));
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 发生错误时的回调函数
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        log.info("错误");
        error.printStackTrace();
    }
}
