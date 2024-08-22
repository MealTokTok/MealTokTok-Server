package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.dishstore.DishStoreInfo;
import core.startup.mealtoktok.domain.dishstore.DishStoreRepository;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;
import core.startup.mealtoktok.infra.dishstore.entity.DishStoreEntity;
import core.startup.mealtoktok.infra.dishstore.exception.DishStoreNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreDishStoreRepository implements DishStoreRepository {

    private final JpaDishStoreRepository jpaDishStoreRepository;

    @Override
    public DishStore findById(TargetDishStore targetStore) {
        return jpaDishStoreRepository
                .findById(targetStore.storeId())
                .map(DishStoreEntity::toDomain)
                .orElseThrow(() -> DishStoreNotFoundException.EXCEPTION);
    }

    @Override
    public void save(DishStoreInfo dishStoreInfo) {
        jpaDishStoreRepository.save(DishStoreEntity.from(dishStoreInfo));
    }

    @Override
    public boolean existsByDishStoreName(String dishStoreName) {
        return jpaDishStoreRepository.existsByStoreName(dishStoreName);
    }

    @Override
    public boolean existsByNameExcludingTargetStore(DishStore dishStore, String dishStoreName) {
        return jpaDishStoreRepository.existsByStoreNameAndStoreIdNot(
                dishStoreName, dishStore.getStoreId());
    }

    @Override
    public void delete(TargetDishStore targetDishStore) {
        DishStoreEntity dishStoreEntity =
                jpaDishStoreRepository.getReferenceById(targetDishStore.storeId());
        jpaDishStoreRepository.delete(dishStoreEntity);
    }

    @Override
    public void update(DishStore dishStore, DishStoreInfo dishStoreInfo) {
        DishStoreEntity dishStoreEntity =
                jpaDishStoreRepository.getReferenceById(dishStore.getStoreId());
        dishStoreEntity.update(dishStoreInfo);
    }

    @Override
    public List<DishStore> findAll() {
        return jpaDishStoreRepository.findAll().stream().map(DishStoreEntity::toDomain).toList();
    }
}
