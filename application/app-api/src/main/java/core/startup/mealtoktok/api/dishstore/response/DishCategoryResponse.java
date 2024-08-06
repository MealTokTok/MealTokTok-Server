package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.domain.dishstore.DishCategory;

public record DishCategoryResponse(Long categoryId, String categoryName) {
    public static DishCategoryResponse from(DishCategory dishCategory) {
        return new DishCategoryResponse(
                dishCategory.getCategoryId(), dishCategory.getCategoryName());
    }
}
