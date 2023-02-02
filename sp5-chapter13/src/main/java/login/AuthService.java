package login;

import member.Member;
import member.MemberDao;
import member.WrongIdPasswordException;

import javax.crypto.interfaces.PBEKey;

public class AuthService {

    private MemberDao memberDao;

    public AuthService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public AuthInfo authenticate(String email, String password){
        Member member = memberDao.selectByEmail(email);
        if(member == null){
            throw new WrongIdPasswordException();
        }
        if(!member.matchPassword(password)){
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(),member.getEmail(),member.getName());
    }
}
