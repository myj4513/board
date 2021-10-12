package study.board.validator;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import study.board.dto.UserInput;
import study.board.utils.SHA256;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserInput.class.isAssignableFrom(clazz);
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        UserInput userInput = (UserInput) target;

        String encryptedPassword = SHA256.encrypt(userInput.getPassword());
        String encryptedPasswordCheck = SHA256.encrypt(userInput.getPasswordCheck());

        //비밀번호 확인 불일치
        if(!encryptedPassword.equals(encryptedPasswordCheck)){
//            errors.addError(new FieldError("userInput", "passwordCheck", userInput.getPasswordCheck(), false, new String[]{"match.userInput.passwordCheck"}, null, null));
            errors.rejectValue("passwordCheck", "match", null, null);
        }
    }
}
