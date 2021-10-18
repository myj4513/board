package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleView {

    private int id;
    private String title;
    private LocalDateTime regDate;


}
