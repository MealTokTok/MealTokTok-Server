package core.startup.mealtoktok.infra.dishstore.entity;

import jakarta.persistence.*;

import lombok.*;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishInfo;
import core.startup.mealtoktok.domain.dishstore.DishState;
import core.startup.mealtoktok.infra.order.entity.MoneyConverter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "dish")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    private String dishName;

    @Convert(converter = MoneyConverter.class)
    private Money dishPrice;

    @Enumerated(EnumType.STRING)
    private DishState dishState;

    private int dishQuantity;

    private Long dishStoreId;

    private Long dishCategoryId;

    public static DishEntity of(Long dishStoreId, Long dishCategoryId, DishInfo dishInfo) {
        DishState dishState = dishInfo.dishQuantity() == 0 ? DishState.SOLD_OUT : DishState.ON_SALE;

        return DishEntity.builder()
                .dishName(dishInfo.dishName())
                .dishPrice(dishInfo.dishPrice())
                .dishQuantity(dishInfo.dishQuantity())
                .dishState(dishState)
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public Dish toDomain() {
        return Dish.builder()
                .dishId(dishId)
                .dishName(dishName)
                .dishPrice(dishPrice)
                .dishState(dishState)
                .dishQuantity(dishQuantity)
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public static DishEntity from(Dish dish) {
        return DishEntity.builder()
                .dishId(dish.getDishId())
                .dishName(dish.getDishName())
                .dishPrice(dish.getDishPrice())
                .dishState(dish.getDishState())
                .dishQuantity(dish.getDishQuantity())
                .dishStoreId(dish.getDishStoreId())
                .dishCategoryId(dish.getDishCategoryId())
                .build();
    }

    public void update(DishInfo dishInfo) {
        this.dishName = dishInfo.dishName();
        this.dishPrice = dishInfo.dishPrice();
        this.dishQuantity = dishInfo.dishQuantity();
        this.dishState = dishInfo.dishQuantity() == 0 ? DishState.SOLD_OUT : DishState.ON_SALE;
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && this.dishQuantity >= amount) {
            this.dishQuantity -= amount;
            if (this.dishQuantity == 0) {
                this.dishState = DishState.SOLD_OUT;
            }
        }
    }
}
