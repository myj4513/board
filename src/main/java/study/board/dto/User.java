package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {
    private String loginId;
    private String password;
    private String name;
    private String email;

    public User(UserInput userInput){
        this.loginId = userInput.getLoginId();
        this.password = userInput.getPassword();
        this.name = userInput.getName();
        this.email = userInput.getEmail();
    }
}