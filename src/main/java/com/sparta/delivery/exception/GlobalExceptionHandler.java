package com.sparta.delivery.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<String> notValid(MethodArgumentNotValidException e) {
//        String msg = e.getBindingResult()
//                .getAllErrors()
//                .get(0)
//                .getDefaultMessage();
//        return ResponseEntity.badRequest().body(msg);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> illegalArgument(IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
}
