package study.board.mapper;

import org.apache.ibatis.annotations.Param;

public interface ArticleLikesMapper {
    public Integer getLikes(@Param("articleId") int articleId, @Param("userId") int userId);
    public void add(@Param("articleId") int articleId, @Param("userId") int userId);
    public void toggle(@Param("articleId") int articleId, @Param("userId") int userId, @Param("likes") int likes);
    public int countLikes(@Param("articleId") int articleId);
}
