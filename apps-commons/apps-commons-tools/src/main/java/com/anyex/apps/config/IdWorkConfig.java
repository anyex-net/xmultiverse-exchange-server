package com.anyex.apps.config;

import com.anyex.apps.bean.IdWorker;
import com.anyex.apps.utils.PropertiesUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * IdWorkConfig
 * <p>File: IdWorkConfig.java </p>
 * <p>Title: IdWorkConfig </p>
 * <p>Description: IdWorkConfig </p>
 * <p>Copyright: Copyright (c) 2019-07-16</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Configuration
public class IdWorkConfig
{
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public IdWorker idWorker()
    {
        // ID生成器实例化,如果未指定将采用默认的生成策略。
        IdWorker idWorker;
        String property = System.getProperty("idWork");
        if (StringUtils.isNotBlank(property))
        {
            PropertiesUtils properties = new PropertiesUtils(property);
            Long workerId = properties.getLong("serial.number.workerId");
            Long centerId = properties.getLong("serial.number.centerId");
            idWorker = new IdWorker(workerId, centerId);
            log.info("IdWorker配置说明（centerId:" + centerId + " workerId:" + workerId + ")");
        }
        else
        {
            log.info("IdWorker 未指定配置文件，采用默认配置（1,1）");
            idWorker = new IdWorker(1, 1);
        }
        return idWorker;
    }
}
