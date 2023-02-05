package login;

import member.ChangePasswordService;
import member.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @GetMapping
    public String form(ChangePasswordCommand passwordCommand){
        return "edit/changePasswordForm";
    }

    @PostMapping
    public String submit(ChangePasswordCommand passwordCommand, Errors errors, HttpSession httpSession) throws WrongIdPasswordException{
        new ChangePasswordCommandValidator().validate(passwordCommand,errors);
        if(errors.hasErrors()){
            return "edit/changePasswordForm";
        }
        AuthInfo authInfo = (AuthInfo) httpSession.getAttribute("authInfo");
        try {
            changePasswordService.changePassword(authInfo.getEmail(), passwordCommand.getCurrentPassword(), passwordCommand.getNewPassword());
            return "edit/changedPassword";
        }catch (WrongIdPasswordException e){
            System.out.println("exception occur");
            errors.reject("currentPassword","notMatching");
            return "edit/changePasswordForm";
        }

    }

}
