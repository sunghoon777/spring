package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

    public void print(Member member){
        if(dateTimeFormatter == null){
            System.out.printf("회원 정보 : 아이디 = %d 이메일 = %s 이름 = %s  패스워드 = %s  등록일 = %tF \n",member.getId(),member.getEmail(),
                    member.getName(),member.getPassword(), member.getRegisterDateTime());
        }
        else{
            System.out.printf("회원 정보 : 아이디 = %d 이메일 = %s 이름 = %s  패스워드 = %s  등록일 = %s \n",member.getId(),member.getEmail(),
                    member.getName(),member.getPassword(),dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    @Autowired(required = false)
    //컨테이너 안에 DateTimeFormatter 빈객체가 존재하지 않으면 메서드가 실행되지 않음
    //Optional 이나 Nullable 로도 @Autowired 필수 여부를 지정할 수 있는데 만약 빈객체가 없으면 Optional은 Optional 객체가 주입되고 Nullable은 null 이 주입된다.
    //@Autowired(required = false) 은 메서드 실행 x but 나머지는 실행이 된다.
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
