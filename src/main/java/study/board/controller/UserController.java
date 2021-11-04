package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.board.dto.ArticleView;
import study.board.dto.User;
import study.board.service.ArticleService;
import study.board.utils.SessionConst;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class UserController {

    private final ArticleService articleService;

    @GetMapping("/info")
    public String info(@SessionAttribute(name = SessionConst.LOGIN_USER) User user, Model model) {
        model.addAttribute("user", user);
        return "user/mypage/info";
    }

    @GetMapping("/info/edit")
    public String editInfo(@SessionAttribute(name = SessionConst.LOGIN_USER) User user, Model model){
        model.addAttribute("user", user);
        return "user/mypage/form/editInfo";
    }


    @GetMapping("/articles")
    public String article(@RequestParam(value = "page", defaultValue = "1") int page, @SessionAttribute(name = SessionConst.LOGIN_USER) User user, Model model){
        List<ArticleView> articlesById = articleService.getArticlesById(page, user.getId());
        model.addAttribute("articles", articlesById);
        return "user/mypage/articles";
    }
}
