package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.TargetUser;

public record RefreshToken(Long userId, String refreshToken) {
    public static RefreshToken of(TargetUser targetUser, String refreshToken) {
        return new RefreshToken(targetUser.userId(), refreshToken);
    }
}
