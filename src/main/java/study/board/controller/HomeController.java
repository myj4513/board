package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import study.board.dao.ArticleDao;
import study.board.dto.ArticleView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleDao articleDao;  //릴레이 메서드로 수정하세요

    @GetMapping("/")
    public String homeLogin(){
        log.info("Home Controller: localhost:8080/"); //prototypejs ajax가 들어오는지 확인
        return "home";
    }

    @ModelAttribute("articles")
    public List<ArticleView> articles(){
        List<ArticleView> articles = articleDao.findAll();
        return articles;
    }
}
