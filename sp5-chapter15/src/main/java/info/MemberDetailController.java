package info;

import member.Member;
import member.MemberDao;
import member.MemberNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberDetailController {

    @Autowired
    private MemberDao memberDao;

    @GetMapping("/members/{id}")
    public String detail(@PathVariable("id")Long memberId, Model model){
        Member member = memberDao.selectById(memberId);
        if(member == null){
            throw new MemberNotFoundException();
        }
        model.addAttribute("member",member);
        return "member/memberDetail";
    }

    //컨트롤러에서 예외가 발생하게 되면 @ExceptionHandler 어노테이션이 적용된 메서드가 실행되어 진다. 사용자에게 어떤 오류가 발생했는지 알려줄 수 있다.(기본 예외 페이지는 알아보기 힘드니까 이것을 사용)
    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(){
        return "member/invalid";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(){
        return "member/noMember";
    }



}
