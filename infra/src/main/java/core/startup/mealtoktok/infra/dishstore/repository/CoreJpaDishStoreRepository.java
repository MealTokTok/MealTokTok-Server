package core.startup.mealtoktok.infra.dishstore.repository;

import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.dishstore.DishStoreRepository;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;
import core.startup.mealtoktok.infra.auth.entity.DishStoreEntity;
import core.startup.mealtoktok.infra.dishstore.exception.DishStoreNotFoundException;
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