package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.WrongLoginDataException;
import study.board.service.UserService;
import study.board.utils.ResponseEntityCreation;
import study.board.utils.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AjaxController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<List> login(@Validated @RequestBody LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult); //메서드 분리 -> utils.ResponseEntityCreation.class
        }

        User loginUser = userService.login(loginForm);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @PostMapping("/signup")
    public ResponseEntity<List> signup(@Validated @RequestBody User user, BindingResult bindingResult){
        if(!checkPassword(user.getPassword())){
            bindingResult.rejectValue("password", "invalid.password", "비밀번호는 ~~");
        }

        if(bindingResult.hasErrors()){
            return  ResponseEntityCreation.getResponseEntity(bindingResult);
        }

        userService.signup(user);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @ExceptionHandler(WrongLoginDataException.class)
    public ResponseEntity<String> handleWrongLoginDataException(){
        return ResponseEntityCreation.getSingleStringResponseEntity("아이디 혹은 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EncryptionException.class)
    public ResponseEntity<String> handleEncryptionException(){
        return ResponseEntityCreation.getSingleStringResponseEntity("암호화 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean checkPassword(String password){
        if(!Pattern.matches("[a-zA-Z0-9~!@#$%^&*()]{8,20}",password)) return false;
        return true;
    }
}