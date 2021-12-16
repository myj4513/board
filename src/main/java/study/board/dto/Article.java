package study.board.dto;

import lombok.*;
import study.board.enums.Category;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class Article {

    private int id;
    private String title;
    private String content;
    private String regDate;
    private int views;
    private int userId;
    private Category category;

    public Article(ArticleForm articleForm, int userId){
        this.title = articleForm.getTitle();
        this.content = articleForm.getContent();
        this.category = articleForm.getCategory();
        this.userId = userId;
    }

}
