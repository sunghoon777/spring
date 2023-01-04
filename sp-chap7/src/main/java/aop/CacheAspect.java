package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(2) // order 값이 작을수록 먼저 실행된다.
public class CacheAspect {

    private Map<Long, Object> cache = new HashMap<>();

    //around 에서 직접 pointcut을 지정 가능함
    @Around("aop.CommonPointcut.commonTarget()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("execute 호출됨");
        Long num = (Long)joinPoint.getArgs()[0];
        if(cache.containsKey(num)){
            System.out.printf("캐시에서 %s 구함\n",num);
            System.out.println("execute 리턴됨");
            return cache.get(num);
        }
        System.out.println("execute 의 joinpoint"+joinPoint.hashCode()+" proceed 호출함");
        Object result = joinPoint.proceed();
        System.out.println("execute 의 joinpoint"+joinPoint.hashCode()+" proceed 리턴됨");
        cache.put(num,result);
        System.out.printf("캐시에 %s 추가\n",num);
        System.out.println("execute 리턴됨");
        return result;
    }

}
