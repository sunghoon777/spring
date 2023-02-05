package config;

import interceptor.AuthCheckInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;

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
    
    //messageSource 빈 객체 등록 , ResourceBundle 을 이용(properties 파일)
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        //message 패키지에 error , label 파일로부터 메시지를 읽어오게 하였음
        ms.setBasenames("message.error","message.label");
        //인코딩은 utf-8
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    //인터셉터 추가함. /edit/ 이하로 오는 요청은 모두 인터셉터가 적용이 된다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthCheckInterceptor()).addPathPatterns("/edit/**");
    }
}
