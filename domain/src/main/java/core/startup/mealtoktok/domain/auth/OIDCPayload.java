package core.startup.mealtoktok.domain.auth;

public record OIDCPayload(String iss, String aud, String sub, String email) {}
