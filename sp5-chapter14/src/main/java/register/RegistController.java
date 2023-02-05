package register;

import member.ExistingMemberException;
import member.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistController {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @RequestMapping("/step1")
    public String handleStep1(){
        return "register/step1";
    }

    @PostMapping("/step2")
    public String handleStep2Post(@RequestParam(value = "agree", defaultValue = "false")Boolean agree, Model model){
        if(!agree)
            return "redirect:step1";
        model.addAttribute("registerRequest",new RegisterRequest());
        return "register/step2";
    }

    @GetMapping("/step2")
    public String handleStep2Get(){
        return "redirect:step1";
    }

    /*
    파라미터 개수가 증가하면 일일히 받기 힘들기 때문에 커맨드객체를 통하여 파라미터 데이터를 가져올 수 있다. 단 파라미터를 가져올려면 setter 메소드를 구현해야함.
    ex) password 라는 키를 가진 파라미터를 가져오려면 setPassword 구현해야함
    순서
    1. parameter 가져온다.
    2. parameter key 를 보고 메서드 파라미터의 클래스의 setter 을 통하여 객체 생성
    3. 객체를 메서드의 파라미터에 전달한다.
    4. 이후 스프링 mvc 는 모델에 커맨드 객체의 클래스 이름과 동일한 속성 이름을(단 첫 글자는 소문자로 바꿈) 사용하여 객체를 뷰에 전달
    */
    @PostMapping("/step3")
    //@ModelAttribute("formData") RegisterRequest registerRequest 이러한 식으로 모델에 전달할 키 이름을 지정도 가능함.
    //Errors 는 커맨더 객체 뒤에 와야 한다.
    public String handleStep3Post(RegisterRequest registerRequest, Errors errors){
        if(registerRequest == null){
            return "/register/step2";
        }
        new RegisterRequestValidator().validate(registerRequest,errors);
        if(errors.hasErrors()){
            return "/register/step2";
        }
        try {
            memberRegisterService.regist(registerRequest);
            return "/register/step3";
        }catch (ExistingMemberException e){
            errors.rejectValue("email","existingMember");
            return "/register/step2";
        }
    }

    /*
     valid 어노테이션을 붙이면 해당 커맨더객체를 글로벌 범위 validator 나 컨트롤러 범위 validator 로 검증한다.

     public String handleStep3Post(@Valid RegisterRequest registerRequest, Errors errors){
        if(errors.hasErrors()){
            return "/register/step2";
        }
        try {
            memberRegisterService.regist(registerRequest);
            return "/register/step3";
        }catch (ExistingMemberException e){
            errors.rejectValue("email","existingMember");
            return "/register/step2";
        }
    }
     */

    @GetMapping("/step3")
    public String handleStep3Get(){
        return "redirect:step1";
    }

    //컨트롤러 범위 validator 설정
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new RegisterRequestValidator());
    }

}
