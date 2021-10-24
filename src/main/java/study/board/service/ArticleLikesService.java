package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.mapper.ArticleDislikesMapper;
import study.board.mapper.ArticleLikesMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleLikesService {

    //DB에서 article likes 와 dislikes 테이블을 분리하였습니다.
    //분리한 이유는 '좋아요'나 '싫어요'를 둘다 누르는 경우를 고려하면, 기존에 좋아요가 눌린 상태에서 싫어요가 눌렸을때 좋아요를 false로 변경하는
    //toggle 기능이 필요 없기 때문에 가볍고 빠른 로직을 위하여 분리했습니다.
    //기존 DB : id, article_id, user_id, likes, dislikes
    //변경 DB : id, article_id, user_id, likes      => article_likes
    //         id, article_id, user_id, dislikes   => article_dislikes
    //테이블의 컬럼이 5 -> 8개로 늘어난다는 단점이 있습니다.


    //테이블을 분리하면서 mapper interface도 분리하였으나 Service에서는 likes와 dislikes를 하나의 서비스로 통합하였는데, 분리해야 할까요?

    private final ArticleLikesMapper articleLikesMapper;
    private final ArticleDislikesMapper articleDislikesMapper;

    public int countLikes(int articleId){
        return articleLikesMapper.countLikes(articleId);
    }
    public int countDislikes(int articleId){
        return articleDislikesMapper.countDislikes(articleId);
    }
    public void toggleLike(int articleId, int userId){
        Integer likes = articleLikesMapper.getLikes(articleId, userId);
        if(likes==null){
            articleLikesMapper.add(articleId, userId);
        } else if(likes==0){
            articleLikesMapper.toggle(articleId, userId, 1);
        } else if(likes==1){
            articleLikesMapper.toggle(articleId, userId, 0);
        }
    }

}
