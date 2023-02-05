package member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

//Member 객체
public class Member {

    private Long id;
    private String email;
    @JsonIgnore // 응답을 할 때 이 프로퍼티를 보내지 않는다.
    private String password;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd") // 응답 json 값을 배열이 아닌 string 값으로 설정해줌. 그리고 string 의 날짜 패턴을 yyyy-MM-dd 로 설정함.
    private LocalDateTime registerDateTime;

    public Member(String email, String password, String name, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void changePassword(String oldPassword, String newPassword) {
        //현재 패스워드가 old password 와 일치하는지 확인한다.
        if(!(this.password.equals(oldPassword)))
            throw new WrongIdPasswordException();
        //일치하면 바꾸어준다.
        this.password = newPassword;
    }

    public boolean matchPassword(String password){
       return this.password.equals(password);
    }
}
