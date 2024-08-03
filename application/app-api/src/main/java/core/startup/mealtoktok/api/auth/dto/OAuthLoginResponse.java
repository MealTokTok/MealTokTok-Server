package core.startup.mealtoktok.api.auth.dto;

public record OAuthLoginResponse(String link) {
    public static OAuthLoginResponse from(String link) {
        return new OAuthLoginResponse(link);
    }
}
