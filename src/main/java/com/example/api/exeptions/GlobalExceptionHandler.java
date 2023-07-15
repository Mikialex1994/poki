package com.example.api.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorObject> handlePokemonNotFound(PokemonNotFoundException ex, WebRequest request){

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewException(ReviewNotFoundException reviewNotFoundException, WebRequest request){

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(reviewNotFoundException.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }


}
