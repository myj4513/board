package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.dto.CommentLikes;
import study.board.mapper.CommentLikesMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentLikesService {

    private final CommentLikesMapper commentLikesMapper;

    public void getLikes(int commentId){
        commentLikesMapper.countLikes(commentId);
    }
    public void getDislikes(int commentId){
        commentLikesMapper.countDislikes(commentId);
    }

    public void toggleLikes(int userId, int commentId){
        if(hasLikes(userId, commentId)){
            CommentLikes likes = commentLikesMapper.getLikes(userId, commentId);
            if(likes.getLikes()==1){
                commentLikesMapper.updateLikes(userId, commentId, 0);
            }
            else if(likes.getLikes()==0){
                commentLikesMapper.updateLikes(userId, commentId, 1);
            }
            return;
        }
        commentLikesMapper.addLikes(userId, commentId);
        commentLikesMapper.updateLikes(userId, commentId, 1);
    }

    public void toggleDislikes(int userId, int commentId){
        if(hasLikes(userId, commentId)){
            CommentLikes likes = commentLikesMapper.getLikes(userId, commentId);
            log.info("likes.getDislikes() : {}" ,likes.getDislikes());
            if(likes.getDislikes()==1){
                commentLikesMapper.updateDislikes(userId, commentId, 0);
            }
            else if(likes.getDislikes()==0){
                commentLikesMapper.updateDislikes(userId, commentId, 1);
            }
            return;
        }
        commentLikesMapper.addLikes(userId, commentId);
        commentLikesMapper.updateDislikes(userId, commentId, 1);
    }

    public boolean hasLikes(int userId, int commentId){
        CommentLikes likes = commentLikesMapper.getLikes(userId, commentId);
        if(likes==null){
            log.info("hasLikes : false");
            return false;
        }

        log.info("hasLikes : true");
        return true;
    }
}
