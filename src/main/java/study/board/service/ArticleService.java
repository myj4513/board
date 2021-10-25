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

    public List<ArticleView> getArticles(int pageNum, SortBy sortBy, Category category){
        int sArticleNum = (pageNum-1)*10;
        List<ArticleView> articles = new ArrayList<>();
        if(category==Category.ALL){
            if(sortBy==SortBy.VIEWS){
                articles = articleMapper.findNext10OrderByViews(sArticleNum, category.name());
            }
            if(sortBy==SortBy.LATEST){
                articles = articleMapper.findNext10OrderByRegDate(sArticleNum, category.name());
            }
            return articles;
        }
        if(sortBy==SortBy.VIEWS){
            articles = articleMapper.findNext10WithCategoryOrderByViews(sArticleNum, category.name());
        }
        if(sortBy==SortBy.LATEST){
            articles = articleMapper.findNext10WithCategoryOrderByRegDate(sArticleNum, category.name());
        }
        return articles;
    }


    public int getTotalPages(){
        int articleNum = articleMapper.count();

        if(articleNum<=0) throw new NoArticlesException();
        return (articleNum-1)/10 + 1;
    }

    public void addView(int articleId){
        articleMapper.addView(articleId, articleMapper.getView(articleId)+1);
    }
}
