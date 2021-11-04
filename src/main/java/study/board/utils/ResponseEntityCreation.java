package study.board.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ResponseEntityCreation {

    public static final ResponseEntity SUCCESS_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.OK);

    public static ResponseEntity<List> getResponseEntity(BindingResult bindingResult){

        List<String> list = new ArrayList<>();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> getSingleStringResponseEntity(String errMessage, HttpStatus status){
        return new ResponseEntity<>(errMessage, status);
    }

}
