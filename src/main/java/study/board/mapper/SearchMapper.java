package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.board.dto.Article;
import study.board.dto.ArticleSearch;

import java.util.List;

@Mapper
public interface SearchMapper {

    public List<ArticleSearch> searchArticleAll(@Param("num") int num, @Param("measure") int measure, @Param("keyWords") String[] keyWords, @Param("target") String target);
    public List<ArticleSearch> searchCommentAll(@Param("num") int num, @Param("measure") int measure, @Param("keyWords") String[] keyWords);
    public List<ArticleSearch> searchArticleCategory(@Param("num") int num, @Param("category")String category, @Param("measure") int measure, @Param("keyWords") String[] keyWords, @Param("target") String target);
    public List<ArticleSearch> searchCommentCategory(@Param("num") int num, @Param("category")String category, @Param("measure") int measure, @Param("keyWords") String[] keyWords);
    public int countArticleCategory(@Param("category")String category, @Param("keyWords") String[] keyWords, @Param("target") String target);
    public int countArticleAll(@Param("keyWords") String[] keyWords, @Param("target") String target);
    public int countCommentCategory(@Param("category")String category, @Param("keyWords") String[] keyWords);
    public int countCommentAll(@Param("keyWords") String[] keyWords);
}
