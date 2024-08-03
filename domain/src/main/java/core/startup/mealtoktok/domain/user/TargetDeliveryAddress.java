package core.startup.mealtoktok.domain.user;

public record TargetDeliveryAddress(Long deliveryAddressId) {

    public static TargetDeliveryAddress from(Long deliveryAddressId) {
        return new TargetDeliveryAddress(deliveryAddressId);
    }
}
