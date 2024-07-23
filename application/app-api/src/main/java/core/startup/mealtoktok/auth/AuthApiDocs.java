package core.startup.mealtoktok.auth;

import core.startup.mealtoktok.api.user.request.SignupRequest;
import core.startup.mealtoktok.auth.response.OAuthLogin;
import core.startup.mealtoktok.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "인증 API")
public interface AuthApiDocs {

    @Operation(summary = "id_token을 통해 회원가입 여부 확인")
    Response<Boolean> canRegistered(String oidcToken);

    @Operation(summary = "회원가입")
    ResponseEntity<Void> signUp(SignupRequest signupRequest);

    @Operation(summary = "OAuth 링크발급(백엔드 개발 테스트용입니다)")
    Response<OAuthLogin> oauthLoginLink();

    @Operation(summary = "OAuth code를 통한 테스트(백엔드 개발 테스트용입니다)")
    ResponseEntity<Void> credentialTest(@RequestParam String code);
}
