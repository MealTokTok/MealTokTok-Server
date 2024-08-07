package core.startup.mealtoktok.api.dishstore.request;

import core.startup.mealtoktok.domain.dishstore.DishCategoryInfo;

public record DishCategoryRequest(String dishCategoryName) {
    public DishCategoryInfo toDishCategoryInfo() {
        return DishCategoryInfo.of(dishCategoryName);
    }
}
