package core.startup.mealtoktok.domain.order;

import core.startup.mealtoktok.domain.user.User;

public record Orderer(Long userId) {

    public static Orderer from(User user) {
        return new Orderer(user.getUserId());
    }

    public boolean isSameUser(Orderer orderer) {
        return userId.equals(orderer.userId());
    }
}
