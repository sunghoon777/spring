package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import register.RegistController;
import survey.SurveyController;
import test.TestController;

@Configuration
public class ControllerConfig {

    @Bean
    public RegistController registController(){
        return new RegistController();
    }

    @Bean
    public SurveyController surveyController(){
        return new SurveyController();
    }

    @Bean
    public TestController testController(){
        return new TestController();
    }
}
