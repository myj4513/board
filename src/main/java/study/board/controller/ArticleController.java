package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dao.ArticleDao;
import study.board.dto.Article;
import study.board.dto.ArticleForm;
import study.board.dto.User;
import study.board.service.ArticleService;
import study.board.utils.Category;
import study.board.utils.SessionConst;
import study.board.validation.CategoryValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleDao articleDao;
    private final ArticleService articleService;
    private final CategoryValidator categoryValidator;

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/form/write";
    }

    @PostMapping("/write")
    public String write(@Validated @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user){

        categoryValidator.validate(articleForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "article/form/write";
        }

        articleService.write(articleForm, user.getId());
        return "redirect:/";
    }

    @GetMapping("{id}")
    public String article(@PathVariable int id, Model model){

        Optional<Article> article = articleDao.findById(id);

        if(article.isPresent()){
            model.addAttribute("article", article.get());
            return "article/article";
        } else{
            //article이 없는 경우
            return "redirect:/";
        }

    }

    @ModelAttribute("categories")
    public List<String> categories(){
        List<String> categories = new ArrayList<>();

        for(Category c : Category.values()){
            categories.add(c.name());
        }

        return categories;
    }

}
