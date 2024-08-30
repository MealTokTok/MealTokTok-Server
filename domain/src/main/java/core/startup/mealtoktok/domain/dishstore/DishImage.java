package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;

@Builder
public record DishImage(Long dishId, Long imageId) {
    public static DishImage of(Long dishId, Long imageId) {
        return new DishImage(dishId, imageId);
    }
}
