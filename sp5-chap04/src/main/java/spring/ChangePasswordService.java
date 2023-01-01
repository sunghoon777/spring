package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    //autowired 어노테이션을 지정하면 컨테이너에서 빈객체가 memberDao 로 자동으로 주입이 된다.
    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email,String oldPassword, String newPassword){
        Member member = memberDao.selectByEmail(email);
        if(member == null)
            throw new MemberNotFoundException();
        member.changePassword(oldPassword,newPassword);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }
}
