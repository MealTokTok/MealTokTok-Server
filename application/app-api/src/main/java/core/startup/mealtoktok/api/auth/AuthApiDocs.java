package core.startup.mealtoktok.api.auth;

import jakarta.servlet.http.HttpServletResponse;

import core.startup.mealtoktok.api.auth.dto.OAuthLoginResponse;
import core.startup.mealtoktok.api.auth.dto.SignUpRequest;
import core.startup.mealtoktok.api.global.dto.Response;
import core.startup.mealtoktok.api.user.dto.AvailabilityResponse;
import core.startup.mealtoktok.domain.auth.OAuthTokens;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "인증 API")
public interface AuthApiDocs {

    @Operation(summary = "id_token을 통해 회원가입 여부 확인")
    Response<AvailabilityResponse> canRegistered(String oidcToken);

    @Operation(summary = "회원가입")
    Response<Void> signUp(SignUpRequest signupRequest, HttpServletResponse response);

    @Operation(summary = "로그인")
    Response<Void> login(OAuthTokens oAuthTokens, String deviceToken, HttpServletResponse response);

    @Operation(summary = "OAuth 링크발급(백엔드 개발 로컬 테스트용입니다)")
    Response<OAuthLoginResponse> oauthLoginLink();

    @Operation(summary = "OAuth code를 통한 테스트(백엔드 개발 로컬 테스트용입니다)")
    Response<Void> credentialTest(String code, HttpServletResponse response);
}
