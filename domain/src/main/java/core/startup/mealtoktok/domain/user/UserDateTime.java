package core.startup.mealtoktok.domain.user;

import java.time.LocalDateTime;

public record UserDateTime(LocalDateTime createdAt, LocalDateTime modifiedAt) {
    public static UserDateTime of(LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new UserDateTime(createdAt, modifiedAt);
    }
}
