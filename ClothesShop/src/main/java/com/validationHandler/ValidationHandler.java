package com.validationHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.firebase.remoteconfig.RemoteConfigErrorCode.VALIDATION_ERROR;

@RestControllerAdvice//tất cả các exception sẽ được gom về đây
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override //lỗi do mình custom
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handlePathVariableError(final ConstraintViolationException exception) {
        String []s = exception.getMessage().split(",");
        Map<String, String> errors = new HashMap<>();
        for( String error : s){
            String filed = error.substring(error.indexOf(".") + 1,error.indexOf(":")).trim();
            String message = error.substring(error.indexOf(":") + 1).trim();
            errors.put(filed,message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(Exception.class)//lỗi hệ thống thì ném ra đây và không nên trả về message lỗi thực sự của nó
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body("Unknown error");//nên trả về unknown
    }

}
