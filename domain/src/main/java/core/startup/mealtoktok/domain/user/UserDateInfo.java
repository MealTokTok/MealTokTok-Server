package core.startup.mealtoktok.domain.user;

import java.time.LocalDateTime;

public record UserDateInfo(
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UserDateInfo of(LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new UserDateInfo(createdAt, modifiedAt);
    }
}
