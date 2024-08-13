package core.startup.mealtoktok.infra.meal.entity;

import core.startup.mealtoktok.domain.meal.Meal;
import core.startup.mealtoktok.domain.meal.MealInfo;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.infra.order.entity.MoneyConverter;

@Entity
@Table(name = "meal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    private String mealName;

    @Convert(converter = MoneyConverter.class)
    private Money mealPrice;

    public static MealEntity of(MealInfo mealInfo) {
        return MealEntity.builder().mealName(mealInfo.mealName()).mealPrice(mealInfo.mealPrice()).build();
    }

    public Meal toDomain() {
        return Meal.builder()
                .mealId(mealId)
                .mealInfo(MealInfo.of(mealName, mealPrice))
                .build();
    }

    public void update(MealInfo mealInfo) {
        this.mealName = mealInfo.mealName();
        this.mealPrice = mealInfo.mealPrice();
    }
}
