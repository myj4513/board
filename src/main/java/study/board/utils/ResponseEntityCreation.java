package study.board.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import study.board.error.ErrorResponse;
import study.board.error.FieldErrorResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseEntityCreation {

    public static final ResponseEntity SUCCESS_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.OK);

    private static Map getFieldErrMap(BindingResult bindingResult){
        Map<String, String> map = new HashMap<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()){
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return map;
    }

    private static List getFieldErrList(BindingResult bindingResult){
        List<String> list = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()){
            list.add(fieldError.getDefaultMessage());
        }
        return list;
    }

    public static ResponseEntity<FieldErrorResponse> getResponseEntity(BindingResult bindingResult){
        return new ResponseEntity<>(new FieldErrorResponse(HttpStatus.BAD_REQUEST, getFieldErrMap(bindingResult)), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ErrorResponse> getSingleStringResponseEntity(String errMessage, HttpStatus status){
        return new ResponseEntity<>(new ErrorResponse(status, errMessage), status);
    }

}
