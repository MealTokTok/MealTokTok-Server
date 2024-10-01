package core.startup.mealtoktok.api.meal.dto;

import java.util.List;

import core.startup.mealtoktok.domain.meal.MealContent;
import core.startup.mealtoktok.domain.meal.MealInfo;

public record MealRequest(String mealName, int mealPrice, List<Long> dishIds) {

    public MealContent toContent() {
        return MealContent.of(MealInfo.of(mealName, mealPrice, false), dishIds);
    }
}
