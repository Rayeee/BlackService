package me.zgy.routing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Rayee on 2017/9/22.
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {

    @Around("@annotation(me.zgy.routing.DataSource))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataSource annotation = signature.getMethod().getAnnotation(DataSource.class);
        String dataSource = annotation.value();
        DataSourceHolder.put(dataSource);
        Object result = point.proceed();
        DataSourceHolder.clear();
        return result;
    }

}
