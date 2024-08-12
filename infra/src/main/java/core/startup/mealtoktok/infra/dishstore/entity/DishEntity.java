package core.startup.mealtoktok.infra.dishstore.entity;

import jakarta.persistence.*;

import lombok.*;

import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishInfo;

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

    private String imgUrl;

    private Long dishStoreId;

    private Long dishCategoryId;

    public static DishEntity of(Long dishStoreId, Long dishCategoryId, DishInfo dishInfo) {
        return DishEntity.builder()
                .dishName(dishInfo.dishName())
                .imgUrl(dishInfo.imgUrl())
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public Dish toDomain() {
        return Dish.builder()
                .dishId(dishId)
                .dishName(dishName)
                .imgUrl(imgUrl)
                .dishStoreId(dishStoreId)
                .dishCategoryId(dishCategoryId)
                .build();
    }

    public void update(DishInfo dishInfo) {
        this.dishName = dishInfo.dishName();
        this.imgUrl = dishInfo.imgUrl();
    }
}
