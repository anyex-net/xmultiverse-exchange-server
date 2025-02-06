package com.anyex.apps.handler;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.system.entity.SysAccessLog;
import com.anyex.apps.system.service.SysAccessLogService;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Aspect(切面): 通常是一个类，里面可以定义切入点和通知
 * JointPoint(连接点): 程序执行过程中明确的点，一般是方法的调用
 * Advice(通知): AOP在特定的切入点上执行的增强处理
 *
 * @Before：前置通知，主要在方法前执行。
 * - 权限控制（权限不足抛出异常）
 * - 记录方法调用信息日志
 *
 * @After: final增强，不管是抛出异常或者正常退出都会执行。
 * - 释放资源 （关闭文件、 关闭数据库连接、 网络连接、 释放内存对象 ）
 *
 * @AfterReturning: 后置增强，似于AfterReturningAdvice, 方法正常退出时执行
 * - 于业务相关的， 如银行在存取款结束后的发送短信消息，或者短信验证码
 *
 * @AfterThrowing: 异常抛出增强，相当于ThrowsAdvice
 * - 处理异常（一般不可预知）
 * - 记录日志
 * - 通知管理员（短信、邮件）哪里出现异常，方便处理
 *
 * @Around: 环绕增强，相当于MethodInterceptor
 * - 日志
 * - 缓存
 * - 权限
 * - 性能监控
 * - 事务管理
 *
 * 注意多个AOP之间的执行顺序可以使用使用注解@Order比如@Order（1），可以放类上，可以是方法上，具体场景根据业务来定
 *
 * 链接：https://www.jianshu.com/p/04f110d4e1a0
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    SysAccessLogService sysAccessLogService;

    @Pointcut("execution(public * com.anyex.**.controller..*.*(..))")
    public void logPointcut(){
    }

    @Before("logPointcut()")
    public void methodBefore(JoinPoint joinPoint){
        // 方法执行前记录日志
        /*
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 打印请求内容
        log.info("---------------请求内容---------------");
        log.info("请求地址:{}", request.getRequestURL().toString());
        log.info("请求方式:{}", request.getMethod());
        log.info("请求类方法:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("请求类方法参数:{}", Arrays.toString(joinPoint.getArgs()));
        log.info("请求IP地址:{}", NetworkUtils.getIpAddr(request));
        log.info("---------------请求内容---------------");
        if (request.getRequestURL().toString().contains("/auth/login/submit")) {
            // 打印请求入参
            // log.info("请求入参 Request Args:{}", joinPoint.getArgs()[0]);
            // log.info("请求入参 Request Args:{}", joinPoint.getArgs()[1]);
            ReqLogin reqLogin = (ReqLogin)joinPoint.getArgs()[1];
            SysAccessLog sysAccessLog = new SysAccessLog();
            sysAccessLog.setUserName(reqLogin.getUsername());
            sysAccessLog.setModule("login");
            sysAccessLog.setMethod(joinPoint.getSignature().getName());
            sysAccessLog.setType("login");
            sysAccessLog.setRemark("登录");
            sysAccessLog.setReqParam(reqLogin.toString());
            sysAccessLog.setUri(request.getRequestURL().toString());
            sysAccessLog.setIp(NetworkUtils.getIpAddr(request));
            sysAccessLog.setCreateDate(System.currentTimeMillis());
            // log.info("sysAccessLog:{}", sysAccessLog);
            sysAccessLogService.insert(sysAccessLog);
        } else if (request.getRequestURL().toString().contains("/auth/logout")) {
            SysAccessLog sysAccessLog = new SysAccessLog();
            sysAccessLog.setUserName(OnLineUserUtils.getPrincipal().getUserName());
            sysAccessLog.setModule("logout");
            sysAccessLog.setMethod(joinPoint.getSignature().getName());
            sysAccessLog.setType("logout");
            sysAccessLog.setRemark("登出");
            sysAccessLog.setReqParam("");
            sysAccessLog.setUri(request.getRequestURL().toString());
            sysAccessLog.setIp(NetworkUtils.getIpAddr(request));
            sysAccessLog.setCreateDate(System.currentTimeMillis());
            // log.info("sysAccessLog:{}", sysAccessLog);
            sysAccessLogService.insert(sysAccessLog);
        }
        */
    }

    @AfterReturning(returning = "o",pointcut = "logPointcut()")
    public void methodAfterReturning(Object o){
        //log.info("===============返回内容===============");
        //log.info("返回的内容:{}", o.toString());
        //log.info("返回的内容大小: {} b", o.toString().length());
        log.info("===============返回内容===============");
    }

    @AfterThrowing(pointcut = "logPointcut()",throwing = "e")
    public void logThrowing(JoinPoint joinPoint, Throwable e){
        log.error("***************抛出异常***************");
        log.error("请求类方法:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.error("异常内容:{}", e.getLocalizedMessage());
        log.error("***************抛出异常***************");
    }

    @After("logPointcut()")
    public void methodAfter() {
        log.info("========================执行结束========================");
    }

    @Around("logPointcut()")
    public Object methodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 方法执行过程中 记录日志 包括异常情况记录 【用于记录全部日志】
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // HttpServletRequest request = requestAttributes.getRequest();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        log.info("---------------请求内容---------------");
        log.info("请求地址:{}", request.getRequestURL().toString());
        log.info("请求方式:{}", request.getMethod());
        log.info("请求类方法:{}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        log.info("请求类方法参数:{}", Arrays.toString(proceedingJoinPoint.getArgs()));
        log.info("请求IP地址:{}", NetworkUtils.getIpAddr(request));
        log.info("---------------请求内容---------------");
        //
        Object result = null;
        SysAccessLog sysAccessLog = new SysAccessLog();
        //
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null != principal) {
            sysAccessLog.setUserName(principal.getUserName());
        } else {
            sysAccessLog.setUserName(NetworkUtils.getIpAddr(request));
        }
        //
        sysAccessLog.setReqParam(request.getParameterMap()==null? " ":JSONObject.toJSONString(request.getParameterMap()));

        //
        sysAccessLog.setMethod(proceedingJoinPoint.getSignature().getName());
        sysAccessLog.setUri(request.getRequestURL().toString());
        sysAccessLog.setIp(NetworkUtils.getIpAddr(request));
        sysAccessLog.setCreateDate(System.currentTimeMillis());
        String modulePath = request.getRequestURI().toString().split("api/")[1];
        sysAccessLog.setModule(modulePath);
        sysAccessLog.setType(request.getMethod());
        sysAccessLog.setRemark("");

        try {
            result = proceedingJoinPoint.proceed();
            // sysAccessLog.setRespParam(JSONObject.toJSONString(result).length()>1000? JSONObject.toJSONString(result).substring(0,1000):JSONObject.toJSONString(result));
            // 执行耗时
            log.info("成功执行耗时: {} ms", System.currentTimeMillis() - startTime);
            sysAccessLog.setRemark("成功执行耗时:" + (System.currentTimeMillis() - startTime) + "ms");
            log.info("=======================执行返回========================");
        } catch (Throwable e) {
            log.error("***************抛出异常***************");
            log.error("请求类方法:{}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
            log.error("异常内容:{}", e.getLocalizedMessage());
            log.error("***************抛出异常***************");
            // sysAccessLog.setRespParam(e.getLocalizedMessage());
            sysAccessLog.setRemark("失败执行耗时:" + (System.currentTimeMillis() - startTime) + "ms");
            throw e;
        } finally {
            new Thread(() -> {
                log.info("sysAccessLog:{}", sysAccessLog);
                sysAccessLogService.insert(sysAccessLog);
            }).start();
        }
        //
        return result;
    }

}
