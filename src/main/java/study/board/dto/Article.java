package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Article {

    private int id;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private int views;
    private int userId;
    private String category;

    public Article(ArticleForm articleForm){
        this.title = articleForm.getTitle();
        this.content = articleForm.getContent();
        this.category = articleForm.getCategory();
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

}
