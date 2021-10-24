package study.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.dto.Comment;
import study.board.dto.CommentView;

import java.util.List;


@SpringBootTest
@Slf4j
class CommentMapperTest {
    @Autowired
    CommentMapper commentMapper;

    @Test
    void addTest(){
        Comment comment = new Comment(6, "hi mapper is working", 3);
        commentMapper.add(comment);
    }

    @Test
    void findAllByArticleIdTest(){
        List<CommentView> commentViews = commentMapper.findAllByArticleId(6);
        for(CommentView comment : commentViews){
            log.info("content: {}", comment.getContent());
            log.info("regDate: {}", comment.getRegDate());
            log.info("userId: {}", comment.getUserId());
        }
    }
}