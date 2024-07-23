package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.TargetUser;

public interface TokenGenerator {
    JwtTokens generate(TargetUser targetUser);
}
