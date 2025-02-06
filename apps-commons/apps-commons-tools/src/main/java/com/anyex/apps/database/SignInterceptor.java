package com.anyex.apps.database;

import com.anyex.apps.bean.SignableEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>File：SignInterceptor.java</p>
 * <p>Title: SignInterceptor </p>
 * <p>Description: 拦截需要签名的对象，实现自动签名</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-17 下午4:02:10</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
@ConditionalOnClass(Interceptor.class)
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SignInterceptor implements Interceptor
{
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Object intercept(Invocation invocation) throws Throwable
    {
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof SignableEntity)
        {
            ((SignableEntity) parameter).doSign();
        }
        if (parameter instanceof Map)
        {// 检查集合参数
            Map map = (Map) parameter;
            if (map.containsKey("list"))
            {
                List<Object> data = (List<Object>) map.get("list");
                for (Object o : data)
                {
                    if (o instanceof SignableEntity)
                    {// 如果对象是需要签名的
                        ((SignableEntity) o).doSign();
                    }
                    else
                    {// 如果对象不是签名对象直接终止
                        break;
                    }
                }
            }
        }
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties)
    {
    }
}