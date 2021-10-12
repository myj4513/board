package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
    @Size(min = 3, max = 20)
    private String loginId;
    @Size(min = 8, max = 20)
    private String password;
    private String passwordCheck;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
}