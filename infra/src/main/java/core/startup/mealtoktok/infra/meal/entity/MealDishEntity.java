package core.startup.mealtoktok.infra.meal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meal_dish")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MealDishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MealDishId;

    private Long mealId;

    private Long dishId;

    public static MealDishEntity of(Long mealId, Long dishId) {
        return MealDishEntity.builder().mealId(mealId).dishId(dishId).build();
    }

    public void update(Long mealId, Long dishId) {
        this.mealId = mealId;
        this.dishId = dishId;
    }
}
