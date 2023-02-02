package config;

import login.AuthService;
import login.ChangePasswordController;
import login.LoginController;
import login.LogoutController;
import org.springframework.beans.factory.annotation.Autowired;
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

}
