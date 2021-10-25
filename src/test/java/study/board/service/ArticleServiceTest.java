package study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void getTotalPagesTest(){
        int totalPages = articleService.getTotalPages();
        Assertions.assertThat(totalPages).isEqualTo(6);
    }
}
