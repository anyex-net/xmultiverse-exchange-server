package com.anyex.apps.config;

import com.anyex.apps.database.DynamicDataSource;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * DataSourceConfig
 * <p>File: DataSourceConfig.java </p>
 * <p>Title: DataSourceConfig </p>
 * <p>Description: DataSourceConfig </p>
 * <p>Copyright: Copyright (c) 2019-01-28</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(SqlSessionFactory.class)
public class DataSourceConfig
{
    /**
     * 数据源1
     *
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master()
    {
        return new ClustomDataSource();
    }
    
    /**
     * 数据源2
     *
     * @return
     */
    @Bean(name = "slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1()
    {
        return new ClustomDataSource();
    }
    
    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DynamicDataSource(DataSource master, DataSource slave1)
    {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setMaster(master);
        dynamicDataSource.setSlaves(Lists.newArrayList(slave1));
        return dynamicDataSource;
    }
    
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(DynamicDataSource DynamicDataSource)
    {
        return new DataSourceTransactionManager(DynamicDataSource);
    }
}
