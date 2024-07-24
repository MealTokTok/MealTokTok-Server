package core.startup.mealtoktok.api.global.security;

import core.startup.mealtoktok.domain.auth.RefreshToken;
import core.startup.mealtoktok.domain.auth.TokenManager;
import core.startup.mealtoktok.domain.user.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserReader userReader;
    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> refreshToken = JwtTokenizer.extractRefreshToken(request);

        if (refreshToken.isPresent()) {
            TargetUser targetUser = JwtTokenizer.extractTargetUser(refreshToken.get());
            tokenManager.validate(targetUser, refreshToken.get());
            reIssueToken(targetUser, response);
            log.info("userEntity - {} 리프레시 토큰 재발급", targetUser.userId());
            return;
        }

        JwtTokenizer.extractAccessToken(request)
                .filter(tokenManager::isAlreadyLogin)
                .map(JwtTokenizer::extractTargetUser)
                .map(userReader::read)
                .ifPresent(this::saveAuthentication);

        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(User user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("{} 유저 인증 성공", user.getUserInfo().profileImageUrl());
    }

    private void reIssueToken(TargetUser targetUser, HttpServletResponse response) {
        String reIssuedAccessToken = JwtTokenizer.generateAccessToken(targetUser);
        String reIssuedRefreshToken = JwtTokenizer.generateRefreshToken(targetUser);
        tokenManager.saveRefreshToken(RefreshToken.of(targetUser, reIssuedRefreshToken));
        JwtTokenizer.setInHeader(response, reIssuedAccessToken, reIssuedRefreshToken);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
