package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.WrongLoginDataException;
import study.board.service.UserService;
import study.board.utils.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AjaxController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        Map<String, String> responseMap = new HashMap<>();
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                responseMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

        try{
            User loginUser = userService.login(loginForm);
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        } catch(EncryptionException e){
            responseMap.put("errMessage", e.getMessage());
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(WrongLoginDataException e){
            responseMap.put("errMessage", e.getMessage());
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}