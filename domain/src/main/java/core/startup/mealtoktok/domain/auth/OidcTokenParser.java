package core.startup.mealtoktok.domain.auth;

public interface OidcTokenParser {

    String getKid(String token, String iss, String aud);

    OIDCPayload getPayload(String token, String modulus, String exponent);
}
