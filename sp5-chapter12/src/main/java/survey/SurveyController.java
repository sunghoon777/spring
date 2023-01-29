package survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @GetMapping
    public String form(Model model){
        List<Question> questions = createQuestion();
        model.addAttribute("questions",questions);
        return "survey/surveyForm";
    }

    private List<Question> createQuestion(){
        List<String> list1 = new ArrayList<>();
        list1.add("backend");
        list1.add("frontend");
        list1.add("fullstack");
        List<String> list2 = new ArrayList<>();
        list2.add("Eclipse");
        list2.add("Intellij");
        list2.add("VS code");
        Question question1 = new Question("당신의 역할은 무엇입니까?", list1);
        Question question2 = new Question("많이 사용하는 개발도구는 무엇입니까?", list2);
        Question question3 = new Question("하고 싶은 말을 적어주세요");
        return Arrays.asList(question1,question2,question3);
    }

    @PostMapping
    public String submit(@Valid AnsweredData answeredData, Errors errors){
        if(errors.hasErrors()){
            return "survey/surveyForm";
        }
        return "survey/submiited";
    }

}
