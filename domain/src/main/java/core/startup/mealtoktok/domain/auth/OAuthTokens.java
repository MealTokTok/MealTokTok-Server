package core.startup.mealtoktok.domain.auth;

public record OAuthTokens(
        String accessToken,
        String idToken
) {
    public static OAuthTokens of(String accessToken, String idToken) {
        return new OAuthTokens(accessToken, idToken);
    }
}
