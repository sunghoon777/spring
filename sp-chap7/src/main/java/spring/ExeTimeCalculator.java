package spring;

//Calculator 의 공통 기능을 담당하는 프록시 클래스
public class ExeTimeCalculator implements Calculator{

    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long number) {
        //시작 시간
        long start = System.nanoTime();
        //핵심 기능은 위임 
        long result = delegate.factorial(number);
        //끝난 시간
        long end = System.nanoTime();
        System.out.printf("%s.factorial(%d) 의 실행 시간 : %d\n",delegate.getClass().getSimpleName(),number,end-start);
        return result;
    }

}
