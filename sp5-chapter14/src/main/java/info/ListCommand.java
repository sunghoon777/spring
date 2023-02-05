package info;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {

    /*
     @DateTimeFormat 어노테이션을 적용하면 자동으로 string 으로 온 데이터를 날짜형으로 바꾸어 준다.
     handlerAdapter 에서는 WebDataBinder 를 이용하여 데이터를 변호나해주는데 또 WebDataBinder 는 conversionService 를 통하여 요청으로 온
     파라미터를(string 타입) 각각 커맨더객체의 프로퍼티 타입으로 변환해준다.
     * 참고로 conversionService 는 @EnableWebMvc 를 통해 설정이 된다.(정확히는 DefaultFormattingConversionService 가 빈으로 등록이 되어진다.)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime from;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime to;

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

}