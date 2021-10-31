package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.board.dto.CommentLikes;

@Mapper
public interface CommentLikesMapper {
    public CommentLikes getLikes(@Param("userId") int userId, @Param("commentId") int commentId);
    public void addLikes(@Param("userId") int userId, @Param("commentId") int commentId);
    public int countLikes(int commentId);
    public int countDislikes(int commentId);
    public void updateLikes(@Param("userId") int userId, @Param("commentId") int commentId, @Param("likes") int likes);
    public void updateDislikes(@Param("userId") int userId, @Param("commentId") int commentId, @Param("dislikes") int dislikes);
}
