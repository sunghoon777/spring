package test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String testGet(Model model){
        model.addAttribute("coffee",new Coffee());
        model.addAttribute("data","no");
        String drinkCountList[] = {"1","2","3","4","5"};
        String competitors[] = {"coke","starbucks","edia","mega"};
        model.addAttribute("drinkCountList",drinkCountList);
        model.addAttribute("competitors",competitors);
        return "test/test";
    }

    @PostMapping
    public String testPost(Model model, Coffee coffee){
        model.addAttribute("data","yes");
        String drinkCountList[] = {"1","2","3","4","5"};
        String competitors[] = {"coke","starbucks","edia","mega"};
        model.addAttribute("drinkCountList",drinkCountList);
        model.addAttribute("competitors",competitors);
        System.out.println("count : "+coffee.getDrinkCount());
        System.out.println("competitors : "+ Arrays.toString(coffee.getCompetitors()));

        return "test/test";
    }

    @GetMapping("/test2")
    public String test2Get(){
        return "test/test2";
    }

    @PostMapping("/test2")
    public String test2Post(String[] data1){
        System.out.println(Arrays.toString(data1));
        return "test/test2";
    }

}
