package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceView;

@Configuration
@EnableWebMvc
/*
@EnableWebMvc 은 여러가지 스프링 설정을 자동으로 해준다.
그 중 하나가 RequestMappingHandlerMapping , RequestMappingHandlerAdapter 이다.
RequestMappingHandlerMapping 은 dispatcher 가 요청 정보를 보내주면 거기에 해당되는 url mapping 찾아 핸들러를 반환.
RequestMappingHandlerAdapter 은 dispatcher 가 핸들러 객체를 보내주면 , 핸들러의 메서드를 실행하고 ModelAndView 객체를 반환해준다.

여기서는 MvcConfig 자체도 @Configuration 이기 때문에 빈객체로 등록되므로 결과적으로는 WebMvcConfigurer 타입의 빈객체를 생성된다.
근데 다음과 같이 직접 빈객체를 만들어도됨.
  @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                configurer.enable();
            }

            @Override
            public void configureViewResolvers(ViewResolverRegistry registry) {
                registry.jsp("/WEB-INF/view/",".jsp");
            }
        };
        return webMvcConfigurer;
    }
 */
public class MvcConfig implements WebMvcConfigurer {


    /*
    DefaultServletHandlerConfigurer 의 enable() 메서드를 실행하면 DefaultServletHttpRequestHandler , SimpleUrlHandlerMapping 빈객체를 등록한다.
    사용 용도
    1. 만약 RequestMappingHandlerMapping 이 요청에 대한 핸들러를 찾기 못하면 dispatcher 는 다시 SimpleUrlHandlerMapping 에게 요청을 한다.
    2. SimpleUrlHandlerMapping 는 모든 경로에 대해서 DefaultServletHttpRequestHandler 를 반환
    3. dispatcher 가 요청을 처리를 DefaultServletHttpRequestHandler 에게 위임한다.
    4. DefaultServletHttpRequestHandler 는 WAS 가 제공하는 티폴트 서블릿에 요청을 전달한다.
    5. 디폴트 서블릿은 응답을 생성하여 클라이언트에게 보낸다.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
    configureViewResolvers 를 재구현 ViewResolverRegistry 객체의 jsp 메서드를 실행하면 내부적으로 InternalResourceViewResolver 타입의 빈객체를 등록한다.
    이후 dispatcher 가 InternalResourceViewResolver 에 view 객체 생성 요청을 한다면 prefix+name+suffix 경로를 가진  InternalResourceView 를 리턴한다.
    InternalResourceView 는 dispatcher 에게 map 객체(model 정보가 들어감)를 받고 그것을 request 에 등록하여 jsp 에 전달하여(아마도  forward 이용할듯?) 이후 jsp 는 request 를 이용하여 응답 결과를 생성한다.
    */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/",".jsp"); // /WEB-INF/view/ 를 prefix    .jsp 를 suffix 로 설정함
    }




}
