package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import study.board.dao.ArticleDao;
import study.board.dto.Article;
import study.board.dto.ArticleView;
import study.board.mapper.ArticleMapper;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleMapper articleMapper;

    @GetMapping("/")
    public String homeLogin(){
        log.info("Home Controller: localhost:8080/"); //prototypejs ajax가 들어오는지 확인
        return "home";
    }

    @ModelAttribute("articles")
    public List<ArticleView> articles(){
        List<Article> articles = articleMapper.findAll();
        //이부분도 수정하자
//        findAll을 Article이 아닌 ArticleView로 변경해야함
        return articles;
    }
}
