package study.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ArticleDislikesMapperTest {

    @Autowired
    private ArticleDislikesMapper articleDislikesMapper;

    @Test
    void addTest(){
        articleDislikesMapper.add(7, 1);
    }

    @Test
    void getDislikesTest(){
        Integer likes1 = articleDislikesMapper.getDislikes(8, 1);
        Assertions.assertThat(likes1).isNull();

        Integer likes2 = articleDislikesMapper.getDislikes(7,1);
        Assertions.assertThat(likes2).isEqualTo(1);
    }

    @Test
    void toggleTest(){
        Integer likes = articleDislikesMapper.getDislikes(9, 1);
        log.info("before toggle : {}", likes);
        if(likes ==null){
            articleDislikesMapper.add(9,1);
        } else if(likes ==0){
            articleDislikesMapper.toggle(9,1, 1);
        }  else if(likes ==1){
            articleDislikesMapper.toggle(9,1, 0);
        }
        likes = articleDislikesMapper.getDislikes(9, 1);
        log.info("after toggle : {}", likes);
    }

    @Test
    void countLikesTest(){
        int cnt = articleDislikesMapper.countDislikes(7);
        log.info("count : {}", cnt);
    }

}