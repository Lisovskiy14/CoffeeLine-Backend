package com.example.CoffeeLine.util;

import com.example.CoffeeLine.web.exception.ParamsValidationDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.util.List;

public class RequestValidationUtil {

    public static ProblemDetail getProblemDetailByValidationDetails(List<ParamsValidationDetails> validationDetails) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Request validation failed");
        problemDetail.setType(URI.create("urn:problem-type:validation-error"));
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("validationDetails", validationDetails);
        return problemDetail;
    }
}
