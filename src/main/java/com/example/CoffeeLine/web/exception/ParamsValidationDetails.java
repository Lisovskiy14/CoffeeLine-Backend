package com.example.CoffeeLine.web.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParamsValidationDetails {
    String field;
    String message;
}
