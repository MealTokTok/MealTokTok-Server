package core.startup.mealtoktok.domain.user;

public record TargetDeliveryAddress(Long deliveryAddressId) {

    public static TargetDeliveryAddress from(Long deliveryAddressId) {
        return new TargetDeliveryAddress(deliveryAddressId);
    }

    public boolean isTarget(DeliveryAddress deliveryAddress) {
        return this.deliveryAddressId.equals(deliveryAddress.getDeliveryAddressId());
    }
}
