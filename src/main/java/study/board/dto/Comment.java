package study.board.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
public class Comment {
    private int id;
    private int articleId;
    private String content;
    private LocalDateTime regDate;
    private int userId;

    public Comment(int articleId, String content, int userId){
        this.articleId = articleId;
        this.content = content;
        this.userId = userId;
    }
}
