package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppContext {

    //memberDao 와 memberPrinter 는 나머지 bean 들을 위한 의존 객체이다.
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }


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
    //의존 객체


    //@AutoWired 의존 자동 주입을 통하여 직접 의존을 주입하지 않았다. 원래는 생성자를 통해 주입이됨
    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService();
    }

    //@AutoWired 의존 자동 주입을 통하여 직접 의존을 주입하지 않았다. 원래는 setter 를 통해 주입이됨
    @Bean
    public ChangePasswordService changePasswordService(){
        return new ChangePasswordService();
    }

    //@AutoWired 의존 자동 주입을 통하여 직접 의존을 주입하지 않았다. 원래는 setter 를 통해 주입이됨
    @Bean
    public MemberInfoPrinter memberInfoPrinter(){
        return new MemberInfoPrinter();
    }

    //@AutoWired 의존 자동 주입을 통하여 직접 의존을 주입하지 않았다. 원래는 생성자를 통해 주입이됨
    @Bean
    public MemberListPrinter memberListPrinter(){
        return new MemberListPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(4);
        return versionPrinter;
    }
}
