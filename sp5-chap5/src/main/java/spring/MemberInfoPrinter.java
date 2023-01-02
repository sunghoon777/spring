package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter") // 이름 따로 지정
public class MemberInfoPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    //autowired 어노테이션을 지정하면 컨테이너에서 빈객체가 매개변수 memberDao 로 자동으로 주입이 된다. 그 후 this.memberDao 에도 대입이 되어짐
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    //autowired 어노테이션을 지정하면 컨테이너에서 빈객체가 매개변수 printer 로 자동으로 주입이 된다. 그 후 this.printer 에도 대입이 되어짐
    @Autowired
    @Qualifier("printer1")
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

    public void printMemberInfo(String email){
        Member member = memberDao.selectByEmail(email);
        if(member == null){
            System.out.println("해당 멤버 존재하지 않음");
            return;
        }
        printer.print(member);
    }
}
