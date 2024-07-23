package core.startup.mealtoktok.auth;

import core.startup.mealtoktok.api.user.request.SignupRequest;
import core.startup.mealtoktok.auth.response.OAuthLogin;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi implements AuthApiDocs {

    private final AuthService authService;

    @GetMapping("/oauth/sign-up/validation")
    public Response<Boolean> canRegistered(@RequestParam String oidcToken) {
        return Response.success(authService.canRegistered(oidcToken));
    }

    @PostMapping("/oauth/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest.toOAuthToken(), signupRequest.toInfo());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/oauth/login/link")
    public Response<OAuthLogin> oauthLoginLink() {
        OAuthLogin oAuthLogin = OAuthLogin.from(authService.getKakaoLoginLink());
        return Response.success(oAuthLogin);
    }

    @GetMapping("/login/oauth2/code/kakao")
    public ResponseEntity<Void> credentialTest(@RequestParam String code) {
        authService.getCredentialTest(code);
        return ResponseEntity.ok().build();
    }
}
