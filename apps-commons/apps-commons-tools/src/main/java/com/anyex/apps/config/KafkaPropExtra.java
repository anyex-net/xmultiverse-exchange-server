package com.anyex.apps.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * KafkaPropExtra
 * <p>File: KafkaPropExtra.java </p>
 * <p>Title: KafkaPropExtra </p>
 * <p>Description: KafkaPropExtra </p>
 * <p>Copyright: Copyright (c) 2018-12-14</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Primary
@Configuration("KafkaPropExtra")
@ConditionalOnClass(KafkaTemplate.class)
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaPropExtra extends KafkaProperties
{
    private final ConsumerExtra consumerExtra = new ConsumerExtra();
    
    public static class ConsumerExtra
    {
        private Boolean batchListener = false;
        
        public Boolean getBatchListener()
        {
            return batchListener;
        }
        
        public void setBatchListener(Boolean batchListener)
        {
            this.batchListener = batchListener;
        }
    }
    
    public ConsumerExtra getConsumerExtra()
    {
        return this.consumerExtra;
    }
}
