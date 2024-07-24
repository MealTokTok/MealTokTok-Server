package core.startup.mealtoktok.domain.dishCategory;

public record TargetDishCategory(
        Long categoryId
) {
    public static TargetDishCategory from(Long categoryId){
        return new TargetDishCategory(categoryId);
    }
}
