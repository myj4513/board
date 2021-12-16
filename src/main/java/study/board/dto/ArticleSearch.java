package study.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.board.enums.Category;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
public class ArticleSearch {
    private int id;
    private Category category;
    private String title;
    private String content;
    private String name;
    private LocalDateTime regDate;
    private int views;
}
