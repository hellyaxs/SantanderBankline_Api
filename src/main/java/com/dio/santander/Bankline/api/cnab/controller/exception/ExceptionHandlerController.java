package com.dio.santander.Bankline.api.cnab.controller.exception;


import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(JobInstanceAlreadyCompleteException.class)
    public ResponseEntity<Object> handleJobInstanceAlreadyCompleteException(JobInstanceAlreadyCompleteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("arquivo informado ja foi importado pelo sistema!!");
    }
}
