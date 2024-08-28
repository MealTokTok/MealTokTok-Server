package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.common.dto.Money;

@Getter
@Builder
public class Dish {

    private Long dishId;
    private String dishName;
    private String imgUrl;
    private Money dishPrice;
    private boolean isSoldOut;
    private Long dishStoreId;
    private Long dishCategoryId;
}
