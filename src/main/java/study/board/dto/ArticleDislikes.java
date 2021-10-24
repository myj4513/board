package study.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ArticleDislikes {
    private int id;
    private int articleId;
    private int userId;
    private int dislikes;

    public ArticleDislikes(int articleId, int userId, int dislikes){
        this.articleId = articleId;
        this.userId = userId;
        this. dislikes = dislikes;
    }
}
