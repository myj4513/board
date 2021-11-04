package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.board.dto.Comment;
import study.board.dto.CommentMyPageView;
import study.board.dto.CommentView;

import java.util.List;

@Mapper
public interface CommentMapper {
    public void add(Comment comment);
    public List<CommentView> findAllByArticleId(int articleId);
    public int countComments(int articleId);
    public List<CommentMyPageView> findByUserId(@Param("sLimit") int sCommentNum, @Param("userId") int userId);
}
