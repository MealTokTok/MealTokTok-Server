package core.startup.mealtoktok.infra.dishstore.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.DishImage;
import core.startup.mealtoktok.infra.jpa.entity.ImageEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "dish_image")
public class DishImageEntity {

    @EmbeddedId private DishImageId dishImageId;

    public static DishImageEntity of(DishEntity savedDish, ImageEntity savedImage) {
        DishImageId dishImageId = new DishImageId(savedDish.getDishId(), savedImage.getId());
        return new DishImageEntity(dishImageId);
    }

    public DishImage toDomain() {
        return DishImage.builder()
                .dishId(this.dishImageId.getDishId())
                .imageId(this.dishImageId.getImageId())
                .build();
    }

    public void update(Long dishId) {
        this.dishImageId.update(dishId);
    }
}
