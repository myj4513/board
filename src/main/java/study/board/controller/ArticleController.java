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
import study.board.exceptions.NoArticleFoundException;
import study.board.service.ArticleLikesService;
import study.board.service.ArticleService;
import study.board.service.CommentService;
import study.board.enums.Category;
import study.board.utils.SessionConst;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final CommentService commentService;
    private final ArticleService articleService;
    private final ArticleLikesService articleLikesService;

    //글작성페이지 GET
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/form/write";
    }

    //글작성페이지 POST
    @PostMapping("/write")
    public String write(@Validated @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER) User user){

        if(bindingResult.hasErrors()){
            return "article/form/write";
        }

        articleService.write(articleForm, user.getId());
        return "redirect:/";
    }

    //글상세보기
    @GetMapping("/{articleId}")
    public String article(@PathVariable int articleId, Model model){

        Article article = articleService.findById(articleId);

        if(article!=null){
            model.addAttribute("comments", commentService.findAllComments(articleId));
            model.addAttribute("commentsCount", commentService.countComments(articleId));
            model.addAttribute("commentForm", new CommentForm());
            model.addAttribute("article", article);
            model.addAttribute("likes", articleLikesService.countLikes(articleId));
            model.addAttribute("dislikes", articleLikesService.countDislikes(articleId));
            return "article/article";
        } else{
            //article이 없는 경우
            return "redirect:/";
        }

    }

    //댓글 작성하기
    @PostMapping("/{articleId}/comment")
    public String addComment(@PathVariable int articleId, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user, @Validated @ModelAttribute CommentForm commentForm, BindingResult bindingResult){

        try{
            Article article = articleService.findById(articleId);

            model.addAttribute("articleId", articleId);
            model.addAttribute("article", article);
            model.addAttribute("comments", commentService.findAllComments(articleId));
            model.addAttribute("commentForm", new CommentForm());

            if(bindingResult.hasErrors()){
                return "article/article";
            }

            //작성된 댓글 등록
            commentService.write(user.getId(), commentForm, articleId);
            return "redirect:/article/{articleId}";
        } catch (NoArticleFoundException e){
            log.error(e.getMessage());
            //어떤 처리를 해주어야 할까?
            return "redirect:/";
        }
    }

    //article button likes
    @GetMapping("/{articleId}/like")
    public String articleLike(@PathVariable int articleId, @SessionAttribute(name = SessionConst.LOGIN_USER) User user){
        //NullPointerException 발생, ServiceTest에선 이상 없음
        articleLikesService.toggleLike(articleId, user.getId());
        return "redirect:/article/{articleId}";
    }



    @ModelAttribute("categories")
    public Map<Category, String> categories(){
        Map<Category, String> map = new HashMap<>();

        for(Category c : Category.values()){
            map.put(c, c.getCategoryName());
        }

        return map;
    }
}
