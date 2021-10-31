package study.board.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.board.dto.User;
import study.board.utils.SessionConst;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false)User user){
        if(user==null) return false;
        return true;
    }

    @ModelAttribute("currentUser")
    public User user(@SessionAttribute(name=SessionConst.LOGIN_USER, required=false) User user){
        return user;
    }
}
