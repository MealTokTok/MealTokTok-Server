package core.startup.mealtoktok.infra.meal.repository;

import core.startup.mealtoktok.domain.meal.Meal;
import core.startup.mealtoktok.domain.meal.TargetMeal;
import core.startup.mealtoktok.infra.dishstore.exception.DishNotFoundException;
import java.util.List;

import java.util.stream.IntStream;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.meal.MealDishes;
import core.startup.mealtoktok.domain.meal.MealRepository;
import core.startup.mealtoktok.infra.meal.entity.MealDishEntity;
import core.startup.mealtoktok.infra.meal.entity.MealEntity;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreJpaMealRepository implements MealRepository {

    private final JpaMealDishRepository jpaMealDishRepository;
    private final JpaMealRepository jpaMealRepository;

    @Override
    public boolean exitsByMealName(String mealName) {
        return jpaMealRepository.existsByMealName(mealName);
    }

    @Override
    public void save(MealDishes mealDishes) {
        MealEntity savedMeal =
                jpaMealRepository.save(
                        MealEntity.of(mealDishes.mealInfo()));

        List<MealDishEntity> mealDishEntities =
                mealDishes.dishIds().stream()
                        .map(dishId -> MealDishEntity.of(savedMeal.getMealId(), dishId))
                        .toList();
        jpaMealDishRepository.saveAll(mealDishEntities);
    }

    @Override
    public Meal findById(TargetMeal targetMeal) {
        return jpaMealRepository.findById(targetMeal.meadId())
                .map(MealEntity::toDomain)
                .orElseThrow(()-> DishNotFoundException.EXCEPTION);
    }

    @Override
    public void update(Meal meal, MealDishes mealDishes) {
        MealEntity mealEntity = jpaMealRepository.getReferenceById(meal.mealId());
        mealEntity.update(mealDishes.mealInfo());

        List<MealDishEntity> mealDishEntities = jpaMealDishRepository.getReferenceByMealId(meal.mealId());

        IntStream.range(0, mealDishEntities.size())
                .forEach(i -> {
                    MealDishEntity mealDishEntity = mealDishEntities.get(i);
                    Long dishId = mealDishes.dishIds().get(i);
                    mealDishEntity.update(meal.mealId(), dishId);
                });
    }
}
