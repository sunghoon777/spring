package controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.ExistingMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegistController {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @RequestMapping("/step1")
    public String handleStep1(){
        return "register/step1";
    }

    // value 는 요청 파라미터 이름 , defaultValue 기본값, 스프링 MVC 가 가져온 String 형 파라미터값을 메서드 파라미터의 클래스형으로 변환해준다. 여기서는 Boolean 으로 변환
    // 기본 데이터 타입과 래퍼 타입 변환을 지원해준다.
    @PostMapping("/step2")
    public String handleStep2Post(@RequestParam(value = "agree", defaultValue = "false")Boolean agree, Model model){
        if(!agree)
            return "redirect:step1";
        model.addAttribute("registerRequest",new RegisterRequest());
        return "register/step2";
    }

   /*
   다음과 같이 구성해도 된다.
    public String handleStep2(HttpServletRequest request){
        String agree = request.getParameter("agree");
        if(agree == null || !agree.equals("true")){
            return "register/step1";
        }
        return "register/step2";
    }
    */

    @GetMapping("/step2")
    public String handleStep2Get(){
        /*
        redirect:/register/step1 로 하면 root/register/step1 으로 리다이렉트 되고
        / 로 시작하지 않으면 상대경로로 리다이렉트한다 예를 들어 redirect:step2 하면 된다
        redirect:완전한 url 로도 할 수 있다.
         */
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
    public String handleStep3(RegisterRequest registerRequest){
        try {
            memberRegisterService.regist(registerRequest);
            return "/register/step3";
        }catch (ExistingMemberException e){
            return "/register/step2";
        }
    }

}
