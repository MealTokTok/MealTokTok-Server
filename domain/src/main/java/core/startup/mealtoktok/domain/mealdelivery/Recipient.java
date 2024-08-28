package core.startup.mealtoktok.domain.mealdelivery;

import core.startup.mealtoktok.domain.user.User;

public record Recipient(Long userId) {

    public static Recipient from(User user) {
        return new Recipient(user.getUserId().getValue());
    }
}
