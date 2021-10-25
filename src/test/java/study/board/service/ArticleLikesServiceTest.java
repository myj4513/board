package study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleLikesServiceTest {

    @Autowired
    private ArticleLikesService articleLikesService;

    @Test
    void getLikesTest(){
    }

    @Test
    void addTest(){

    }

    @Test
    void toggleTest(){
        articleLikesService.toggleLike(6,5);
    }

    @Test
    void countLikesTest(){
        int count = articleLikesService.countLikes(6);
        log.info("count : {}", count);
    }
}