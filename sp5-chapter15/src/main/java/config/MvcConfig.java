package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import interceptor.AuthCheckInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

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

    //HttpConverter 를 직접 설정해준다. @EnableWebMvc 을 적용하면 원래는 자동으로 되지만 원하는 설정을 추가하고 싶으면 직접 추가 설정해야한다.
    //파라미터로 converter 리스트를 전달받는데 여기에는 스프링 MVC 가 미리 등록한 converter 들이 들어감. 참고로 @JsonFormat 을 직접 적용한 것과 우선순위를 비교하면 @JsonFormat 가 우선순위가 더 높다.
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().serializerByType(LocalDateTime.class,new LocalDateTimeSerializer(formatter)).build();
        //이 converter 를 둬야 가장 먼저 적용이 된다.
        converters.add(0,new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
