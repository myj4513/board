package study.board.mapper;

import org.apache.ibatis.annotations.Param;
import study.board.dto.ArticleLikes;

public interface ArticleLikesMapper {
    public ArticleLikes getLikes(@Param("articleId") int articleId, @Param("userId") int userId);
    public void addLikes(@Param("articleId") int articleId, @Param("userId") int userId);
    public void updateLikes(@Param("articleId") int articleId, @Param("userId") int userId, @Param("likes") int likes);
    public void updateDislikes(@Param("articleId") int articleId, @Param("userId") int userId, @Param("dislikes") int dislikes);
    public int countLikes(@Param("articleId") int articleId);
    public int countDislikes(@Param("articleId") int articleId);
}
