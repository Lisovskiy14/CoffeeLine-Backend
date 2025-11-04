package com.example.CoffeeLine.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OperationCustomizer removeSecurityResponsesFromPublicEndpoints() {
        return (operation, handlerMethod) -> {
            String controllerName = handlerMethod.getBeanType().getSimpleName();
            if (controllerName.equals("AuthenticationController")) {
                if (operation.getResponses() != null) {
                    operation.getResponses().remove("403");
                }
            }
            return operation;
        };
    }
}
