package core.startup.mealtoktok.domain.auth;

import static core.startup.mealtoktok.common.properties.OauthProperties.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.user.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserReader userReader;
    private final OAuthClient oAuthClient;
    private final OAuthAuthenticator oAuthAuthenticator;
    private final TokenGenerator tokenGenerator;
    private final UserAppender userAppender;
    private final UserUpdater userUpdater;

    public boolean canRegistered(String idToken) {
        OAuthInfo oAuthInfo = oAuthAuthenticator.authenticate(idToken);

        return !userReader.isAlreadyRegistered(oAuthInfo);
    }

    public JwtTokens signUp(
            OAuthTokens oAuthTokens,
            String deviceToken,
            AddressWithCoordinate addressWithCoordinate) {
        OAuthInfo oAuthInfo = oAuthAuthenticator.authenticate(oAuthTokens.idToken());
        UserProfile userProfile = oAuthClient.getUserProfile(oAuthTokens.accessToken());
        TargetUser targetUser =
                userAppender.append(
                        oAuthInfo,
                        deviceToken,
                        userProfile,
                        DeliveryAddress.configure(addressWithCoordinate));
        return tokenGenerator.generate(targetUser);
    }

    public JwtTokens login(OAuthTokens oAuthTokens, String deviceToken) {
        OAuthInfo oAuthInfo = oAuthAuthenticator.authenticate(oAuthTokens.idToken());
        UserProfile userProfile = oAuthClient.getUserProfile(oAuthTokens.accessToken());
        User user = userReader.read(oAuthInfo.oid());
        TargetUser targetUser = userUpdater.update(user, userProfile, deviceToken);
        return tokenGenerator.generate(targetUser);
    }

    public String getKakaoOAuthUrl() {
        return OAuthClient.getKakaoOAuthUrl();
    }

    public JwtTokens getCredentialTest(String code) {
        OAuthTokens authToken = oAuthClient.auth(CLIENT_ID, REDIRECT_URL, code);
        return signUp(
                authToken,
                "deviceTokenTest",
                AddressWithCoordinate.of("충청북도 흥덕구 봉명동 2300-1", 36.629, 127.456));
    }
}
