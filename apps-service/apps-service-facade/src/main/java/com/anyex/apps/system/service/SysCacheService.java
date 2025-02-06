package com.anyex.apps.system.service;

import com.anyex.apps.exception.BusinessException;

public interface SysCacheService
{
    /**
     * 清除指定缓存
     *
     * @param key
     * @throws BusinessException
     */
    void delete(String key) throws BusinessException;

    /**
     * 清除所有缓存
     *
     * @throws BusinessException
     */
    void cleanAll() throws BusinessException;

    /**
     * 清除Mybatis缓存
     *
     * @throws BusinessException
     */
    void cleanMybatis() throws BusinessException;

    /**
     * 清除会话缓存
     *
     * @throws BusinessException
     */
    void cleanSession() throws BusinessException;
}
