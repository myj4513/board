package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dao.CommentDao;
import study.board.dto.Article;
import study.board.dto.Comment;
import study.board.dto.CommentForm;
import study.board.dto.CommentView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public void write(int userId, CommentForm commentForm, int articleId){
        commentDao.add(new Comment(articleId, commentForm.getContent(), userId));
    }

    public List<CommentView> findAllComments(int articleId){
        return commentDao.findAllByArticleId(articleId);
    }

}
