package com.wdkj.web.common.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源切面类
 * note： 应当设计成环绕通知，否则在一个定义了数据源的方法里调用另一个设置了数据源的方法，
 * 会污染当前设置的数据源
 */
//@Aspect
//@Component
//@Order(99)
public class DataSourceAspect {

    private static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    // 指定切入点匹配表达式，注意它是以方法的形式进行声明的。
    // 第一个* 代表返回值类型
    // 如果要设置多个切点可以使用 || 拼接
    @Pointcut("execution(public * com.wdkj.multi.plan.service..*.*(..))")
    public void serviceMethod() {
    }

    /*
     * 定义一个切入点
     */
    @Pointcut("@annotation(com.wdkj.common.config.database.DestineDataSource)")
    public void dataSourceAnnotation() {
    }

    /**
     * 环绕通知设置数据源<br></>
     * 检查类和方法，如果都存在，一方法为主
     * 在invoke之前设置，之后需要设置成原来的
     *
     * @param pjp 连接点
     * @return
     * @throws Throwable
     */
    @Around("serviceMethod()")
    public Object setDatasource(ProceedingJoinPoint pjp) throws Throwable {

        DatasourceEnum original = DynamicDataSource.getDatabaseType();

        Class targetClass = pjp.getTarget().getClass();
        DestineDataSource classAnno = null;
        DestineDataSource methodAnno = null;

        if (targetClass.isAnnotationPresent(DestineDataSource.class)) {
            classAnno = (DestineDataSource) targetClass.getDeclaredAnnotation(DestineDataSource.class);
        }

        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = targetClass.getMethod(signature.getName(), methodSignature.getMethod().getParameterTypes());
            if (targetMethod.isAnnotationPresent(DestineDataSource.class)) {
                methodAnno = targetMethod.getDeclaredAnnotation(DestineDataSource.class);
            }
        }

        if (methodAnno != null) {
            DynamicDataSource.setDatabaseType(methodAnno.value());
            if (log.isDebugEnabled()) log.debug("为方法:" + signature.getName() + " 设置了数据源:" + methodAnno.value().name());
        } else if (classAnno != null) {
            DynamicDataSource.setDatabaseType(classAnno.value());
            if (log.isDebugEnabled()) log.debug("为类:" + targetClass.getName() + " 设置了数据源:" + classAnno.value().name());
        }

        Object res = pjp.proceed();

        DynamicDataSource.setDatabaseType(original);

        return res;
    }

    // 异常通知
    // @AfterThrowing(value = "anyMethod()", throwing = "e")
    // public void doThrow(JoinPoint jp, Throwable e) {
    //
    // }

    /**
     * //     前置通知
     * 在切点方法集合执行前，执行前置通知
     * 这里设置了
     */
//    @Before("serviceMethod()")
//    public void setDatasource(JoinPoint jp) {
    //   }


    // 后置通知
    // @AfterReturning(value = "anyMethod()", returning = "result")
    // public void doAfter(JoinPoint jp) {
    //
    // }
}
