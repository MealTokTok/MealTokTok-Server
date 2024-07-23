package core.startup.mealtoktok.global.web;

import core.startup.mealtoktok.global.security.JwtTokenProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("1.0.0")
                .title("MealTokTok API")
                .description("Cercat API 문서입니다.");

        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(JwtTokenProperties.ACCESS_TOKEN_HEADER)
                .addList(JwtTokenProperties.REFRESH_TOKEN_HEADER);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(JwtTokenProperties.ACCESS_TOKEN_HEADER, new SecurityScheme()
                        .name(JwtTokenProperties.ACCESS_TOKEN_HEADER)
                        .type(SecurityScheme.Type.APIKEY) // HTTP 방식
                        .scheme("bearer")
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT"))
                .addSecuritySchemes(JwtTokenProperties.REFRESH_TOKEN_HEADER, new SecurityScheme()
                        .name(JwtTokenProperties.REFRESH_TOKEN_HEADER)
                        .type(SecurityScheme.Type.APIKEY) // HTTP 방식
                        .scheme("bearer")
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
