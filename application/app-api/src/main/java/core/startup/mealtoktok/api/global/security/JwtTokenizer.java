package core.startup.mealtoktok.api.global.security;

import static core.startup.mealtoktok.api.global.security.SecurityProperties.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import core.startup.mealtoktok.api.auth.exception.ExpiredTokenException;
import core.startup.mealtoktok.api.auth.exception.InvalidTokenException;
import core.startup.mealtoktok.domain.auth.JwtTokens;
import core.startup.mealtoktok.domain.auth.TokenGenerator;
import core.startup.mealtoktok.domain.user.UserId;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenizer implements TokenGenerator {

    public static String generateAccessToken(UserId userId) {
        Key key = getKeyFromSecretKey(SECRET_KEY);

        return Jwts.builder()
                .setSubject(String.valueOf(userId.getValue()))
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(getTokenExpiration())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String generateRefreshToken(UserId userId) {
        Key key = getKeyFromSecretKey(SECRET_KEY);

        return Jwts.builder()
                .setSubject(String.valueOf(userId.getValue()))
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static void setInHeader(
            HttpServletResponse response, String accessToken, String refreshToken) {
        response.setHeader(ACCESS_TOKEN_HEADER, accessToken);
        response.setHeader(REFRESH_TOKEN_HEADER, refreshToken);
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

    public static UserId extractTargetUser(String token) {
        try {
            return UserId.from(
                    Long.parseLong(
                            Jwts.parserBuilder()
                                    .setSigningKey(getKeyFromSecretKey(SECRET_KEY))
                                    .build()
                                    .parseClaimsJws(token)
                                    .getBody()
                                    .getSubject()));
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
    ;

    @Override
    public JwtTokens generate(UserId userId) {
        return JwtTokens.of(generateAccessToken(userId), generateRefreshToken(userId));
    }
}
