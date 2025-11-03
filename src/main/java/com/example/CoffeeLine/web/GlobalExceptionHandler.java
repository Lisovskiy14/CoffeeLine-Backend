package com.example.CoffeeLine.web;

import com.example.CoffeeLine.service.exception.EmailAlreadyExistsException;
import com.example.CoffeeLine.service.exception.ResourceNotFoundException;
import com.example.CoffeeLine.util.RequestValidationUtil;
import com.example.CoffeeLine.web.exception.ParamsValidationDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        log.info("Validation Error has occurred");
        List<ParamsValidationDetails> validationDetails = ex.getFieldErrors().stream()
                .map(fieldError -> ParamsValidationDetails.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build()
                )
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RequestValidationUtil.getProblemDetailByValidationDetails(validationDetails));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handeResourceNotFound(ResourceNotFoundException ex) {
        log.info("Resource Not Found exception occurred");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:resource-not-found"));
        problemDetail.setTitle("Resource Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handeEmailAlreadyExists(EmailAlreadyExistsException ex) {
        log.info("Email Already Exists exception occurred");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:conflict-error"));
        problemDetail.setTitle("Email Already Exists");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwt(ExpiredJwtException ex) {
        log.info("Expired JWT exception occurred");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:expired-jwt"));
        problemDetail.setTitle("Expired JWT Exception");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignature(SignatureException ex) {
        log.info("JWT Signature exception occurred");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:jwt-signature-exception"));
        problemDetail.setTitle("JWT Signature Exception");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        log.info("Bad Credentials exception occurred");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:bad-credentials"));
        problemDetail.setTitle("Bad Credentials Exception");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleInternalServerError(RuntimeException ex) {
        log.info("Internal Server Error has occurred");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:internal-server-error"));
        problemDetail.setTitle("Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }
}