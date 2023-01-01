package assembler;

import spring.ChangePasswordService;
import spring.Member;
import spring.MemberDao;
import spring.MemberRegisterService;

//member 에 관련된 서비스들을 조립시켜주는 조립기이다. 또한 서비스 객체를 담기도 해서 서비스 객체를 반환해줄 수도 있다.
public class MemberServiceAssembler {

    private MemberDao memberDao; // member 객체를 담는 클래스
    private MemberRegisterService registerService; // member 를 등록시켜주는(memberDao 에 member를 저장시키는) 클래스 , 생성자를 통해 객체를 주입시킨다.
    private ChangePasswordService changePasswordService; // member 의 패스워드를 변경시켜주는 클래스, setter 를 통해 객체를 주입시킨다.

    //생성자를 통해 member 서비스를 조립킨다.
    public MemberServiceAssembler() {
        memberDao = new MemberDao();
        /*
         만약 member 의 기능을 확장시킨 ChachedMemberDao 클래스를 새로 만들어 객체를 주입시키고 싶으면 다음과 같이만 하면 된다.
         memberDao = new ChachedMemberDao();
         */
        registerService = new MemberRegisterService(memberDao);
        changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);

    }

    public MemberRegisterService getRegisterService() {
        return registerService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }
}
