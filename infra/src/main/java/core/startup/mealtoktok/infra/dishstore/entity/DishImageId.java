package core.startup.mealtoktok.infra.dishstore.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class DishImageId implements Serializable {

    private Long dishId;

    private Long imageId;

    public DishImageId(Long dishId, Long imageId) {
        this.dishId = dishId;
        this.imageId = imageId;
    }

    public void update(Long imageId) {
        this.imageId = imageId;
    }
}
