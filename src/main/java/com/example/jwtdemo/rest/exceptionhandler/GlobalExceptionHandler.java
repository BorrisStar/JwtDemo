package com.example.jwtdemo.rest.exceptionhandler;

import com.example.jwtdemo.security.jwt.JwtAuthenticationException;
import com.example.jwtdemo.rest.exceptionhandler.problem.JwtProblem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@RestControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling {
    @ExceptionHandler({JwtAuthenticationException.class})
    public ResponseEntity<Problem> handleAuthenticationException(JwtAuthenticationException exception, NativeWebRequest request) {
        JwtProblem problem = JwtProblem.JwtProblemBuilder.prepareVrpProblemWithDetail(exception, request, Status.UNAUTHORIZED, Problem.DEFAULT_TYPE).build();
        return this.create(exception, problem, request);
    }
}
