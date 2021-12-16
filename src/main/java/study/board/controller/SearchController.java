package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.board.enums.Category;
import study.board.service.SearchService;
import study.board.utils.SearchKeyWordValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/article")
    public String searchArticle(@RequestParam(value = "q") String q,
                         @RequestParam(value = "page", defaultValue = "1") int pageNum,
                         @RequestParam(value = "category", defaultValue = "ALL") Category category,
                         @RequestParam(value = "measure", defaultValue = "10") int measure,
                         @RequestParam(value = "target", defaultValue = "title")String target,
                         Model model){

        if(SearchKeyWordValidator.isValid(q)){
            //return 오류의 경우 1.alert를 띄운다. 2. 검색페이지로 넘어가 에러메시지를 body에 출력해준다.
            //alert의 경우     방법 -> ajax를 적용한다.
            //                문제점 -> 검색바를 네비게이션 바가 아니라 body 본문에서 제공해야한다.

            //검색페이지의 경우  방법 -> 별도의 에러 페이지를 생성한다. or 검색 페이지에 오류 메시지를 출력한다.
            //                문제점 -> 페이지가 많아진다.         /  html이 복잡해진다.

        }

        //정상 검색 로직 실행
//        model.addAttribute("articles", searchService.searchByKeyWord(q));
        model.addAttribute("articles", searchService.searchByKeyWord(pageNum, category, measure, q, target));
        model.addAttribute("totalPages", searchService.getTotalPages(measure, category, q, target));
        model.addAttribute("currCategory", category);
        model.addAttribute("measure", measure);
        model.addAttribute("curPage", pageNum);
        model.addAttribute("target", target);
        model.addAttribute("q", q);

        return "search/searchArticle";

    }

    @ModelAttribute("Categories")
    public Category[] categories(){
        return Category.values();
    }

}
