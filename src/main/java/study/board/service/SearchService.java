package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.dto.ArticleSearch;
import study.board.enums.Category;
import study.board.mapper.SearchMapper;
import study.board.utils.SearchKeyWordValidator;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    public List<ArticleSearch> searchByKeyWord(int pageNum, Category category, int measure, String q, String target){

        String[] filteredKeyWords = filterKeyWords(q);

        int sArticleNum = (pageNum-1)*measure;
        List<ArticleSearch> articles = null;

        if(category==Category.ALL){
            articles = searchMapper.searchArticleAll(sArticleNum, measure, filteredKeyWords, target);
        }
        else{
            articles = searchMapper.searchArticleCategory(sArticleNum, category.name(), measure, filteredKeyWords, target);
        }
        return articles;
    }

    public int getTotalPages(int measure, Category category, String q, String target){
        String[] filteredKeyWords = filterKeyWords(q);
        int articleNum;
        if (target.equals("comment")) {
            if(category==Category.ALL)
                articleNum = searchMapper.countCommentAll(filteredKeyWords);
            else
                articleNum = searchMapper.countCommentCategory(category.name(), filteredKeyWords);
        }
        if(category==Category.ALL)
            articleNum = searchMapper.countArticleAll(filteredKeyWords, target);
        else
            articleNum = searchMapper.countArticleCategory(category.name(), filteredKeyWords, target);

        return (articleNum-1)/measure + 1;
    }

    private String[] filterKeyWords(String q){
        return SearchKeyWordValidator.filterKeyWords(q.split("\\s+"));
    }
}
