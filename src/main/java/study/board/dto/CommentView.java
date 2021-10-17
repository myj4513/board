package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
@AllArgsConstructor
public class CommentView {
    private int userId;
    private String content;
    private LocalDateTime regDate;
}
