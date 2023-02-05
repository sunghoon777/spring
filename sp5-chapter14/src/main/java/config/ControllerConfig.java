package config;

import info.MemberDetailController;
import info.MemberListController;
import login.ChangePasswordController;
import login.LoginController;
import login.LogoutController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import register.RegistController;

@Configuration
public class ControllerConfig {

    @Bean
    public RegistController registController(){
        return new RegistController();
    }

    @Bean
    public LoginController loginController(){
        return new LoginController();
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController();
    }

    @Bean
    public ChangePasswordController changePasswordController(){
        return new ChangePasswordController();
    }

    @Bean
    public MemberListController memberListController(){
        return new MemberListController();
    }

    @Bean
    public MemberDetailController memberDetailController(){
        return new MemberDetailController();
    }
}
