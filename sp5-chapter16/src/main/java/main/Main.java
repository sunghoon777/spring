package main;

import bean.Person;
import config.DevConfig;
import config.PropertyConfig;
import config.RealConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        //dev 는 person 이름이 hoho 이고 나이는 10임..
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(RealConfig.class, DevConfig.class, PropertyConfig.class);
        context.refresh();
        Person person = context.getBean("person",Person.class);
        System.out.println(person);
        //만약 real profile 에 있는 빈 가져올려고 하면 빈을 찾을 수 없다고 예외가 발생됨
        /*
        아래와 같이 System.setProperty 를 이용하여 profile 을 지정할 수 도 있다.
        System.setProperty("spring.profiles.active","dev");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RealConfig.class, DevConfig.class);
         */

    }

}
