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
import study.board.exceptions.NoArticleFoundException;
import study.board.service.*;
import study.board.enums.Category;
import study.board.utils.SessionConst;


@Slf4j
@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final CommentService commentService;
    private final CommentLikesService commentLikesService;
    private final ArticleService articleService;
    private final UserService userService;
    private final ArticleLikesService articleLikesService;

    //글작성페이지 GET
    @GetMapping("/article/write")
    public String writeForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/form/write";
    }

    //글작성페이지 POST
    @PostMapping("/article/write")
    public String write(@Validated @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){

        if(bindingResult.hasErrors()){
            return "article/form/write";
        }

        articleService.write(articleForm, userId);
        return "redirect:/";
    }

    //글상세보기
    @GetMapping("/{articleId}")
    public String article(@PathVariable int articleId, Model model){
        Article article = articleService.findById(articleId);

        articleService.addView(articleId); //조회수 +1
        model.addAttribute("comments", commentService.findAllComments(articleId));
        model.addAttribute("commentsCount", commentService.countComments(articleId));
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("article", article);
        model.addAttribute("writerName", userService.getWriterNameById(article.getUserId()));
        model.addAttribute("likes", articleLikesService.countLikes(articleId));
        model.addAttribute("dislikes", articleLikesService.countDislikes(articleId));
        return "article/article";
    }

    //댓글 작성하기
    @PostMapping("/{articleId}/comment")
    public String addComment(@PathVariable int articleId, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) int userId, @Validated @ModelAttribute CommentForm commentForm, BindingResult bindingResult){

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
            commentService.write(userId, commentForm, articleId);
            return "redirect:/articles/{articleId}";
        } catch (NoArticleFoundException e){
            log.error(e.getMessage());
            //어떤 처리를 해주어야 할까?
            return "redirect:/";
        }
    }

    //article button likes
    @GetMapping("/{articleId}/likes")
    public String articleLikes(@PathVariable int articleId, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){
        articleLikesService.toggleLike(articleId, userId);
        return "redirect:/articles/{articleId}";
    }

    //article button dislikes
    @GetMapping("/{articleId}/dislikes")
    public String articleDislikes(@PathVariable int articleId, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){
        articleLikesService.toggleDislikes(articleId, userId);
        return "redirect:/articles/{articleId}";
    }

    //comment button likes
    @GetMapping("/{articleId}/comment/{commentId}/likes")
    public String commentLikes(@PathVariable int articleId, @PathVariable int commentId, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){
        commentLikesService.toggleLikes(userId, commentId);
        return "redirect:/articles/{articleId}";
    }
    //comment button dislikes
    @GetMapping("/{articleId}/comment/{commentId}/dislikes")
    public String commentDislikes(@PathVariable int articleId, @PathVariable int commentId, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){
        commentLikesService.toggleDislikes(userId, commentId);
        return "redirect:/articles/{articleId}";
    }

    @GetMapping("/{articleId}/edit")
    public String editArticle(@PathVariable int articleId, Model model){
        model.addAttribute("article", articleService.findArticleById(articleId));
        return "article/form/edit";
    }

    @ModelAttribute("categories")
    public Category[] categories(){
        return Category.values();
    }
}
