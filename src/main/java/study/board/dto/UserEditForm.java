package study.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserEditForm {
    @NotBlank(message = "필수 입력란입니다.")
    private String password;
    @NotBlank(message = "필수 입력란입니다.")
    private String name;
    @NotBlank(message = "필수 입력란입니다.")
    @Email(message = "이메일 형식이여야 합니다.")
    private String email;
}
