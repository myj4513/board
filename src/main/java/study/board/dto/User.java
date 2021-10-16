package study.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class User {
    private int id;
    @Size(min = 3, max = 20)
    private String loginId;
    @Size(min = 8, max = 20)
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    public User(String loginId, String password, String name, String email){
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}