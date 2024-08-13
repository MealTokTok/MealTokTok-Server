package core.startup.mealtoktok.domain.meal;

public record TargetMeal(
        Long meadId
) {
    public static TargetMeal from(Long meadId) {
        return new TargetMeal(meadId);
    }
}
