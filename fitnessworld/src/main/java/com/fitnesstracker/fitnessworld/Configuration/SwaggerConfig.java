package com.fitnesstracker.fitnessworld.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API")
                        .description("API documentation for My Application")
                        .version("1.0.0"));
    }
}


//Public Url 
//http://localhost:8080/swagger-ui/index.html
