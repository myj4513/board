package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.board.enums.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Category category;

}
