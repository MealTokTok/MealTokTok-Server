package core.startup.mealtoktok.domain.meal;

import core.startup.mealtoktok.domain.user.User;

public record MealOwner(Long userId) {

    public static MealOwner from(User user) {
        return new MealOwner(user.getUserId().getValue());
    }

    public boolean isSameUser(MealOwner mealOwner) {
        return userId.equals(mealOwner.userId());
    }
}
