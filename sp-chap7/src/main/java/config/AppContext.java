package config;

import aop.CacheAspect;
import aop.ExeTimeAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.Calculator;
import spring.ImpeCalculator;
import spring.RecCalculator;

@Configuration
/*
@EnableAspectJAutoProxy 어노테이션을 통해 스프링은 자동으로 AnnotationAwareAspectJAutoProxyCreator 객체를 빈으로 등록한다.
아마도 이 객체가 프록시 객체를 자동으로 만들어주는 듯 함.  그리고 예상이긴한데 다음과 같은 과정일 듯 함.(나중에 시간나면 정확히 알아보자)
ex)
AnnotationAwareAspectJAutoProxyCreator 객체 생성 -> AutoProxyCreator 가 exeTimeAspect 를 위한 프록시 객체 생성 ,
해당 프록시는 포인트컷으로 참조한 메서드를 가진 객체가 implement 한 인터페이스를 자신도 implement 한다.
-> 이후 프록시 객체는 컨테이너안에서 빈객체로 등록된 ExeTimeAspect 와 포인트컷으로 참조한 메서드를 가진 객체를 참조함. ->
그 이후 client 가 프로시를 통해 메서드를 실행시킬 수 있음.
 */
@EnableAspectJAutoProxy // proxyTargetClass = true 로 설정하면 프록시는 인터페이스가 아닌 해당 객체빈의 타입을 상속받아 생성된다.
public class AppContext {

    @Bean
    public ExeTimeAspect exeTimeAspect(){
        return new ExeTimeAspect();
    }

    @Bean
    public CacheAspect cacheAspect(){
        return new CacheAspect();
    }

    //반환 타입이 Calculator 이든 ImpeCalculator 이든 상관없음.
    @Bean
    public Calculator impeCalculator(){
        return new ImpeCalculator();
    }

    @Bean
    public Calculator recCalculator(){
        return new RecCalculator();
    }

}
