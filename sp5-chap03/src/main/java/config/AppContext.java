package config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppContext {

    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        ChangePasswordService changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao());
        return changePasswordService;
    }

    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter memberListPrinter(){
        return new MemberListPrinter(memberDao(),memberPrinter());
    }

    @Bean
    public MemberInfoPrinter memberInfoPrinter(){
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        memberInfoPrinter.setPrinter(memberPrinter());
        memberInfoPrinter.setMemberDao(memberDao());
        return memberInfoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(4);
        return versionPrinter;
    }
}
