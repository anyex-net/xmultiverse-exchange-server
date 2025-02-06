package com.anyex.apps.handler;

import com.alipay.sofa.rpc.core.exception.SofaRouteException;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.exception.LimitAccessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler
{
    /**
     * 并发限制异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = LimitAccessException.class)
    public JsonMessage handleLimitAccessException(LimitAccessException e)
    {
        log.error(e.getLocalizedMessage());
        return new JsonMessage(401, e.getLocalizedMessage());
    }
    
    /**
    * 业务异常控制
    * @param e
    * @return
    */
    @ExceptionHandler(value = BusinessException.class)
    public JsonMessage handleBusinessException(BusinessException e)
    {
        log.error(e.getLocalizedMessage());
        return new JsonMessage(e.getError(), e.getObject());
    }
    
    /**
     * 安全认证异常控制
     * @param e
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public JsonMessage handleAuthenticationException(AuthenticationException e)
    {
        log.error(e.getLocalizedMessage());
        return new JsonMessage(CommonEnums.ERROR_AUTHER_FAILED);
    }
    
    /**
    * 唯一约束异常
    * @param e
    * @return
    */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public JsonMessage handleDuplicateKeyException(DuplicateKeyException e)
    {
        log.error(e.getLocalizedMessage());
        return new JsonMessage(CommonEnums.DB_UNIQUE_ERROR, e.getCause().getLocalizedMessage());
    }
    
    /**
    * SOFA异常处理
    * @param e
    * @return
    */
    @ExceptionHandler(value = SofaRouteException.class)
    public JsonMessage handleSofaRouteException(SofaRouteException e)
    {
        log.error(e.getLocalizedMessage());
        return new JsonMessage(500, "SOFA服务链路异常:" + e.getLocalizedMessage());
    }
    
    /**
     * 处理500错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JsonMessage handleException(Exception e)
    {
        log.error(e.getLocalizedMessage());
        e.printStackTrace();
        return new JsonMessage(500, "系统内部异常");
    }
}
