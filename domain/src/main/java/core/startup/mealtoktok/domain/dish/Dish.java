package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Dish {

    Long dishId;

    Long storeId;

    String dishName;

    DishCategory dishCategory;
}
