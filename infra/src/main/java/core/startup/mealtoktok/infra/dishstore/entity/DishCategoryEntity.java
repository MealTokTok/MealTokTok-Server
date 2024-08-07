package core.startup.mealtoktok.infra.dishstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

import core.startup.mealtoktok.domain.dishstore.DishCategory;
import core.startup.mealtoktok.domain.dishstore.DishCategoryInfo;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class DishCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    String categoryName;

    public DishCategory toDomain() {
        return DishCategory.builder().categoryId(categoryId).categoryName(categoryName).build();
    }

    public static DishCategoryEntity from(DishCategoryInfo dishCategoryInfo) {
        return DishCategoryEntity.builder().categoryName(dishCategoryInfo.categoryName()).build();
    }

    public void update(DishCategoryInfo dishCategoryInfo) {
        this.categoryName = dishCategoryInfo.categoryName();
    }
}
