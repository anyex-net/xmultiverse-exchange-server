package com.anyex.apps.utils;

import com.anyex.apps.bean.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class KafkaUtil
{
    private static KafkaTemplate kafkaTemplate = SpringContext.getBean(KafkaTemplate.class);
    
    /**
     * 发送kafka消息，带回调函数
     * @param topic
     * @param msgType
     * @param message
     */
    public static void sendKafkaMessage(String topic, String msgType, String message)
    {
        kafkaTemplate.send(topic, msgType, message);
    }
}
