package core.startup.mealtoktok.infra.dishstore.entity;

import jakarta.persistence.*;

import lombok.*;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishContent;
import core.startup.mealtoktok.domain.dishstore.DishState;
import core.startup.mealtoktok.infra.jpa.entity.ImageEntity;
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

    public static DishEntity of(Long dishStoreId, Long dishCategoryId, DishContent dishContent) {
        DishState dishState =
                dishContent.dishQuantity() == 0 ? DishState.SOLD_OUT : DishState.ON_SALE;

        return DishEntity.builder()
                .dishName(dishContent.dishName())
                .dishPrice(dishContent.dishPrice())
                .dishQuantity(dishContent.dishQuantity())
                .dishState(dishState)
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public Dish toDomain(ImageEntity image) {
        return Dish.builder()
                .dishId(dishId)
                .dishName(dishName)
                .dishPrice(dishPrice)
                .dishState(dishState)
                .dishQuantity(dishQuantity)
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .dishImage(image.toDomain())
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

    public void update(DishContent dishContent) {
        this.dishName = dishContent.dishName();
        this.dishPrice = dishContent.dishPrice();
        this.dishQuantity = dishContent.dishQuantity();
        this.dishState = dishContent.dishQuantity() == 0 ? DishState.SOLD_OUT : DishState.ON_SALE;
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
