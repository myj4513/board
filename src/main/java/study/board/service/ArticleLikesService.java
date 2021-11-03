package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.ArticleLikes;
import study.board.mapper.ArticleLikesMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleLikesService {

    private final ArticleLikesMapper articleLikesMapper;

    public int countLikes(int articleId){
        return articleLikesMapper.countLikes(articleId);
    }
    public int countDislikes(int articleId){
        return articleLikesMapper.countDislikes(articleId);
    }

    @Transactional
    public void toggleLike(int articleId, int userId){
        if(hasLikes(articleId, userId)){
            int likes = articleLikesMapper.getLikes(articleId, userId).getLikes();
            //else문 사용을 최대한 지양하려 하였습니다만, 여기에서는 사용하는 것이 불필요한 return 문 중복 작성을 막아준다고 생각했습니다.
            if(likes==1) {
                articleLikesMapper.updateLikes(articleId, userId, 0);
            } else{
                articleLikesMapper.updateLikes(articleId, userId, 1);
            }
            return;
        }
        articleLikesMapper.addLikes(articleId, userId);
        articleLikesMapper.updateLikes(articleId, userId, 1);
    }

    @Transactional
    public void toggleDislikes(int articleId, int userId){
        if(hasLikes(articleId, userId)){
            int dislikes = articleLikesMapper.getLikes(articleId, userId).getDislikes();
            //else문 사용을 최대한 지양하려 하였습니다만, 여기에서는 사용하는 것이 불필요한 return 문 중복 작성을 막아준다고 생각했습니다.
            if(dislikes==1) {
                articleLikesMapper.updateDislikes(articleId, userId, 0);
            } else{
                articleLikesMapper.updateDislikes(articleId, userId, 1);
            }
            return;
        }
        articleLikesMapper.addLikes(articleId, userId);
        articleLikesMapper.updateDislikes(articleId, userId, 1);
    }

    private boolean hasLikes(int articleId, int userId){
        ArticleLikes likes = articleLikesMapper.getLikes(articleId, userId);
        if(likes==null) return false;
        return true;
    }

}
