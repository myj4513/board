package study.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.WrongLoginDataException;
import study.board.utils.SessionConst;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.service.UserService;
import study.board.utils.SessionControl;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "user/form/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) throws NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            return "user/form/login";
        }

        try{
            User loginUser = userService.login(loginForm);
            request.getSession().setAttribute(SessionConst.LOGIN_USER, loginUser.getId());
            return "redirect:"+redirectURL;
        } catch(EncryptionException e){
            bindingResult.reject("encryption", e.getMessage());
            return "user/form/login";
        } catch(WrongLoginDataException e){
            bindingResult.reject("wrong.loginForm", e.getMessage());
            return "user/form/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SessionControl.invalidate(request);
        return "redirect:/";
    }
}
