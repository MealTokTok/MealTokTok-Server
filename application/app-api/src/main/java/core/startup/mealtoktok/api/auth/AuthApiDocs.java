package core.startup.mealtoktok.api.auth;

import core.startup.mealtoktok.api.dto.SignupRequest;
import core.startup.mealtoktok.api.dto.OAuthLoginResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.auth.OAuthTokens;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "인증 API")
public interface AuthApiDocs {

    @Operation(summary = "id_token을 통해 회원가입 여부 확인")
    Response<Boolean> canRegistered(String oidcToken);

    @Operation(summary = "회원가입")
    Response<Void> signUp(SignupRequest signupRequest, HttpServletResponse response);

    @Operation(summary = "로그인")
    Response<Void> login(OAuthTokens oAuthTokens, HttpServletResponse response);

    @Operation(summary = "OAuth 링크발급(백엔드 개발 로컬 테스트용입니다)")
    Response<OAuthLoginResponse> oauthLoginLink();

    @Operation(summary = "OAuth code를 통한 테스트(백엔드 개발 로컬 테스트용입니다)")
    Response<Void> credentialTest(String code, HttpServletResponse response);
}
