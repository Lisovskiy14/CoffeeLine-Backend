package com.example.CoffeeLine.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class JwtAuthResponse {
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
            "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0." +
            "KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30")
    String token;
}
