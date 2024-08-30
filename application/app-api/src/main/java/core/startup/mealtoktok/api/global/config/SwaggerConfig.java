package core.startup.mealtoktok.api.global.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import core.startup.mealtoktok.api.global.security.SecurityProperties;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi dishStore() {
        return GroupedOpenApi.builder()
                .group("반찬가게")
                .packagesToScan("core.startup.mealtoktok.api.dishstore")
                .build();
    }

    @Bean
    public GroupedOpenApi auth() {
        return GroupedOpenApi.builder()
                .group("인증")
                .packagesToScan("core.startup.mealtoktok.api.auth")
                .build();
    }

    @Bean
    public GroupedOpenApi user() {
        return GroupedOpenApi.builder()
                .group("회원")
                .packagesToScan("core.startup.mealtoktok.api.user")
                .build();
    }

    @Bean
    public GroupedOpenApi fullDining() {
        return GroupedOpenApi.builder()
                .group("풀대접")
                .packagesToScan("core.startup.mealtoktok.api.fulldining")
                .build();
    }

    @Bean
    public GroupedOpenApi order() {
        return GroupedOpenApi.builder()
                .group("주문")
                .packagesToScan("core.startup.mealtoktok.api.order")
                .build();
    }

    @Bean
    public GroupedOpenApi mealDelivery() {
        return GroupedOpenApi.builder()
                .group("도시락 배송")
                .packagesToScan("core.startup.mealtoktok.api.mealdelivery")
                .build();
    }

    @Bean
    public GroupedOpenApi payment() {
        return GroupedOpenApi.builder()
                .group("결제")
                .packagesToScan("core.startup.mealtoktok.api.payment")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {

        Info info =
                new Info()
                        .version("1.0.0")
                        .title("MealTokTok API")
                        .description("Cercat API 문서입니다.");

        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement =
                new SecurityRequirement()
                        .addList(SecurityProperties.ACCESS_TOKEN_HEADER)
                        .addList(SecurityProperties.REFRESH_TOKEN_HEADER);
        // SecuritySchemes 등록
        Components components =
                new Components()
                        .addSecuritySchemes(
                                SecurityProperties.ACCESS_TOKEN_HEADER,
                                new SecurityScheme()
                                        .name(SecurityProperties.ACCESS_TOKEN_HEADER)
                                        .type(SecurityScheme.Type.APIKEY) // HTTP 방식
                                        .scheme("bearer")
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("JWT"))
                        .addSecuritySchemes(
                                SecurityProperties.REFRESH_TOKEN_HEADER,
                                new SecurityScheme()
                                        .name(SecurityProperties.REFRESH_TOKEN_HEADER)
                                        .type(SecurityScheme.Type.APIKEY) // HTTP 방식
                                        .scheme("bearer")
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("JWT"));

        return new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);
    }
}
