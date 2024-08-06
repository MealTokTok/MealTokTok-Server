package core.startup.mealtoktok.domain.dishstore;

public record DishCategoryInfo(
        String categoryName
) {

    public static DishCategoryInfo of(String dishCategoryName) {
        return new DishCategoryInfo(dishCategoryName);
    }
}