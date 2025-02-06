package com.anyex.apps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * KafkaConfig
 * <p>File: KafkaConfig.java </p>
 * <p>Title: KafkaConfig </p>
 * <p>Description: KafkaConfig </p>
 * <p>Copyright: Copyright (c) 2018-12-11</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(KafkaTemplate.class)
public class KafkaConfig
{
    @Autowired(required = false)
    private KafkaPropExtra kafkaProperties;
    
    /**
     * 默认订阅
     * @param configurer
     * @param kafkaConsumerFactory
     * @return
     */
    @Bean("kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory)
    {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        return factory;
    }
    
    /**
     * 批量订阅
     * @param configurer
     * @param kafkaConsumerFactory
     * @return
     */
    @Bean("kafkaMultiListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaMultiListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory)
    {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        // 补充批量处理参数
        factory.setBatchListener(kafkaProperties.getConsumerExtra().getBatchListener());
        return factory;
    }
}
