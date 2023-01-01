package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class MemberListPrinter {

    //autowired 어노테이션을 지정하면 컨테이너에서 빈객체가 memberDao 로 자동으로 주입이 된다.
    @Autowired
    private MemberDao memberDao;
    //autowired 어노테이션을 지정하면 컨테이너에서 빈객체가 printer 로 자동으로 주입이 된다.
    @Autowired
    private MemberSummaryPrinter printer;

    public MemberListPrinter() {
    }

    public void printAll(){
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> printer.print(m));
    }

}
