package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    //모델 데이터는 greeting view 는 hello 이름의 파일
    @GetMapping("/hello")
    public String hello(Model model , @RequestParam(value = "name", required = false) String name){
        model.addAttribute("greeting","안녕하세요"+name);
        return "hello";
    }

}
