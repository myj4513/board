package study.board.mapper;

import org.apache.ibatis.annotations.Param;
import study.board.dto.ArticleDislikes;
import study.board.dto.ArticleLikes;

public interface ArticleDislikesMapper {
    public Integer getDislikes(@Param("articleId") int articleId, @Param("userId") int userId);
    public void add(@Param("articleId") int articleId, @Param("userId") int userId);
    public void toggle(@Param("articleId") int articleId, @Param("userId") int userId, @Param("dislikes") int dislikes);
    public int countDislikes(@Param("articleId") int articleId);
}
