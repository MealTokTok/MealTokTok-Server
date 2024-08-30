package core.startup.mealtoktok.infra.dishstore.entity;

import jakarta.persistence.*;

import lombok.*;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishInfo;
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

    private boolean isSoldOut;

    private Long dishStoreId;

    private Long dishCategoryId;

    public static DishEntity of(Long dishStoreId, Long dishCategoryId, DishInfo dishInfo) {
        return DishEntity.builder()
                .dishName(dishInfo.dishName())
                .dishPrice(dishInfo.dishPrice())
                .isSoldOut(dishInfo.isSoldOut())
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public Dish toDomain() {
        return Dish.builder()
                .dishId(dishId)
                .dishName(dishName)
                .dishPrice(dishPrice)
                .isSoldOut(isSoldOut)
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public static DishEntity from(Dish dish) {
        return DishEntity.builder()
                .dishId(dish.getDishId())
                .dishName(dish.getDishName())
                .dishPrice(dish.getDishPrice())
                .isSoldOut(dish.isSoldOut())
                .dishStoreId(dish.getDishStoreId())
                .dishCategoryId(dish.getDishCategoryId())
                .build();
    }

    public void update(DishInfo dishInfo) {
        this.dishName = dishInfo.dishName();
        this.dishPrice = dishInfo.dishPrice();
        this.isSoldOut = dishInfo.isSoldOut();
    }
}
