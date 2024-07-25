package core.startup.mealtoktok.api.dto;

public record OAuthLoginResponse(
        String link
) {
    public static OAuthLoginResponse from(String link) {
        return new OAuthLoginResponse(link);
    }
}
