package core.startup.mealtoktok.domain.dish;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Dish {
    Long dishId;
    String dishName;
    String imgUrl;
    Long dishStoreId;
    Long dishCategoryId;
}
