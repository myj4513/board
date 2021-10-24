package study.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ArticleLikes {
    private int id;
    private int articleId;
    private int userId;
    private int likes;

    public ArticleLikes(int articleId, int userId, int likes){
        this.articleId = articleId;
        this.userId = userId;
        this.likes = likes;
    }
}
