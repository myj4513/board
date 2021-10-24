package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.service.UserService;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/ajax")
@RequiredArgsConstructor
public class AjaxController {

    private final UserService userService;

    @PostMapping("/login")
    //http 요청으로 들어오면 @ModelAttribute로 LoginForm을 받지만, ajax 요청에서 처리하는 방법을 모르겠습니다.
    //LoginForm으로 받지 못하여 validation 적용이 불가..
    //validation 로직을 새로 짜야함
    public String ajaxLogin(@RequestParam String loginId, String password){
        LoginForm loginForm = new LoginForm(loginId, password);
        User loginUser = userService.login(loginForm);

        //로그인 실패
        if (loginUser==null){
            return "WRONG";
        }
        return "OK";
    }
}
