package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.UserId;

public record RefreshToken(Long userId, String refreshToken) {

    public static RefreshToken of(UserId userId, String refreshToken) {
        return new RefreshToken(userId.getValue(), refreshToken);
    }
}
