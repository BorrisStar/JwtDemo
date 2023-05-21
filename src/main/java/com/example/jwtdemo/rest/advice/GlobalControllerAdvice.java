package com.example.jwtdemo.rest.advice;

import com.example.jwtdemo.exception.UserNotFoundException;
import com.example.jwtdemo.rest.advice.problem.CommonProblem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class GlobalControllerAdvice implements ProblemHandling {
//     throw new JwtAuthenticationException("JWT token is expired or invalid"); - not working because of throws in filter
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Problem> handleAuthenticationException(final UserNotFoundException exception, final NativeWebRequest request) {
        CommonProblem problem = CommonProblem.CommonProblemBuilder.prepareVrpProblemWithDetail(exception, request, Status.NOT_FOUND, Problem.DEFAULT_TYPE).build();
        return this.create(exception, problem, request);
    }
}
