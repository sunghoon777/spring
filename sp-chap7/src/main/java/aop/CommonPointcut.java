package aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {


    @Pointcut("execution(public * spring..*.*(long))")
    public void commonTarget(){

    }

}
