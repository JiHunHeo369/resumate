package com.resumate.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("My API")
                .version("v1.0")
                .description("Spring Boot Swagger 설정 예제");
        return new OpenAPI().info(info);
    }

}
