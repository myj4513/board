package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.WrongLoginDataException;
import study.board.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AjaxController {

    private final UserService userService;

    @PostMapping("/login")
    //http 요청으로 들어오면 @ModelAttribute로 LoginForm을 받지만, ajax 요청에서 처리하는 방법을 모르겠습니다.
    //LoginForm으로 받지 못하여 validation 적용이 불가..
    //validation 로직을 새로 짜야함
    public ResponseEntity login(@RequestParam String loginId, String password){
        LoginForm loginForm = new LoginForm(loginId, password);

        try{
            userService.login(loginForm);
        } catch(EncryptionException e){
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(WrongLoginDataException e){
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
