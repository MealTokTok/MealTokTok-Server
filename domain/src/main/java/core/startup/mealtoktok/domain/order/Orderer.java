package core.startup.mealtoktok.domain.order;

import core.startup.mealtoktok.domain.user.User;

public record Orderer(Long userId, Long deliveryAddressId) {

    public static Orderer from(User user) {
        return new Orderer(
                user.getUserId(), user.fetchConfiguredDeliveryAddress().getDeliveryAddressId());
    }

    public static Orderer of(Long userId, Long deliveryAddressId) {
        return new Orderer(userId, deliveryAddressId);
    }

    public boolean isSameUser(Orderer orderer) {
        return userId.equals(orderer.userId());
    }
}
