package core.startup.mealtoktok.api.global.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import core.startup.mealtoktok.api.auth.exception.InvalidTokenException;
import core.startup.mealtoktok.domain.auth.RefreshToken;
import core.startup.mealtoktok.domain.auth.TokenManager;
import core.startup.mealtoktok.domain.user.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserReader userReader;
    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            Optional<String> refreshToken = JwtTokenizer.extractRefreshToken(request);

            if (refreshToken.isPresent()) {
                UserId userId = JwtTokenizer.extractTargetUser(refreshToken.get());
                validateRefreshToken(userId, refreshToken.get());
                reIssueToken(userId, response);
                log.info("userEntity - {} 리프레시 토큰 재발급", userId.getValue());
                return;
            }

            JwtTokenizer.extractAccessToken(request)
                    .filter(tokenManager::isAlreadyLogin)
                    .map(JwtTokenizer::extractTargetUser)
                    .map(userReader::read)
                    .ifPresent(this::saveAuthentication);

        } catch (Exception e) {
            log.error(e.toString());
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    public void validateRefreshToken(UserId userId, String givenRefreshToken) {
        tokenManager
                .getRefreshToken(userId)
                .filter(existRefreshToken -> existRefreshToken.equals(givenRefreshToken))
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }

    private void reIssueToken(UserId userId, HttpServletResponse response) {
        String reIssuedAccessToken = JwtTokenizer.generateAccessToken(userId);
        String reIssuedRefreshToken = JwtTokenizer.generateRefreshToken(userId);
        tokenManager.saveRefreshToken(RefreshToken.of(userId, reIssuedRefreshToken));
        JwtTokenizer.setInHeader(response, reIssuedAccessToken, reIssuedRefreshToken);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    private void saveAuthentication(User user) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, getAuthorities(user));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("{} 유저 인증 성공", user.getUserProfile().getNickname());
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name()));
        return authorities;
    }
}
