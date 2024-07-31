package core.startup.mealtoktok.domain.auth;

public record OAuthInfo(OAuthProvider provider, String oid) {

    public static OAuthInfo kakao(OIDCPayload payload) {
        return new OAuthInfo(OAuthProvider.KAKAO, payload.sub());
    }

    public static OAuthInfo of(OAuthProvider provider, String oid) {
        return new OAuthInfo(provider, oid);
    }
}
