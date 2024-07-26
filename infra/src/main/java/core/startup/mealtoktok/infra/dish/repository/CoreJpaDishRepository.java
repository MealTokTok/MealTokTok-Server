package core.startup.mealtoktok.infra.dish.repository;

import core.startup.mealtoktok.domain.dish.DishStore;
import core.startup.mealtoktok.domain.dish.Dish;
import core.startup.mealtoktok.domain.dish.DishInfo;
import core.startup.mealtoktok.domain.dish.DishRepository;
import core.startup.mealtoktok.domain.dish.TargetDish;
import core.startup.mealtoktok.domain.dish.DishCategory;
import core.startup.mealtoktok.infra.dish.entity.DishEntity;
import core.startup.mealtoktok.infra.dish.exception.DishNotFoundException;
import core.startup.mealtoktok.infra.dish.entity.DishCategoryEntity;
import core.startup.mealtoktok.infra.dish.entity.DishStoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreJpaDishRepository implements DishRepository {

    private final JpaDishRepository jpaDishRepository;
    private final JpaDishStoreRepository jpaDishStoreRepository;
    private final JpaDishCategoryRepository jpaDishCategoryRepository;

    @Override
    public void save(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo) {
        DishStoreEntity dishStoreEntity = jpaDishStoreRepository.getReferenceById(dishStore.getStoreId());
        DishCategoryEntity dishCategoryEntity = jpaDishCategoryRepository.getReferenceById(dishCategory.getCategoryId());
        jpaDishRepository.save(DishEntity.of(dishStoreEntity, dishCategoryEntity, dishInfo));
    }

    @Override
    public Dish findById(TargetDish targetDish) {
        return jpaDishRepository.findById(targetDish.dishId())
                .map(DishEntity::toDomain)
                .orElseThrow(()-> DishNotFoundException.EXCEPTION);
    }

    @Override
    public void delete(Dish dish) {
        DishEntity dishEntity = jpaDishRepository.getReferenceById(dish.getDishId());
        jpaDishRepository.delete(dishEntity);
    }

    @Override
    public List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory) {
        return jpaDishRepository.findAllByStoreAndCategory(dishStore.getStoreId(), dishCategory.getCategoryId())
                .stream().map(DishEntity::toDomain)
                .toList();
    }

    @Override
    public void update(Dish dish, DishInfo dishInfo) {
        DishEntity dishEntity = jpaDishRepository.getReferenceById(dish.getDishId());
        dishEntity.update(dishInfo);
    }

    @Override
    public boolean existsByDishStoreEntityAndDishName(DishStore dishStore, String dishName) {
        DishStoreEntity dishStoreEntity = jpaDishStoreRepository.getReferenceById(dishStore.getStoreId());
        return jpaDishRepository.existsByDishStoreEntityAndDishName(dishStoreEntity, dishName);
    }

}
