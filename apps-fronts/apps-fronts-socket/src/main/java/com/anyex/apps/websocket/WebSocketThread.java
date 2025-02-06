//package com.anyex.apps.websocket;
//
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.bean.SpringContext;
//import com.anyex.apps.jyfzgl.service.JyfzFireengineLocationsService;
//import com.anyex.apps.location.service.JunFengService;
//import com.anyex.apps.location.service.YinZhouDetachmentService;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author
// * @Date 2023/8/9 13:22
// */
//@Slf4j
//@Component
//public class WebSocketThread implements Runnable {
//
//    private YinZhouDetachmentService yinZhouDetachmentService;
//
//    private JunFengService junFengService;
//
//    private JyfzFireengineLocationsService jyfzFireengineLocationsService;
//
//    private Integer count=0;
//
//    @SneakyThrows
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                try {
//                    //获取推送内容
//                    if (WebSocket.vehicleCodings.size() > 0) {
//                        if (yinZhouDetachmentService == null) {
//                            yinZhouDetachmentService = SpringContext.getBean(YinZhouDetachmentService.class);
//                        }
//                        HashMap<String, String> pushContent = yinZhouDetachmentService.getPushContent(WebSocket.vehicleCodings);
//                        for (Map.Entry<String, String> entry : pushContent.entrySet()) {
//                            if (StringUtils.isNotEmpty(entry.getValue())) {
//                                HashMap<String, Object> map = new HashMap<>();
//                                map.put("list", entry.getValue());
//                                map.put("type", "0");
//                                WebSocket.sendMessageByUserId(entry.getKey(), JSONObject.toJSONString(map));
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                }
//                //假数据推送
////                if (WebSocket.lifeSupportSystem.size() > 0) {
////                    for (Map.Entry<String, List<String>> entry : WebSocket.lifeSupportSystem.entrySet()) {
////                        HashMap<String, Object> map = new HashMap<>();
////                        List<JyfzFireAttackDeviceDynamicEntity> list = new ArrayList<>();
////                        JyfzFireAttackDeviceDynamicEntity dynamic = new JyfzFireAttackDeviceDynamicEntity();
////                        dynamic.setId(10001L);
////                        dynamic.setLongitude(String.valueOf(121.544073 + (Math.random() * 2 - 1) / 1000));
////                        dynamic.setLatitude(String.valueOf(29.8144 + (Math.random() * 2 - 1) / 1000));
////                        dynamic.setAltitude(String.valueOf(168 + (Math.random() * 2 - 1) / 10));
////                        list.add(dynamic);
////                        JyfzFireAttackDeviceDynamicEntity dynamic1 = new JyfzFireAttackDeviceDynamicEntity();
////                        dynamic1.setId(10002L);
////                        dynamic1.setLongitude(String.valueOf(121.544073 + (Math.random() * 2 - 1) / 1000));
////                        dynamic1.setLatitude(String.valueOf(29.8144 + (Math.random() * 2 - 1) / 1000));
////                        dynamic1.setAltitude(String.valueOf(168 + (Math.random() * 2 - 1) / 10));
////                        list.add(dynamic1);
////                        map.put("list",list);
////                        map.put("type","1");
////                        WebSocket.sendMessageByUserId(entry.getKey(), JSONObject.toJSONString(map));
////                    }
////                }
//                try {
//                    if (junFengService == null) {
//                        junFengService = SpringContext.getBean(JunFengService.class);
//                    }
//                    //沃尔沃数据推送
//                    JSONObject jsonObject = junFengService.selectCeWzxx();
//                    if (WebSocket.volvoPush.size() > 0) {
//                        JSONObject jsonObject1 = junFengService.selectCeSzxx();
//                        for (Map.Entry<String, String> entry : WebSocket.volvoPush.entrySet()) {
//                            HashMap<String, Object> map = new HashMap<>();
//                            map.put("list", jsonObject);
//                            map.put("szxx", jsonObject1);
//                            map.put("type", "3");
//                            WebSocket.sendMessageByUserId(entry.getKey(), JSONObject.toJSONString(map));
//                        }
//                    }
//                    if (count>=30){
//                        //进行入库操作
//                        count=0;
//                        if (jyfzFireengineLocationsService == null) {
//                            jyfzFireengineLocationsService = SpringContext.getBean(JyfzFireengineLocationsService.class);
//                        }
//                        jyfzFireengineLocationsService.saveCoordinate(jsonObject);
//                    }else {
//                        count++;
//                    }
//                } catch (Exception e) {
//
////                log.info("当前时间：{}", LocalTime.now());
////                log.info("当前时间：{}", LocalTime.now());
//                }
//            }catch (Exception e){
//
//            }finally {
//                Thread.sleep(1000 * 2);
//            }
//        }
//    }
//}
