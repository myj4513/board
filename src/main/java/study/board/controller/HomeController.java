package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import study.board.enums.Category;
import study.board.enums.SortBy;
import study.board.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String homeLogin(@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "sortBy", defaultValue = "LATEST") SortBy sortBy, @RequestParam(value = "category", defaultValue = "ALL") Category category, Model model){
        model.addAttribute("articles", articleService.getArticles(pageNum, sortBy, category));
        model.addAttribute("totalPages", articleService.getTotalPages());
        model.addAttribute("currSortBy", sortBy);
        model.addAttribute("currCategory", category);
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
}
