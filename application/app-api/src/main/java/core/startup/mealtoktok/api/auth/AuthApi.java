package core.startup.mealtoktok.api.auth;

import core.startup.mealtoktok.api.auth.request.SignupRequest;
import core.startup.mealtoktok.api.auth.response.OAuthLogin;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.auth.AuthService;
import core.startup.mealtoktok.domain.auth.JwtTokens;
import core.startup.mealtoktok.domain.auth.OAuthTokens;
import core.startup.mealtoktok.global.security.JwtTokenizer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi implements AuthApiDocs {

    private final AuthService authService;

    @GetMapping("/oauth/can-sign-up")
    public Response<Boolean> canRegistered(@RequestParam String idToken) {
        return Response.success(authService.canRegistered(idToken));
    }

    @GetMapping("/oauth/login")
    public Response<Void> login(@RequestParam OAuthTokens oAuthTokens, HttpServletResponse response) {
        JwtTokens jwtTokens = authService.login(oAuthTokens);
        JwtTokenizer.setInHeader(response, jwtTokens.accessToken(), jwtTokens.refreshToken());
        return Response.success("로그인 성공");
    }

    @PostMapping("/oauth/sign-up")
    public Response<Void> signUp(@RequestBody SignupRequest signupRequest, HttpServletResponse response) {
        JwtTokens jwtTokens = authService.signUp(signupRequest.oAuthTokens(), signupRequest.toInfo());
        JwtTokenizer.setInHeader(response, jwtTokens.accessToken(), jwtTokens.refreshToken());
        return Response.success("회원가입 성공");
    }

    @GetMapping("/oauth/login/link")
    public Response<OAuthLogin> oauthLoginLink() {
        OAuthLogin oAuthLogin = OAuthLogin.from(authService.getKakaoLoginLink());
        return Response.success(oAuthLogin);
    }

    @GetMapping("/login/oauth2/code/kakao")
    public Response<Void> credentialTest(@RequestParam String code) {
        authService.getCredentialTest(code);
        return Response.success("테스트 성공");
    }
}
