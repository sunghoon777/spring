package main;

import config.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;
import spring.Client2;

public class Main {

    public static void main(String[] args) {
        //초기화
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        //빈객체 가져오기 
        //싱글톤 범위인 client 빈객체 , 계속 빈객체를 가져와도 똑같은 객체를 반환해줌.
        Client client1_1 = context.getBean(Client.class);
        Client client1_2 = context.getBean(Client.class);
        //prototype 범위인 client2 빈객체, 계속 빈객체를 가져오면 다른 객체를 반환해준다.
        Client2 client2_1 = context.getBean(Client2.class);
        Client2 client2_2 = context.getBean(Client2.class);
        if(client1_1 == client1_2){
            System.out.println("client1_1 과 client1_2 똑같은 객체를 가리킵니다.");
        }
        if(client2_1 != client2_2){
            System.out.println("client2_1 과 client2_2 다른 객체를 가리킵니다.");
        }
        //send
        client1_1.send();
        client2_1.send();
        //범위가 prototype 인 client2는 수동으로 종료 메서드를 호출해야한다.
        client2_1.close();
        client2_2.close();
        //종료
        context.close();
    }

}
