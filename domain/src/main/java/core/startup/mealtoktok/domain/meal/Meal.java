package core.startup.mealtoktok.domain.meal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meal {

    private Long mealId;
    private MealOwner mealOwner;
    private MealInfo mealInfo;

    public static Meal of(MealOwner mealOwner, MealInfo mealInfo) {
        return new Meal(null, mealOwner, mealInfo);
    }

    public boolean isOwnedBy(Meal meal, MealOwner mealOwner) {
        return meal.mealOwner.isSameUser(mealOwner);
    }
}
