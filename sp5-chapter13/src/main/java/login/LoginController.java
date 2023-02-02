package login;

import com.mysql.jdbc.log.Log;
import member.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String form(LoginCommand loginCommand, @CookieValue(value = "remember", required = false) Cookie cookie){
        if(cookie != null){
            loginCommand.setEmail(cookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, Errors errors, HttpSession httpSession, HttpServletResponse response){
        new LoginCommandValidator().validate(loginCommand,errors);
        if(errors.hasErrors()){
            return "login/loginForm";
        }
        try {
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(),loginCommand.getPassword());
            httpSession.setAttribute("authInfo",authInfo);
            Cookie cookie = new Cookie("remember",loginCommand.getEmail());
            cookie.setPath("/");
            if(loginCommand.isRememberEmail()){
                cookie.setMaxAge(60*60*24*30);
            }
            else{
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
            return "login/loginSuccess";
        }catch (WrongIdPasswordException e){
            errors.reject("IdPasswordNotMatching");
            return "login/loginForm";
        }
    }
}
