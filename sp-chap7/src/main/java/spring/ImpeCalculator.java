package spring;

import spring.Calculator;

public class ImpeCalculator implements Calculator {


    @Override
    public long factorial(long number) {
        System.out.println("factorial 호출됨");
        long result = 1;
        for(int i=2;i<=number;i++){
            result *= i;
        }
        System.out.println("factorial 리턴됨");
        return result;
    }
}
