package core.startup.mealtoktok.infra.meal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
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

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.meal.Meal;
import core.startup.mealtoktok.domain.meal.MealInfo;
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

    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "meal_owner_id"))
    private MealOwnerVO mealOwner;

    private boolean isDeleted;

    public static MealEntity from(Meal meal) {
        return MealEntity.builder()
                .mealName(meal.getMealInfo().mealName())
                .mealPrice(meal.getMealInfo().mealPrice())
                .mealOwner(MealOwnerVO.from(meal.getMealOwner()))
                .isDeleted(false)
                .build();
    }

    public Meal toDomain() {
        return Meal.builder()
                .mealId(mealId)
                .mealOwner(mealOwner.toDomain())
                .mealInfo(MealInfo.of(mealName, mealPrice))
                .build();
    }

    public void update(MealInfo mealInfo) {
        this.mealName = mealInfo.mealName();
        this.mealPrice = mealInfo.mealPrice();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
