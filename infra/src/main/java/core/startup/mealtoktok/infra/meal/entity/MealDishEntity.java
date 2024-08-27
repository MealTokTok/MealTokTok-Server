package core.startup.mealtoktok.infra.meal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.meal.MealDish;

@Entity
@Table(name = "meal_dish")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MealDishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealDishId;

    private Long mealId;

    private Long dishId;

    public static MealDishEntity of(Long mealId, Long dishId) {
        return MealDishEntity.builder().mealId(mealId).dishId(dishId).build();
    }

    public static MealDishEntity from(MealDish mealDish) {
        return MealDishEntity.builder().mealId(mealDish.mealId()).dishId(mealDish.dishId()).build();
    }

    public MealDish toDomain() {
        return MealDish.builder().mealDishId(mealDishId).mealId(mealId).dishId(dishId).build();
    }

    public void update(Long dishId) {
        this.dishId = dishId;
    }
}
