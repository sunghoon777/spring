package main;

import config.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Member;
import spring.MemberDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainForMemberDao {

    private static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        memberDao = context.getBean(MemberDao.class);
        selectAll();
        updateMember();
        insertMember();
        context.close();
    }
    private static void selectAll() {
        System.out.println("----------------select All----------------");
        int total = memberDao.count();
        System.out.println("전체 데이터 : "+total);
        List<Member> memberList = (List<Member>) memberDao.selectAll();
        for(Member member : memberList){
            System.out.println(member.getId()+"  "+member.getEmail()+"  "+member.getName());
        }
    }

    private static void updateMember() {
        System.out.println("----------------update Member----------------");
        Member member = memberDao.selectByEmail("sunghoonlee7@naver.com");
        String oldPassword = member.getPassword();
        String newPassword = Double.toHexString(Math.random());
        member.changePassword(oldPassword,newPassword);
        memberDao.update(member);
        System.out.println("암호 변경 : "+oldPassword+" -> "+newPassword);
    }

    private static void insertMember(){
        System.out.println("----------------update Member----------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix+"@text.com",prefix,prefix,LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId()+" 데이터 추가");
    }


}
