package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import study.board.enums.Category;
import study.board.enums.SortBy;
import study.board.error.ErrorResponse;
import study.board.exceptions.NoArticlesException;
import study.board.service.ArticleService;
import study.board.utils.ResponseEntityCreation;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                            @RequestParam(value = "sortBy", defaultValue = "LATEST") SortBy sortBy,
                            @RequestParam(value = "category", defaultValue = "ALL") Category category,
                            @RequestParam(value = "measure", defaultValue = "10") int measure,
                            Model model){
        model.addAttribute("articles", articleService.getArticles(pageNum, sortBy, category, measure));
        model.addAttribute("totalPages", articleService.getTotalPages(measure, category));
        model.addAttribute("currSortBy", sortBy);
        model.addAttribute("currCategory", category);
        model.addAttribute("measure", measure);
        model.addAttribute("curPage", pageNum);
        return "home";
    }

    @ModelAttribute("SortBy")
    public SortBy[] sortByList(){
        return SortBy.values();
    }

    @ModelAttribute("Categories")
    public Category[] categories(){
        return Category.values();
    }

    @ExceptionHandler(NoArticlesException.class)
    public ResponseEntity<ErrorResponse> handleNoArticleException(){
        return ResponseEntityCreation.getSingleStringResponseEntity("게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}
