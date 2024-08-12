package core.startup.mealtoktok.domain.order;

import java.util.List;

import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;

public record OrderDetail(Order order, List<MealDelivery> mealDeliveries) {

    public static OrderDetail of(Order order, List<MealDelivery> mealDeliveries) {
        return new OrderDetail(order, mealDeliveries);
    }
}
