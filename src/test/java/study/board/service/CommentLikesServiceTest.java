package study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommentLikesServiceTest {

    @Autowired
    CommentLikesService commentLikesService;

    @Test
    void hasLikesTest(){
        boolean result = commentLikesService.hasLikes(4, 11);
        Assertions.assertThat(result).isEqualTo(false);
    }

}