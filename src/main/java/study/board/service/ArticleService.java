package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dto.Article;
import study.board.dto.ArticleForm;
import study.board.exceptions.NoArticleFoundException;
import study.board.mapper.ArticleMapper;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    public void write(ArticleForm articleForm , int userId) {
        Article article = new Article(articleForm, userId);
        articleMapper.add(article);
    }

    public Article findById(int articleId) {
        Article article = articleMapper.findById(articleId);

        if(article==null) throw new NoArticleFoundException();
        return article;
    }
}
