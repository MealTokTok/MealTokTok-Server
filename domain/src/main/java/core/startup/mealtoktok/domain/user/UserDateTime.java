package core.startup.mealtoktok.domain.user;

import java.time.LocalDateTime;

public record UserDateTime(
        LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime removedAt) {

    public static UserDateTime of(
            LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime removedAt) {
        return new UserDateTime(createdAt, modifiedAt, removedAt);
    }
}
