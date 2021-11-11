package study.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@Slf4j
@SpringBootTest
class ArticleLikesMapperTest {

    @Autowired
    private ArticleLikesMapper articleLikesMapper;

    @Test
    void addTest(){
    }

    @Test
    void countLikesTest(){
        int cnt = articleLikesMapper.countLikes(6);
        log.info("count : {}", cnt);
    }

}