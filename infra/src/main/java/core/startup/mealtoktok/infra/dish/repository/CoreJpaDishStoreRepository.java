package core.startup.mealtoktok.infra.dish.repository;

import core.startup.mealtoktok.domain.dish.DishStore;
import core.startup.mealtoktok.domain.dish.DishStoreRepository;
import core.startup.mealtoktok.domain.dish.TargetDishStore;
import core.startup.mealtoktok.infra.dish.entity.DishStoreEntity;
import core.startup.mealtoktok.infra.dish.exception.DishStoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreJpaDishStoreRepository implements DishStoreRepository {

    private final JpaDishStoreRepository jpaDishStoreRepository;

    @Override
    public DishStore findById(TargetDishStore targetStore) {
        return jpaDishStoreRepository.findById(targetStore.storeId())
                .map(DishStoreEntity::toDomain)
                .orElseThrow(() -> DishStoreNotFoundException.EXCEPTION);
    }
}
