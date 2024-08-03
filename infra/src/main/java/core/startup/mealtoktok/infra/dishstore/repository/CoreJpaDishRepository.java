package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.*;
import core.startup.mealtoktok.infra.dishstore.entity.DishCategoryEntity;
import core.startup.mealtoktok.infra.dishstore.entity.DishEntity;
import core.startup.mealtoktok.infra.dishstore.entity.DishStoreEntity;
import core.startup.mealtoktok.infra.dishstore.exception.DishCategoryNotFoundException;
import core.startup.mealtoktok.infra.dishstore.exception.DishNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreJpaDishRepository implements DishRepository {

    private final JpaDishRepository jpaDishRepository;
    private final JpaDishStoreRepository jpaDishStoreRepository;
    private final JpaDishCategoryRepository jpaDishCategoryRepository;

    @Override
    public void save(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo) {
        DishStoreEntity dishStoreEntity =
                jpaDishStoreRepository.getReferenceById(dishStore.getStoreId());
        DishCategoryEntity dishCategoryEntity =
                jpaDishCategoryRepository.getReferenceById(dishCategory.getCategoryId());
        jpaDishRepository.save(DishEntity.of(dishStoreEntity, dishCategoryEntity, dishInfo));
    }

    @Override
    public Dish findCategoryById(TargetDish targetDish) {
        return jpaDishRepository
                .findById(targetDish.dishId())
                .map(DishEntity::toDomain)
                .orElseThrow(() -> DishNotFoundException.EXCEPTION);
    }

    @Override
    public void delete(Dish dish) {
        DishEntity dishEntity = jpaDishRepository.getReferenceById(dish.getDishId());
        jpaDishRepository.delete(dishEntity);
    }

    @Override
    public List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory) {
        return jpaDishRepository
                .findAllByStoreAndCategory(dishStore.getStoreId(), dishCategory.getCategoryId())
                .stream()
                .map(DishEntity::toDomain)
                .toList();
    }

    @Override
    public void update(Dish dish, DishInfo dishInfo) {
        DishEntity dishEntity = jpaDishRepository.getReferenceById(dish.getDishId());
        dishEntity.update(dishInfo);
    }

    @Override
    public boolean existsByDishStoreEntityAndDishName(DishStore dishStore, String dishName) {
        DishStoreEntity dishStoreEntity =
                jpaDishStoreRepository.getReferenceById(dishStore.getStoreId());
        return jpaDishRepository.existsByDishStoreEntityAndDishName(dishStoreEntity, dishName);
    }

    @Override
    public DishCategory findCategoryById(TargetDishCategory targetDishCategory) {
        return jpaDishCategoryRepository
                .findById(targetDishCategory.categoryId())
                .map(DishCategoryEntity::toDomain)
                .orElseThrow(() -> DishCategoryNotFoundException.EXCEPTION);
    }
}
