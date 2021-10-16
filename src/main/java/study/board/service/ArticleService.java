package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dao.ArticleDao;
import study.board.dto.Article;
import study.board.dto.ArticleForm;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

    public void write(ArticleForm articleForm , int userId) {

        Article article = new Article(articleForm);
        article.setUserId(userId);

        articleDao.add(article);
    }

}
