package spring;


import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

//Member 등록 서비스
public class MemberRegisterService {

    //autowired 어노테이션을 지정하면 컨테이너에서 빈객체가 memberDao 로 자동으로 주입이 된다.
    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public MemberRegisterService() {
    }

    public Long regist(RegisterRequest registerRequest){
        Member member = memberDao.selectByEmail(registerRequest.getEmail());
        if(member != null)
            throw new ExistingMemberException();
        Member newMember = new Member(registerRequest.getEmail(),registerRequest.getPassword(),registerRequest.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }
}
