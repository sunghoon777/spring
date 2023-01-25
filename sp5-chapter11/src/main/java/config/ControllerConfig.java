package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import controller.RegistController;
import survey.SurveyController;

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

}
