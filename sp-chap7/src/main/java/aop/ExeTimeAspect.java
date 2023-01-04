package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.Arrays;


@Aspect
@Order(1) // order 값이 작을수록 먼저 실행된다.
public class ExeTimeAspect {


    //around 에서 직접 pointcut을 지정 가능함
    @Around("aop.CommonPointcut.commonTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("measure 호출됨");
        long start = System.nanoTime();
        try {
            System.out.println("measure 의 joinpoint"+joinPoint.hashCode()+" proceed 호출됨");
            Object result = joinPoint.proceed();
            System.out.println("measure 의 joinpoint"+joinPoint.hashCode()+" proceed 리턴됨");
            System.out.println("measure 리턴됨");
            return result;
        }finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행시간 : %d ns \n",joinPoint.getTarget().getClass().getName(),
                    sig.getName(),  Arrays.toString(joinPoint.getArgs()),   finish-start);
        }
    }

}
