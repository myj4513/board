package study.board.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import study.board.exceptions.*;
import study.board.utils.ResponseEntityCreation;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DuplicatedLoginIdException.class)
    public ResponseEntity<String> handleDuplicatedLoginId(){
        return ResponseEntityCreation.getSingleStringResponseEntity("이미 사용중인 아이디입니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoArticleFoundException.class)
    public String handleNoArticleFoundException(){
        return "";
    }
}
