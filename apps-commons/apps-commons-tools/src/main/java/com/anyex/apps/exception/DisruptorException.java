package com.anyex.apps.exception;

import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * DisruptorException
 * <p>File: DisruptorException.java </p>
 * <p>Title: DisruptorException </p>
 * <p>Description: DisruptorException </p>
 * <p>Copyright: Copyright (c) 2018-12-04</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class DisruptorException implements ExceptionHandler
{
    /**
     * 运行过程中发生时的异常
     * @param ex
     * @param sequence
     * @param event
     */
    @Override
    public void handleEventException(Throwable ex, long sequence, Object event)
    {
        log.error("process data error sequence ==[{}] event==[{}] ,ex ==[{}]", sequence, event.toString(), ex.getMessage());
    }
    
    /**
     * 启动时的异常
     * @param ex
     */
    @Override
    public void handleOnStartException(Throwable ex)
    {
        log.error("start disruptor error ==[{}]!", ex.getMessage());
    }
    
    /**
     * 关闭时的异常
     * @param ex
     */
    @Override
    public void handleOnShutdownException(Throwable ex)
    {
        log.error("shutdown disruptor error ==[{}]!", ex.getMessage());
    }
}
