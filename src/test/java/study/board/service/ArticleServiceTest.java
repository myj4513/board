package study.board.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.enums.Category;

@SpringBootTest
@Slf4j
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("페이지 단위와 카테고리 입력시 총 페이지 수를 반환하는 테스트")
    void getTotalPagesTest(){
        int totalPages = articleService.getTotalPages(30, Category.SPORTS);
        Assertions.assertThat(totalPages).isEqualTo(2);
    }
}
