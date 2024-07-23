package core.startup.mealtoktok.global.util;

import core.startup.mealtoktok.domain.auth.JwtTokens;
import org.springframework.http.HttpHeaders;

import static core.startup.mealtoktok.global.security.SecurityProperties.*;

public class HttpUtils {


    public static HttpHeaders setHeaders(JwtTokens jwtTokens) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ACCESS_TOKEN_HEADER, jwtTokens.accessToken());
        httpHeaders.add(REFRESH_TOKEN_HEADER, jwtTokens.refreshToken());
        return httpHeaders;
    }
}
