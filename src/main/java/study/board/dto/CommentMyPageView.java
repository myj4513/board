package study.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.board.enums.Category;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentMyPageView {
    private int articleId;
    private Category category;
    private String content;
    private int likes;
    private LocalDateTime regdate;
}
