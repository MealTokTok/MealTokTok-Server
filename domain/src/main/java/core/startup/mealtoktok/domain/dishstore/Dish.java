package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Dish {
    private Long dishId;
    private String dishName;
    private String imgUrl;
    private Long dishStoreId;
    private Long dishCategoryId;
}
