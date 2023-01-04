package main;

import config.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;
import spring.ImpeCalculator;

public class Main {

    //직접 프록시를 간단하게 구현하여 실행한 메소드
    private void startExeTimeCalculator(){
        ExeTimeCalculator exeTimeCalculator = new ExeTimeCalculator(new ImpeCalculator());
        exeTimeCalculator.factorial(4);
        ExeTimeCalculator exeTimeCalculator2 = new ExeTimeCalculator(new RecCalculator());
        exeTimeCalculator.factorial(4);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Calculator calculator = context.getBean("impeCalculator", Calculator.class);
        System.out.println(calculator.factorial(5));
        System.out.println("_______________________________\n\n\n");
        System.out.println(calculator.factorial(5));
        context.close();
    }



}
