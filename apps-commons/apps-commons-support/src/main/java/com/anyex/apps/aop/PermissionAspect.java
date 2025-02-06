//package com.anyex.apps.aop;
//
//import com.anyex.apps.annotation.Permission;
//import com.anyex.apps.enums.CommonEnums;
//import com.anyex.apps.enums.LogicTypeEnum;
//import com.anyex.apps.exception.BusinessException;
//import com.anyex.apps.utils.OnLineUserUtils;
//import com.anyex.apps.utils.ServletsUtils;
//import com.anyex.apps.utils.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//
///**
// *
// * 权限过滤Aop切面
// *
// */
//@Slf4j
//@Aspect
//@Component
//public class PermissionAspect {
//
//    /**
//     *
//     * 权限切入点
//     *
//     */
//    @Pointcut("@annotation(com.anyex.apps.annotation.Permission)")
//    private void getPermissionPointCut() {
//        System.out.println("========================================");
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
//     *
//     * 执行权限过滤
//     *
//     */
//    @Before("getPermissionPointCut()")
//    public void doPermission(JoinPoint joinPoint) {
//
////        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
////        Method method = methodSignature.getMethod();
////        Permission permission = method.getAnnotation(Permission.class);
////        System.out.println("================1111========================");
////        System.out.println(permission.value().length);
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        Permission permission = method.getAnnotation(Permission.class);
//
//        //当前方法需要的角色集合
//        String[] requireRoles = permission.value();
//        //当前方法需要的角色集合 逻辑类型
//        LogicTypeEnum logicType = permission.logicType();
//
//        //如果不需要特别的角色，则判断用户所属角色是否有当前访问的url的权限
//        if (requireRoles.length == 0) {
//            HttpServletRequest request = ServletsUtils.getRequest();
//            boolean flag = hasPermission(request.getRequestURI());
//            // log.info("flag:"+flag);
//            if (!flag) {
////                this.executeNoPermissionExceptionLog(joinPoint, new PermissionException(PermissionExceptionEnum.NO_PERMISSION));
////                throw new ServerException(PermissionExceptionEnum.NO_PERMISSION.getMessage());
//                throw new BusinessException(CommonEnums.ERROR_NO_PERMISSION);
//            }
//        } else {
//            //当前方法的权限需要一些特别的角色
//
//            HttpServletRequest request = ServletsUtils.getRequest();
//            boolean flag = hasPermission(request.getRequestURI());
//            // log.info("flag:"+flag);
//            if (!flag) {
//                throw new BusinessException(CommonEnums.ERROR_NO_PERMISSION);
//            }
//
////            boolean flag = true;
////            if (LogicTypeEnum.AND.equals(logicType)) {
////                flag = LoginContextHolder.me().hasAllRole(StringUtils.join(requireRoles));
////            } else if (LogicTypeEnum.OR.equals(logicType)) {
////                flag = LoginContextHolder.me().hasAnyRole(StringUtils.join(requireRoles));
////            } else {
////                log.error(">>> permission注解逻辑枚举错误");
////            }
////            if (!flag) {
////                this.executeNoPermissionExceptionLog(joinPoint, new PermissionException(PermissionExceptionEnum.NO_PERMISSION));
////                throw new ServerException(PermissionExceptionEnum.NO_PERMISSION.getMessage());
////            }
//        }
//    }
//
//    /**
//     *
//     * 记录无权限异常日志
//     *
//     */
//    private void executeNoPermissionExceptionLog(JoinPoint joinPoint, Exception exception) {
////        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
////        Method method = methodSignature.getMethod();
////        BusinessLog businessLog = method.getAnnotation(BusinessLog.class);
//
//        //异步记录日志
//       /* LogManager.me().executeExceptionLog(
//                businessLog, LoginContextHolder.me().getSysLoginUserAccount(), joinPoint, exception);*/
//    }
//
//    public boolean hasPermission(String requestUri) {
//        //String removePrefix = StringUtils.removePrefix(requestUri, "/api/");
//        String removePrefix = StringUtils.removePrefix(requestUri, "/adminapi/");
//        String requestPermission = removePrefix.replaceAll("/", ":");
//        // log.info("requestPermission:"+requestPermission);
//        // log.info("OnLineUserUtils.getAllResourcesData():{}", OnLineUserUtils.getAllResourcesData());
//        return OnLineUserUtils.getAllResourcesData().stream().filter(item->item.getResCode().equals(requestPermission)).findAny().isPresent();
//        // return OnLineUserUtils.getUserResources().contains(requestPermission);
//    }
//
//}
