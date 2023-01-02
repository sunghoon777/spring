package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {

    private String host;

    public void setHost(String host){
        this.host = host;
    }

    public void send(){
        System.out.println("Client sent to "+host);
    }

    //빈객체 초기화 단계에서 실행됨
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Client 초기화가 실행됩니다.");
    }

    //빈객체 소멸 단계에서 실행됨
    @Override
    public void destroy() throws Exception {
        System.out.println("Client 소멸이 실행됩니다.");
    }
}
