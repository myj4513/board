package study.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleLikesMapperTest {

//    @Autowired
//    private ArticleLikesMapper articleLikesMapper;
//
//    @Test
//    void addTest(){
//        articleLikesMapper.add(7, 1);
//    }
//
//    @Test
//    void getLikesTest(){
//        Integer likes1 = articleLikesMapper.getLikes(8, 1);
//        Assertions.assertThat(likes1).isNull();
//
//        Integer likes3 = articleLikesMapper.getLikes(6,5);
//        log.info("likes3 : {}", likes3);
//    }
//
//    @Test
//    void toggleTest(){
//        Integer likes = articleLikesMapper.getLikes(9, 1);
//        log.info("before toggle : {}", likes);
//        if(likes ==null){
//            articleLikesMapper.add(9,1);
//        } else if(likes ==0){
//            articleLikesMapper.toggle(9,1, 1);
//        }  else if(likes ==1){
//            articleLikesMapper.toggle(9,1, 0);
//        }
//        likes = articleLikesMapper.getLikes(9, 1);
//        log.info("after toggle : {}", likes);
//    }
//
//    @Test
//    void countLikesTest(){
//        int cnt = articleLikesMapper.countLikes(6);
//        log.info("count : {}", cnt);
//    }

}