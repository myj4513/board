package study.board.dao;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.dto.Comment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    void add() {
        System.out.println(1);
        Comment comment = new Comment(1, "i like it", 4);

        commentDao.add(comment);

    }
}