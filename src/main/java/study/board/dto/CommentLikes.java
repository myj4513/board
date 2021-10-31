package study.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CommentLikes {
    private int id;
    private int commentId;
    private int userId;
    private int likes;
    private int dislikes;
}
