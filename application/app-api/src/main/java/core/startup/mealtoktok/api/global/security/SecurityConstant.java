package core.startup.mealtoktok.api.global.security;

import java.util.stream.Stream;

public class SecurityConstant {

    public static final String[] SWAGGER_URIS = {
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

    public static final String[] SERVICE_URIS = {
        "/",
        "/health",
        "/api/v1/auth/oauth/can-sign-up",
        "/api/v1/auth/oauth/sign-up",
        "/api/v1/auth/oauth/login",
        "/api/v1/auth/oauth/login/link",
        "/api/v1/auth/login/oauth2/code/kakao"
    };

    public static final String[] SYSTEM_URIS = {
        "/error", "/error/**", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**"
    };

    public static final String[] ADMIN_URIS = {"/api/v1/admin/**"};

    public static final String[] DELIVERYMAN_URIS = {"/api/v1/auth/**"};

    public static final String[] STORE_OWNER_URIS = {"/api/v1/store/**"};

    public static String[] getPermittedURIs() {
        return Stream.of(SWAGGER_URIS, SERVICE_URIS, SYSTEM_URIS)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }
}
