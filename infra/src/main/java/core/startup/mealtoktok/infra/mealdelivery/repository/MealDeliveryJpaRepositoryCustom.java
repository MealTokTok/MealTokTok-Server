package core.startup.mealtoktok.infra.mealdelivery.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import core.startup.mealtoktok.domain.mealdelivery.MealDeliverySearchCond;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;
import core.startup.mealtoktok.infra.mealdelivery.entity.MealDeliveryEntity;

public interface MealDeliveryJpaRepositoryCustom {

    Slice<MealDeliveryEntity> search(
            Recipient recipient, MealDeliverySearchCond cond, Pageable pageable);
}
