package main;

import config.TestContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.Test;

public class MainForTransactionTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestContext.class);
        Test test = context.getBean(Test.class);
        try {
            System.out.println(test.count());
            test.a();
        }catch (Exception e){

        }
        System.out.println(test.count());
    }
}
