package core.startup.mealtoktok.global.security;

import core.startup.mealtoktok.domain.user.TargetUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static core.startup.mealtoktok.global.security.JwtTokenConfig.*;

@Component
public class JwtTokenizer {

    public static String generateAccessToken(TargetUser targetUser) {
        Key key = getKeyFromSecretKey(SECRET_KEY);

        return Jwts.builder()
                .setSubject(String.valueOf(targetUser.userId()))
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(getTokenExpiration())
                .signWith(key)
                .compact();
    }

    public static String generateRefreshToken(TargetUser targetUser) {
        Key key = getKeyFromSecretKey(SECRET_KEY);

        return Jwts.builder()
                .setSubject(String.valueOf(targetUser.userId()))
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith(key)
                .compact();
    }

    public static void setInHeader(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setHeader(ACCESS_TOKEN_HEADER, BEARER + accessToken);
        response.setHeader(REFRESH_TOKEN_HEADER, BEARER + refreshToken);
    }

    public static Key getKeyFromSecretKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Date getTokenExpiration() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRATION);
        return calendar.getTime();
    }

    public static Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_TOKEN_HEADER))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    public static Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(REFRESH_TOKEN_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public static TargetUser extractTargetUser(String token) {
        //TODO: Handle exception
        return TargetUser.from(Long.parseLong(
                Jwts.parserBuilder()
                .setSigningKey(getKeyFromSecretKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject())
        );
    }


}
