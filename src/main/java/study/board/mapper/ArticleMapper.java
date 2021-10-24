package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.dto.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    public void add(Article article);
    public Article findById(int articleId);
    public List<Article> findAll();

}
