package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.auth.exception.NoMatchedKidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static core.startup.mealtoktok.common.properties.OauthProperties.*;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticator {

    private final OAuthClient oauthClient;
    private final OidcTokenParser oidcTokenParser;

    public OAuthInfo authenticate(String idToken) {
        String kid = oidcTokenParser.getKid(idToken, BASE_URL, SERVICE_KEY);
        OIDCPublicKey matchedPublicKey = getMatchedPublicKey(kid);
        OIDCPayload payload = oidcTokenParser.getPayload(idToken, matchedPublicKey.n(), matchedPublicKey.e());
        return OAuthInfo.kakao(payload);
    }

    private OIDCPublicKey getMatchedPublicKey(String kid) {
        return oauthClient.getPublicKeys().stream()
                .filter(oidcPublicKey -> oidcPublicKey.kid().equals(kid))
                .findFirst()
                .orElseThrow(() -> NoMatchedKidException.EXCEPTION);
    }

}
