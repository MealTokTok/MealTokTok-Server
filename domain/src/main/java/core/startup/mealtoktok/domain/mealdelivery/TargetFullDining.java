package core.startup.mealtoktok.domain.mealdelivery;

public record TargetFullDining(Long fullDiningId) {

    public static TargetFullDining from(Long fullDiningId) {
        return new TargetFullDining(fullDiningId);
    }
}
