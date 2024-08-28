package core.startup.mealtoktok.domain.order;

public record FullDiningInfo(Long mealDeliveryId, boolean hasFullDiningOption) {

    public static FullDiningInfo of(Long mealDeliveryId, boolean hasFullDiningOption) {
        return new FullDiningInfo(mealDeliveryId, hasFullDiningOption);
    }
}
