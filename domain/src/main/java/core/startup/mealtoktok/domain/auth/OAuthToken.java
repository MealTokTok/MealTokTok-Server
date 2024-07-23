package core.startup.mealtoktok.domain.auth;

public record OAuthToken(
        String accessToken,
        String idToken
) {
    public static OAuthToken of(String accessToken, String idToken) {
        return new OAuthToken(accessToken, idToken);
    }
}
