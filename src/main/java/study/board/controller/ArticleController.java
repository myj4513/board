package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dto.Article;
import study.board.dto.ArticleForm;
import study.board.dto.CommentForm;
import study.board.dto.User;
import study.board.service.ArticleService;
import study.board.service.CommentService;
import study.board.utils.Category;
import study.board.utils.SessionConst;
import study.board.validation.CategoryValidator;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final CommentService commentService;
    private final ArticleService articleService;
    private final CategoryValidator categoryValidator;

    //글작성페이지 GET
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/form/write";
    }

    //글작성페이지 POST
    @PostMapping("/write")
    public String write(@Validated @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user){

        categoryValidator.validate(articleForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "article/form/write";
        }

        articleService.write(articleForm, user.getId());
        return "redirect:/";
    }

    //글상세보기
    @GetMapping("{id}")
    public String article(@PathVariable int id, Model model){

        Optional<Article> article = articleService.findById(id);

        if(article.isPresent()){
            model.addAttribute("comments", commentService.findAllComments(id));
            model.addAttribute("commentForm", new CommentForm());
            model.addAttribute("article", article.get());
            return "article/article";
        } else{
            //article이 없는 경우
            return "redirect:/";
        }

    }

    //글상세보기
    @PostMapping("{id}")
    public String addComment(@PathVariable int id, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user, @Validated @ModelAttribute CommentForm commentForm, BindingResult bindingResult){

        Optional<Article> article = articleService.findById(id);

        if(article.isPresent()){
            model.addAttribute("article", article.get());
            model.addAttribute("comments", commentService.findAllComments(id));
            model.addAttribute("commentForm", new CommentForm());
            if(bindingResult.hasErrors()){
                return "article/article";
            }

            //작성된 댓글 등록
            commentService.write(user.getId(), commentForm, id);
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
