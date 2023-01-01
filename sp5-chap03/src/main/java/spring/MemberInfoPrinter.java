package spring;

public class MemberInfoPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

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
