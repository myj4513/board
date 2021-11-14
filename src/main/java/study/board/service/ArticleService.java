package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dto.Article;
import study.board.dto.ArticleForm;
import study.board.dto.ArticleView;
import study.board.enums.Category;
import study.board.enums.SortBy;
import study.board.exceptions.NoArticleFoundException;
import study.board.exceptions.NoArticlesException;
import study.board.exceptions.NoAuthorityException;
import study.board.mapper.ArticleMapper;

import java.util.ArrayList;
import java.util.List;

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

    public List<ArticleView> getArticles(int pageNum, SortBy sortBy, Category category, int measure){
        int sArticleNum = (pageNum-1)*measure;
        List<ArticleView> articles = new ArrayList<>();
        if(category==Category.ALL){
            if(sortBy==SortBy.VIEWS){
                articles = articleMapper.findOrderByViews(sArticleNum, measure);
            }
            if(sortBy==SortBy.LATEST){
                articles = articleMapper.findOrderByRegDate(sArticleNum, measure);
            }
            if(sortBy==SortBy.LIKES){
                articles = articleMapper.findOrderByLikes(sArticleNum, measure);
            }
            return articles;
        }
        if(sortBy==SortBy.VIEWS){
            articles = articleMapper.findWithCategoryOrderByViews(sArticleNum, category.name(), measure);
        }
        if(sortBy==SortBy.LATEST){
            articles = articleMapper.findWithCategoryOrderByRegDate(sArticleNum, category.name(), measure);
        }
        if(sortBy==SortBy.LIKES){
            articles = articleMapper.findWithCategoryOrderByLikes(sArticleNum, category.name(), measure);
        }
        return articles;
    }

    public List<ArticleView> getArticlesById(int pageNum, int userId, int measure){
        int sArticleNum = (pageNum-1)*measure;
        return articleMapper.findArticlesById(sArticleNum, userId);
    }

    public int getTotalPages(int measure){
        int articleNum = articleMapper.count();

        if(articleNum<=0) throw new NoArticlesException();
        return (articleNum-1)/measure + 1;
    }

    public void addView(int articleId){
        articleMapper.addView(articleId, articleMapper.getView(articleId)+1);
    }

    public Article findArticleById(int articleId) {
        return articleMapper.findById(articleId);
    }

    public void deleteArticle(int articleId, int userId) {
        if(!hasAuthority(articleId, userId)){
            throw new NoAuthorityException();
        }
        articleMapper.deleteById(articleId);
    }

    private boolean hasAuthority(int articleId, int userId){
        Article article = findArticleById(articleId);
        if(article.getUserId()==userId)
            return true;
        return false;
    }

    public void editArticle(ArticleForm articleForm, int articleId, int userId) {
        if(!hasAuthority(articleId, userId)){
            throw new NoAuthorityException();
        }
        articleMapper.updateById(articleId, articleForm);
    }
}
