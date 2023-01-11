package main;

import config.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongPasswordException;

public class MainForChangePasswordService {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        ChangePasswordService cpService = context.getBean(ChangePasswordService.class);
        System.out.println(cpService);
        try {
            cpService.changePassword("sunghoonlee7@naver.com","123456","1234");
        }catch (MemberNotFoundException e){
            System.out.println("회원 데이터가 존재하지 않음");
        }catch (WrongPasswordException e){
            System.out.println("패스워드가 틀렸음");
        }
        context.close();
    }

}
