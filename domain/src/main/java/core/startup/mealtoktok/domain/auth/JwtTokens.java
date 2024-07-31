package core.startup.mealtoktok.domain.auth;

public record JwtTokens(String accessToken, String refreshToken) {

    public static JwtTokens of(String accessToken, String refreshToken) {
        return new JwtTokens(accessToken, refreshToken);
    }
}
