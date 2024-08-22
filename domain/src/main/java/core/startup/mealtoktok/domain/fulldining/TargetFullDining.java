package core.startup.mealtoktok.domain.fulldining;

public record TargetFullDining(Long fullDiningId) {

    public static TargetFullDining from(Long fullDiningId) {
        return new TargetFullDining(fullDiningId);
    }
}
