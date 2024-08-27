package core.startup.mealtoktok.infra.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.meal.MealOwner;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MealOwnerVO {

    @Column(name = "meal_owner_id")
    private Long userId;

    public static MealOwnerVO from(MealOwner mealOwner) {
        return new MealOwnerVO(mealOwner.userId());
    }

    public MealOwner toDomain() {
        return new MealOwner(userId);
    }
}
