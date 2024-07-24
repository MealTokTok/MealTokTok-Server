package core.startup.mealtoktok.api.global.web;

import core.startup.mealtoktok.api.global.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("DELETE", "GET", "POST", "PATCH", "PUT")
                .allowedHeaders("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization", SecurityProperties.ACCESS_TOKEN_HEADER, SecurityProperties.REFRESH_TOKEN_HEADER)
                .exposedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
