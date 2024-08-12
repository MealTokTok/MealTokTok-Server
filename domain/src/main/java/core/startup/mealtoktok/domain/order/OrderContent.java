package core.startup.mealtoktok.domain.order;

import java.util.List;

public record OrderContent(
        OrderType orderType,
        List<OrderedMeal> orderedMeals,
        String specialInstruction,
        OrderPrice orderPrice) {}
