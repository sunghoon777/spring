package spring;

import org.springframework.transaction.annotation.Transactional;

public class MemberInfoPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberInfoPrinter(MemberDao memberDao, MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }

    @Transactional
    public void printMemberInfo(String email){
        Member member = memberDao.selectByEmail(email);
        if(member == null){
            System.out.println("해당 멤버 존재하지 않음");
            return;
        }
        printer.print(member);
    }
}
