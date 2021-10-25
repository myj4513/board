package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dto.Comment;
import study.board.dto.CommentForm;
import study.board.dto.CommentView;
import study.board.mapper.CommentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public void write(int userId, CommentForm commentForm, int articleId){
        commentMapper.add(new Comment(articleId, commentForm.getContent(), userId));
    }

    public List<CommentView> findAllComments(int articleId){
        return commentMapper.findAllByArticleId(articleId);
    }
    public int countComments(int articleId){
        return commentMapper.countComments(articleId);
    }

}
