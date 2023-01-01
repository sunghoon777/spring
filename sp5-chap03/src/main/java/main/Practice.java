package main;

import config.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Practice {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        MemberRegisterService registerService = context.getBean("memberRegisterService", MemberRegisterService.class);
        ChangePasswordService passwordService = context.getBean("changePasswordService",ChangePasswordService.class);
        MemberDao memberDao = context.getBean("memberDao",MemberDao.class);
        System.out.println("register 의 dao 와 password 의 dao 같나 ? : "+(registerService.getMemberDao() == passwordService.getMemberDao()));
        System.out.println("register 의 dao 와 빈객체 dao 같나 ? : "+(registerService.getMemberDao() == memberDao));
        System.out.println("빈객체 dao 와 password 의 dao 같나 ? : "+(memberDao == passwordService.getMemberDao()));
        //실험 결과 다 똑같은 memberdao 이다.
    }
}
