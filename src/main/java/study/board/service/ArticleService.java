package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dao.ArticleDao;
import study.board.dto.Article;
import study.board.dto.ArticleForm;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

    public void write(ArticleForm articleForm , int userId) {

        Article article = new Article(articleForm);
        article.setUserId(userId);

        articleDao.add(article);
    }

    public Optional<Article> findById(int articleId) {
        return articleDao.findById(articleId);
    }
}
