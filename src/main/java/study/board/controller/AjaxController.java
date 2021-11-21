package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dto.*;
import study.board.error.ErrorResponse;
import study.board.error.FieldErrorResponse;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.NoAuthorityException;
import study.board.exceptions.WrongLoginDataException;
import study.board.service.ArticleService;
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
    private final ArticleService articleService;

    @PostMapping("/login")
    public ResponseEntity<FieldErrorResponse> login(@Validated @RequestBody LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult); //메서드 분리 -> utils.ResponseEntityCreation.class
        }

        User loginUser = userService.login(loginForm);
        request.getSession().setAttribute(SessionConst.LOGIN_USER, loginUser.getId());

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

    @PatchMapping("/user/{userId}")
    public ResponseEntity<FieldErrorResponse> editUserInfo(@Validated @RequestBody UserEditForm userEditForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId, HttpServletRequest request){
        if(!CheckPasswordPattern.isValidPassword(userEditForm.getPassword())){
            bindingResult.rejectValue("password", "invalid.password", "비밀번호는 8자 이상, 대소문자, 특수기호, 숫자를 포함해야합니다.");
        }

        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult);
        }

        userService.updateUserInfo(userEditForm, userId);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ErrorResponse> withdrawal(@SessionAttribute(name = SessionConst.LOGIN_USER) int userId, HttpServletRequest request){
        SessionControl.invalidate(request);
        userService.deleteUser(userId);
        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<ErrorResponse> deleteArticle(@PathVariable int articleId, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){
        articleService.deleteArticle(articleId, userId);
        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @PutMapping("/articles/{articleId}")
    public ResponseEntity<FieldErrorResponse> editArticle(@PathVariable int articleId, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId, @Validated @RequestBody ArticleForm articleForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult);
        }

        articleService.editArticle(articleForm, articleId, userId);

        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    //글작성페이지 POST
    @PostMapping("/articles/new")
    public ResponseEntity<FieldErrorResponse> write(@Validated @RequestBody ArticleForm articleForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_USER) int userId){

        log.info("articleForm : {}", articleForm);
        log.info("category : {}", articleForm.getCategory());

        if(bindingResult.hasErrors()){
            return ResponseEntityCreation.getResponseEntity(bindingResult);
        }

        articleService.write(articleForm, userId);
        return ResponseEntityCreation.SUCCESS_RESPONSE_ENTITY;
    }

    @ExceptionHandler(NoAuthorityException.class)
    public ResponseEntity<ErrorResponse> handleNoAuthorityException(){
        return ResponseEntityCreation.getSingleStringResponseEntity("접근 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
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