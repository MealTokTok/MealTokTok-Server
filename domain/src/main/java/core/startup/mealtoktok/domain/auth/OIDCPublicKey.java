package core.startup.mealtoktok.domain.auth;

public record OIDCPublicKey(
        String kid,
        String alg,
        String use,
        String n,
        String e
) {
}
