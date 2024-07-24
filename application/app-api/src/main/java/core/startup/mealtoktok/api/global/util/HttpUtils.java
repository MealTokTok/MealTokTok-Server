package core.startup.mealtoktok.api.global.util;

import core.startup.mealtoktok.api.global.security.SecurityProperties;
import core.startup.mealtoktok.domain.auth.JwtTokens;
import org.springframework.http.HttpHeaders;

public class HttpUtils {


    public static HttpHeaders setHeaders(JwtTokens jwtTokens) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityProperties.ACCESS_TOKEN_HEADER, jwtTokens.accessToken());
        httpHeaders.add(SecurityProperties.REFRESH_TOKEN_HEADER, jwtTokens.refreshToken());
        return httpHeaders;
    }
}
