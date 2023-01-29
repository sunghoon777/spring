package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;
import register.RegisterRequestValidator;

import java.util.ResourceBundle;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {


    //viewResolver 빈 객체 등록
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/",".jsp");
    }

    //기본 요청 처리할 DefaultServletHandler 와 simpleUrlHandler 등록
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //main controller 추가, /main 경로로 요청이 오면  /WEB-INF/view/main.jsp 리턴
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }

    /*
    이름 messageSource 여야만 적용이됨
    src/main/resources 폴더는 자동으로 클래스 패스에 포함됨(아마 ide 가 해주는 듯함)
    이러한 messageSource 빈은 후에 <spring:message> 태그에서 사용되어 진다. <spring:message> 태그는 내부적으로  MessageSource#getMessage(String code, Object[] args, Locale locale)을 호출하게 된다.
    이를 통해 code 에 대한 메시지를 얻을 수 있다.
     */
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        //message 패키지에 error , label 파일로부터 메시지를 읽어오게 하였음
        ms.setBasenames("message.error","message.label");
        //인코딩은 utf-8
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    /*
    글로벌 validator 로 등록한다. 하지만 RegisterRequestValidator 는 글로벌 validator 로 적합하지는 않아 주석 처리
    @Override
    public Validator getValidator() {
        return new RegisterRequestValidator();
    }
     */
}
