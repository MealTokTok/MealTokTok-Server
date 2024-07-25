package core.startup.mealtoktok.api.global.security;

public class SecurityConstant {
    public static final String[] PERMIT_SWAGGER_URIS = {
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    public static final String[] PERMIT_SERVICE_URIS = {
            "/",
            "/health",
            "/api/v1/auth/oauth/can-sign-up",
            "/api/v1/auth/oauth/sign-up",
            "/api/v1/auth/oauth/login",
            "/api/v1/auth/login/oauth2/code/kakao"
    };

    public static final String[] PERMIT_SYSTEM_URIS = {
            "/error",
            "/error/**",
            "/css/**",
            "/images/**",
            "/js/**",
            "/favicon.ico",
            "/h2-console/**"
    };
}
