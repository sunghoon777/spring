package config;

import bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//실제 서비스 configuration
@Profile("real")
public class RealConfig {

    @Bean
    public Person person(){
        Person person = new Person("haha",20);
        return person;
    }

    @Bean
    public Person person2(){
        Person person = new Person("haha2",20);
        return person;
    }

}
