package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.board.dto.Article;
import study.board.dto.ArticleView;

import java.util.List;

@Mapper
public interface ArticleMapper {

    public void add(Article article);
    public Article findById(int articleId);
    public int count();
    public int getView(int articleId);
    public void addView(@Param("articleId") int articleId, @Param("views") int views);
    public List<ArticleView> findArticlesById(@Param("num") int num, @Param("userId") int userId);
    public List<ArticleView> findNext10WithCategoryOrderByViews(@Param("num") int num, @Param("category") String category);
    public List<ArticleView> findNext10WithCategoryOrderByRegDate(@Param("num") int num, @Param("category") String category);
    public List<ArticleView> findNext10WithCategoryOrderByLikes(@Param("num") int num, @Param("category") String category);
    public List<ArticleView> findNext10OrderByViews(@Param("num") int num);
    public List<ArticleView> findNext10OrderByRegDate(@Param("num") int num);
    public List<ArticleView> findNext10OrderByLikes(@Param("num") int num);
}
