package spring;

public class RecCalculator implements Calculator{
    @Override
    public long factorial(long number) {
        if(number == 1){
            return 1;
        }
        return number*factorial(number-1);
    }
}
