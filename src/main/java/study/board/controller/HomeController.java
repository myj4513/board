package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.board.dao.ArticleDao;
import study.board.dto.ArticleView;
import study.board.service.ArticleService;
import study.board.utils.SessionConst;
import study.board.dto.User;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleDao articleDao;  //릴레이 메서드로 수정하세요

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER ,required = false) User user, Model model){

        if(user!=null) {
            model.addAttribute("user", user);
        }

        return "home";
    }

    @ModelAttribute("articles")
    public List<ArticleView> articles(){
        List<ArticleView> articles = articleDao.findAll();
        return articles;
    }
}
