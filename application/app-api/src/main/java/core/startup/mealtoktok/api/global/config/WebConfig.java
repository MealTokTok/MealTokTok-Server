package core.startup.mealtoktok.api.global.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import core.startup.mealtoktok.api.global.security.SecurityProperties;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("DELETE", "GET", "POST", "PATCH", "PUT")
                .allowedHeaders(
                        "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers",
                        "Origin",
                        "Cache-Control",
                        "Content-Type",
                        "Authorization",
                        SecurityProperties.ACCESS_TOKEN_HEADER,
                        SecurityProperties.REFRESH_TOKEN_HEADER)
                .exposedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CursorArgumentResolver());
    }
}
