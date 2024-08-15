package core.startup.mealtoktok.domain.meal;

import java.util.List;

public record MealContent(MealInfo mealInfo, List<Long> dishIds) {
    public static MealContent of(MealInfo mealInfo, List<Long> dishIds) {
        return new MealContent(mealInfo, dishIds);
    }
}
