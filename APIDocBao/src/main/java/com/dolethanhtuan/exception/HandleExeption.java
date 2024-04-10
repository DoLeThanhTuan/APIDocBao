package com.dolethanhtuan.exception;

import com.dolethanhtuan.response.ErrorResponse;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
@RestControllerAdvice
public class HandleExeption {
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExeption(Exception e, WebRequest request){
        System.out.println("==========> Handle Validation Exception");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        String message = e.getMessage();
        if(e instanceof MethodArgumentNotValidException){
            int start = message.lastIndexOf("[");
            int end = message.lastIndexOf("]");
            message = message.substring(start+1,end-1);
        }
        if(e instanceof ConstraintViolationException){
            int start = message.indexOf(" ");
            message = message.substring(start+1);
        }



        errorResponse.setMessage(message);
        return errorResponse;
    }

}
