package com.jnlim.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("API Test") // API 제목
                .description("Tennis-reservation API Swagger UI") // API 설명
                .version("1.0.0"); // API 버전
    }
}
