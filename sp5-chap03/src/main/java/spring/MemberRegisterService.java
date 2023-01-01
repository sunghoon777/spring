package spring;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Member 등록 서비스
public class MemberRegisterService {

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
