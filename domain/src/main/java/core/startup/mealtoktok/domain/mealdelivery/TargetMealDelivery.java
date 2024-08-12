package core.startup.mealtoktok.domain.mealdelivery;

public record TargetMealDelivery(Long mealDeliveryId) {

    public static TargetMealDelivery from(Long mealDeliveryId) {
        return new TargetMealDelivery(mealDeliveryId);
    }
}
