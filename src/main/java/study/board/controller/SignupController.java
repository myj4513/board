package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dto.User;
import study.board.service.UserService;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "form/signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user, BindingResult bindingResult) throws NoSuchAlgorithmException {

        if(bindingResult.hasErrors()){
            log.info("error : {}", bindingResult);
            return "form/signup";
        }

        //중복 아이디 처리
        if(!userService.signup(user)){
            bindingResult.rejectValue("loginId", "exists.user.loginId", null);
            return "form/signup";
        }

        //회원가입 성공
        return "redirect:/login";
    }
}
