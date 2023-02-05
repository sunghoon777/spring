package info;

import member.Member;
import member.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MemberListController {

    @Autowired
    private MemberDao memberDao;

    @RequestMapping("/members")
    //Errors errors 를 파라미터로 붙여주면 데이터형 변환 에러를(typeMismatch exception) 자동으로 처리해준다. 또한 스프링 MVC 가 자동으로 에러코드로 typeMismatch 를 넣어준다.
    public String list(@ModelAttribute("cmd") ListCommand listCommand, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "member/memberList";
        }
        if (listCommand.getFrom() != null && listCommand.getTo() != null) {
            List<Member> members = memberDao.selectByRegdate(
                    listCommand.getFrom(), listCommand.getTo());
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }

}
