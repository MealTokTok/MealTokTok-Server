package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDate;

import core.startup.mealtoktok.domain.order.ReservedSchedule;
import core.startup.mealtoktok.domain.order.ReservedTime;

public record OrderedMeal(
        Long mealId,
        ReservedSchedule reservedSchedule,
        boolean includeRice,
        boolean hasFullDiningOption) {

    public static OrderedMeal create(
            Long mealId,
            LocalDate reservedDate,
            ReservedTime reservedTime,
            boolean includeRice,
            boolean hasFullDiningOption) {

        return new OrderedMeal(
                mealId,
                ReservedSchedule.schedule(reservedDate, reservedTime),
                includeRice,
                hasFullDiningOption);
    }
}
