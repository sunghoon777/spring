package config;

import bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public Person person(){
        Person person = new Person("hoho",10);
        return person;
    }

    @Bean
    public Person person2(){
        Person person = new Person();
        return person;
    }

}
