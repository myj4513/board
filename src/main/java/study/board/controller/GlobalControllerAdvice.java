package study.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.board.dto.User;
import study.board.service.UserService;
import study.board.utils.SessionConst;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final UserService userService;

    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false)Integer userId){
        if(userId==null) return false;
        if(userService.findUserById(userId)==null) return false;
        return true;
    }

    @ModelAttribute("curUserId")
    public int getUserId(@SessionAttribute(name=SessionConst.LOGIN_USER, required=false) Integer userId){
        if(userId==null) return -1;
        return userId;
    }

    @ModelAttribute("curUserName")
    public String getUserName(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Integer userId){
        if(userId==null) return null;
        return userService.findUserById(userId).getName();
    }
}
