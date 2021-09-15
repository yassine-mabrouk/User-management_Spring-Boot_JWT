package com.enset.fbc.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // pour personaliser l'exception
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    /*cette annotation pour que spring boot prendre en concederation l'exepetion personalisé
    un fois il trouve une exception  dans value
    *
    * */
    public ResponseEntity<Object> userExeptionHandler(UserException ex, WebRequest webRequest){
        // Pour personaliser l'objet de retour
        ErrorResponse errorResponse=new ErrorResponse(new Date(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // exception globel
    @ExceptionHandler(value =Exception.class)
    public ResponseEntity<Object> userExeptionHandler(Exception ex, WebRequest webRequest){
        // Pour personaliser l'objet de retour
        ErrorResponse errorResponse=new ErrorResponse(new Date(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Personnaliser exception de validation
    @ExceptionHandler(value =MethodArgumentNotValidException.class)
    public ResponseEntity<Object> MethodArgumentNotValidExceptionHundler(MethodArgumentNotValidException ex, WebRequest webRequest){
        // Pour personaliser l'objet de retour
        ErrorResponse errorResponse=new ErrorResponse(new Date(),ex.getMessage());
        Map<String,String> errors =new HashMap<>();

         ex.getBindingResult().getFieldErrors().forEach(error-> {
             errors.put(error.getField(),error.getDefaultMessage());
         });
        return new ResponseEntity<>(errors,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
