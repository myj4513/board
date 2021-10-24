package study.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.dto.Article;
import study.board.dto.ArticleForm;
import study.board.enums.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    void addTest(){
        Article article = new Article(new ArticleForm("title", "content", Category.DAILY),1);

        articleMapper.add(article);
    }

    @Test
    void findByIdTest(){
        Article article = articleMapper.findById(9);
        log.info("id: {}", article.getId());
        log.info("title: {}", article.getTitle());
        log.info("content: {}", article.getContent());
        log.info("regDate: {}", article.getRegDate());
        log.info("views: {}", article.getViews());
        log.info("userId: {}", article.getUserId());
        log.info("category: {}", article.getCategory());
    }

    @Test
    void findAll(){
        List<Article> articles = articleMapper.findAll();
        for(Article a : articles){
            log.info("id: {}", a.getId());
            log.info("title: {}", a.getTitle());
            log.info("content: {}", a.getContent());
            log.info("regDate: {}", a.getRegDate());
            log.info("views: {}", a.getViews());
            log.info("userId: {}", a.getUserId());
            log.info("category: {}", a.getCategory());
        }
    }
}