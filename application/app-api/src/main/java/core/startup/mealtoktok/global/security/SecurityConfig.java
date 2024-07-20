package core.startup.mealtoktok.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final HttpCookieAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;

    private static final String[] SWAGGER_URIS = {
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


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/","/health", "/api/v2/alarms/subscribe", "/error", "/error/**", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**").permitAll()
                                .requestMatchers(SWAGGER_URIS).permitAll()
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .oauth2Login(
                        oauth2 -> oauth2
                                .loginPage("/oauth2/authorization/kakao")
                                .authorizationEndpoint(
                                        authorizationEndpointConfig -> authorizationEndpointConfig
                                                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
                                )
                                .successHandler(oAuth2MemberSuccessHandler)
                                .failureHandler(oAuth2LoginFailureHandler)
                                .userInfoEndpoint(
                                        userInfoEndpointConfig -> userInfoEndpointConfig
                                                .userService(customOAuth2UserService)
                                )

                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtExceptionHandleFilter(), JwtTokenFilter.class);
        return http.build();
    }

}
