//package com.anyex.apps.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.system.entity.AccessLog;
//import com.anyex.apps.shiro.model.UserPrincipal;
//import com.anyex.apps.system.service.AccessLogService;
//import com.anyex.apps.utils.NetworkUtils;
//import com.anyex.apps.utils.OnLineUserUtils;
//import com.anyex.apps.utils.ServletsUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Aspect
//@Component
//public class AccessLogAspect {
//
//    @Autowired
//    private AccessLogService accessLogService;
//
//    /**
//     * 设置访问日志切入点 记录访问日志 在注解的位置切入代码
//     */
//    @Pointcut("@annotation(com.anyex.apps.aop.AccessLog)")
//    public void accessLogPoinCut() {
//    }
//
//    /**
//     * 设置访问异常切入点记录异常日志 扫描所有controller包下操作
//     * 重点讲解下execution表达式部分，
//     * execution是执行的意思。public * com.cx.timer...看起来非常复杂，晦涩难懂。其实这里就是一个方法名的定义：作用域 返回类型 方法名（参数..）。
//     * 其中上图的作用域是：
//     * public返回类型：
//     * * （* 表示Object类型）方法名(参数..) ：
//     * com.anyex是指具体的包名；
//     * ** 表示多级路径
//     * .* 表示 该包名下的所有的类；
//     * *(..) 表示类下所有的方法，不限定参数。
//     */
//    @Pointcut("execution(public * com.anyex.**.controller..*.*(..))")
//    public void accessExceptionLogPoinCut() {
//    }
//
//    /**
//     * 正常返回通知，拦截用户访问日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
//     *
//     * @param joinPoint 切入点
//     * @param result    返回结果
//     */
//    @AfterReturning(value = "accessLogPoinCut()", returning = "result")
//    public void saveAccessLog(JoinPoint joinPoint, Object result)
//    {
//        // 获取HttpServletRequest的信息
//        HttpServletRequest request = ServletsUtils.getRequest();
//        //
//        try
//        {
//            AccessLog operationLog = new AccessLog();
//
//            // 从切面织入点处通过反射机制获取织入点处的方法
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            // 获取切入点所在的方法
//            Method method = signature.getMethod();
//            // 获取操作
//            com.anyex.apps.aop.AccessLog accessLog = method.getAnnotation(com.anyex.apps.aop.AccessLog.class);
//            if (null != accessLog) {
//                operationLog.setModule(accessLog.module()); // 操作模块
//                operationLog.setType(accessLog.type()); // 操作类型
//                operationLog.setDescribe(accessLog.desc()); // 操作描述
//            }
//            // 获取请求的类名
//            String className = joinPoint.getTarget().getClass().getName();
//            // 获取请求的方法名
//            String methodName = method.getName();
//            methodName = className + "." + methodName;
//            operationLog.setMethod(methodName); // 请求方法
//            // 请求的参数
//            // log.info("request.getContentType:{}", request.getContentType());
////            Map<String, String> rtnMap = converMap(request.getParameterMap());
////            // 将参数所在的数组转换成json
////            String params = JSON.toJSONString(rtnMap);
//            Object[] args = joinPoint.getArgs();
//            String params = "";
//            for (Object arg : args) {
//                try {
//                    // log.info("访问日志arg:{}", arg);
//                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(arg));
//                    jsonObject.remove("password");
//                    params = params + JSON.toJSONString(jsonObject) + ",";
//                } catch (Exception e) {
//                    //params = params + JSON.toJSONString(arg) + ",";
//                }
//            }
//            operationLog.setReqParam(params); // 请求参数
//            //operationLog.setRespParam(JSON.toJSONString(result)); // 返回结果
//            operationLog.setRespParam("成功");
//            //
//            UserPrincipal principal = OnLineUserUtils.getPrincipal();
//            if(null != principal){
//                operationLog.setUserName(principal.getUserName()); // 请求用户名称
//            } else {
//                operationLog.setUserName(""); // 请求用户名称
//            }
//            operationLog.setIp(NetworkUtils.getIpAddr(request)); // 请求IP
//            operationLog.setUri(request.getRequestURI()); // 请求URI
//            operationLog.setCreateDate(System.currentTimeMillis()); // 创建时间
//            //
//            //log.info("访问日志operationLog:{}", operationLog);
//            accessLogService.insert(operationLog);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
//     *
//     * @param joinPoint 切入点
//     * @param exception 异常信息
//     */
//    @AfterThrowing(pointcut = "accessExceptionLogPoinCut()", throwing = "exception")
//    public void saveAccessExceptionLog(JoinPoint joinPoint, Throwable exception)
//    {
//        // 获取HttpServletRequest的信息
//        HttpServletRequest request = ServletsUtils.getRequest();
//        //
//        try
//        {
//            AccessLog exceptionLog = new AccessLog();
//
//            // 从切面织入点处通过反射机制获取织入点处的方法
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            // 获取切入点所在的方法
//            Method method = signature.getMethod();
//            // excepLog.setExcId(UuidUtil.get32UUID());
//            // 获取请求的类名
//            String className = joinPoint.getTarget().getClass().getName();
//            // 获取请求的方法名
//            String methodName = method.getName();
//            methodName = className + "." + methodName;
//            exceptionLog.setMethod(methodName); // 请求方法名
//            // 请求的参数
////            Map<String, String> rtnMap = converMap(request.getParameterMap());
////            // 将参数所在的数组转换成json
////            String params = JSON.toJSONString(rtnMap);
//            //
//            Object[] args = joinPoint.getArgs();
//            String params = "";
//            for (Object arg : args) {
//                try {
//                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(arg));
//                    jsonObject.remove("password");
//                    params = params + JSON.toJSONString(jsonObject) + ",";
//                } catch (Exception e) {
//                    //params = params + JSON.toJSONString(arg) + ",";
//                }
//            }
//            exceptionLog.setReqParam(params); // 请求参数
//            exceptionLog.setRespParam(exception.getMessage()); // 返回结果
//            //excepLog.setName(exception.getClass().getName()); // 异常名称
//            //excepLog.setMessage(stackTraceToString(exception.getClass().getName(), exception.getMessage(), exception.getStackTrace())); // 异常信息
//            //
//            UserPrincipal principal = OnLineUserUtils.getPrincipal();
//            if(null != principal){
//                exceptionLog.setUserName(principal.getUserName()); // 请求用户名称
//            } else {
//                exceptionLog.setUserName(""); // 请求用户名称
//            }
//            exceptionLog.setIp(NetworkUtils.getIpAddr(request)); // 请求IP
//            exceptionLog.setUri(request.getRequestURI()); // 操作URI
//            exceptionLog.setCreateDate(System.currentTimeMillis()); // 创建时间
//            //log.info("异常访问日志exceptionLog:{}", exceptionLog);
//            accessLogService.insert(exceptionLog);
//        } catch (Exception e2) {
//            e2.printStackTrace();
//        }
//    }
//
//    /**
//     * 转换request 请求参数
//     *
//     * @param paramMap request获取的参数数组
//     */
//    public Map<String, String> converMap(Map<String, String[]> paramMap)
//    {
//        Map<String, String> rtnMap = new HashMap<String, String>();
//        for (String key : paramMap.keySet()) {
//            rtnMap.put(key, paramMap.get(key)[0]);
//        }
//        return rtnMap;
//    }
//
//    /**
//     * 转换异常信息为字符串
//     *
//     * @param exceptionName    异常名称
//     * @param exceptionMessage 异常信息
//     * @param elements         堆栈信息
//     */
//    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements)
//    {
//        StringBuffer strbuff = new StringBuffer();
//        for (StackTraceElement stet : elements) {
//            strbuff.append(stet + "\n");
//        }
//        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
//        return message;
//    }
//}
