package com.anyex.apps.handler;

import com.alipay.sofa.rpc.core.exception.SofaRouteException;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.exception.LimitAccessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.List;

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
        return new JsonMessage(CommonEnums.ERROR_OVERSHOOT_MAXIMUM_LIMIT);
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
        return new JsonMessage(CommonEnums.ERROR_DB_UNIQUE_ERROR);
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
        return new JsonMessage(500, e.getLocalizedMessage());
    }

    /**
     * 处理用户未认证授权错误
     * class org.apache.shiro.authz.UnauthenticatedException
     * This subject is anonymous - it does not have any identifying principals and authorization operations require an identity to check against.  A Subject instance will acquire these identifying principals automatically after a successful login is performed be executing org.apache.shiro.subject.Subject.login(AuthenticationToken) or when 'Remember Me' functionality is enabled by the SecurityManager.  This exception can also occur when a previously logged-in Subject has logged out which makes it anonymous again.  Because an identity is currently not known due to any of these conditions, authorization is denied.
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthenticatedException.class)
    public JsonMessage handleUnauthenticatedException(UnauthenticatedException e)
    {
        log.error(e.getLocalizedMessage());
        return new JsonMessage(CommonEnums.USER_NOT_LOGIN);
    }

    /**
     * 处理表单验证异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    //log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getField());
                    stringBuilder.append(":");
                    stringBuilder.append(fieldError.getDefaultMessage());
                    stringBuilder.append("; ");
                });
            }

        }
        return new JsonMessage(402, stringBuilder.toString());
    }
}
