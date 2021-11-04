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
import study.board.dto.UserEditForm;
import study.board.error.ErrorResponse;
import study.board.error.FieldErrorResponse;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.WrongLoginDataException;
import study.board.service.UserService;
import study.board.utils.CheckPasswordPattern;
import study.board.utils.ResponseEntityCreation;
import study.board.utils.SessionConst;
import study.board.utils.SessionControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AjaxController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<FieldErrorResponse> login(@Validated @RequestBody LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult); //메서드 분리 -> utils.ResponseEntityCreation.class
        }

        User loginUser = userService.login(loginForm);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @PostMapping("/signup")
    public ResponseEntity<FieldErrorResponse> signup(@Validated @RequestBody User user, BindingResult bindingResult){
        if(!CheckPasswordPattern.isValidPassword(user.getPassword())){
            bindingResult.rejectValue("password", "invalid.password", "비밀번호는 8자 이상, 대소문자, 특수기호, 숫자를 포함해야합니다.");
        }

        if(bindingResult.hasErrors()){
            return  ResponseEntityCreation.getResponseEntity(bindingResult);
        }

        userService.signup(user);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @PostMapping("/userInfo/edit")
    public ResponseEntity<FieldErrorResponse> editUserInfo(@Validated @RequestBody UserEditForm userEditForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER) User user, HttpServletRequest request){
        if(!CheckPasswordPattern.isValidPassword(userEditForm.getPassword())){
            bindingResult.rejectValue("password", "invalid.password", "비밀번호는 8자 이상, 대소문자, 특수기호, 숫자를 포함해야합니다.");
        }

        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult);
        }

        userService.updateUserInfo(userEditForm, user);
        user = userService.findUserById(user.getId());
        request.getSession().setAttribute(SessionConst.LOGIN_USER, user);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @GetMapping("/user/withdrawal")
    public ResponseEntity<ErrorResponse> withdrawal(@SessionAttribute(name = SessionConst.LOGIN_USER) User user, HttpServletRequest request){
        SessionControl.invalidate(request);
        userService.deleteUser(user);
        return null;
    }

    @ExceptionHandler(WrongLoginDataException.class)
    public ResponseEntity<ErrorResponse> handleWrongLoginDataException(){
        return ResponseEntityCreation.getSingleStringResponseEntity("아이디 혹은 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EncryptionException.class)
    public ResponseEntity<ErrorResponse> handleEncryptionException(){
        return ResponseEntityCreation.getSingleStringResponseEntity("암호화 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}