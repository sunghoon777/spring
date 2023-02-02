package register;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRequestValidator implements Validator {

    private static final String emailRegExp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;

    public RegisterRequestValidator(){
        pattern = Pattern.compile(emailRegExp);
    }


    @Override
    public boolean supports(Class<?> clazz) {
        //clazz 가 RegisterRequest 를 타입이여야함.
        return RegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest registerRequest = (RegisterRequest) target;
        if(registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()){
            errors.rejectValue("email","required");

        }else{
            Matcher matcher = pattern.matcher(registerRequest.getEmail());
            if(!matcher.matches()){
                errors.rejectValue("email","bad");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","required");
        if(registerRequest.getPassword() != null){
            if(!registerRequest.isPasswordEqualToConfirmPassword()){
                errors.rejectValue("confirmPassword","nomatch");
            }
        }
    }

}
