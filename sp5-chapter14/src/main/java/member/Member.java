package member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Member 객체
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
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
