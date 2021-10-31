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
    @Size(min = 3, max = 20, message = "최소 3자 이상, 20자 이하여야 합니다.")
    private String loginId;
    @Size(min = 8, max = 20, message = "최소 3자 이상, 20자 이하여야 합니다.")
    private String password;
    @NotBlank(message = "필수 입력란입니다.")
    private String name;
    @NotBlank(message = "필수 입력란입니다.")
    @Email(message = "이메일 형식이여야 합니다.")
    private String email;

    public User(String loginId, String password, String name, String email){
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}