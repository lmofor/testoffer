package com.poc.testoffert.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class DocumentationConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("testoffer-public")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Test Offer API")
                        .description("Test Offer sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://localhost:8089/swagger-ui")))
                .externalDocs(new ExternalDocumentation()
                        .description("Test Offer Documentation")
                        .url("https://github.com"));
    }
}
