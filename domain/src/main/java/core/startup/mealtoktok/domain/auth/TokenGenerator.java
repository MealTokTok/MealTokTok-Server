package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.UserId;

public interface TokenGenerator {

    JwtTokens generate(UserId userId);
}
