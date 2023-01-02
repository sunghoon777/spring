package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.*;

@Configuration
@ComponentScan(basePackages = {"spring"})
/*
다음과 같이 excludeFilters 를 이용하여 스캔을 제외할 컴포넌트를 선택 가능하다.
@ComponentScan(basePackages = {"spring"} ,
        excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX , pattern = ""),
        @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = MyAnnotation.class)})

        type 의 종류로는 REGEX(정규식) ASPECTJ(정규식과 비슷한 것) ANNOTATION(어노테이션) ASSIGNABLE_TYPE(클래스를 직접 지정하는 방식) 이 존재한다.
 */
//MemberRegisterService, ChangePasswordService , MemberInfoPrinter, MemberListPrinter, MemberDao 가 Component
public class AppContext {

    //MemberPrinter 빈객체가 2개 존재 그래서 Qualifier 를 통해 빈객체에 이름을 붙여준다.
    @Bean
    @Qualifier("printer1")
    public MemberPrinter memberPrinter1(){
        return new MemberPrinter();
    }

    //빈객체의 기본 이름은 메서드의 이름이다. memberPrinter2
    @Bean
    public MemberSummaryPrinter memberPrinter2(){
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(4);
        return versionPrinter;
    }
}
