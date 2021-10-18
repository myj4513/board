package study.board.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.dto.ArticleView;

import java.util.List;


@SpringBootTest
class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    void findAll(){
        List<ArticleView> list = articleDao.findAll();
        System.out.println(list);
    }

}