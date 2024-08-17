package core.startup.mealtoktok.infra.meal.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.meal.Meal;
import core.startup.mealtoktok.domain.meal.MealAndDishIds;
import core.startup.mealtoktok.domain.meal.MealContent;
import core.startup.mealtoktok.domain.meal.MealDish;
import core.startup.mealtoktok.domain.meal.MealOwner;
import core.startup.mealtoktok.domain.meal.MealRepository;
import core.startup.mealtoktok.domain.meal.TargetMeal;
import core.startup.mealtoktok.infra.dishstore.exception.DishNotFoundException;
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
    public boolean exitsByNameExcludingTargetMeal(Meal meal, String mealName) {
        return jpaMealRepository.existsByMealNameAndMealIdNot(mealName, meal.mealId());
    }

    @Override
    public List<Meal> findAllByMealOwner(MealOwner mealOwner) {
        return jpaMealRepository.findAllByMealOwner(mealOwner).stream()
                .map(MealEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(MealAndDishIds mealAndDishIds) {
        MealEntity savedMeal = jpaMealRepository.save(MealEntity.of(mealAndDishIds.meal()));
        mealAndDishIds.dishIds().stream()
                .map(dishId -> MealDishEntity.of(savedMeal.getMealId(), dishId))
                .forEach(jpaMealDishRepository::save);
    }

    @Override
    public Meal findById(TargetMeal targetMeal) {
        return jpaMealRepository
                .findById(targetMeal.meadId())
                .map(MealEntity::toDomain)
                .orElseThrow(() -> DishNotFoundException.EXCEPTION);
    }

    @Override
    public void update(Meal meal, List<MealDish> mealDishes, MealContent mealContent) {
        jpaMealRepository.getReferenceById(meal.mealId()).update(mealContent.mealInfo());

        List<MealDishEntity> mealDishEntities =
                jpaMealDishRepository.getReferenceByMealId(meal.mealId());

        IntStream.range(0, mealDishEntities.size())
                .forEach(
                        i -> {
                            MealDishEntity mealDishEntity = mealDishEntities.get(i);
                            Long dishId = mealContent.dishIds().get(i);
                            mealDishEntity.update(dishId);
                        });
    }

    @Override
    public void delete(Meal meal, List<MealDish> mealDishes) {
        jpaMealRepository.deleteById(meal.mealId());
        mealDishes.forEach(mealDish -> jpaMealDishRepository.deleteById(mealDish.mealDishId()));
    }

    @Override
    public List<MealDish> findAllByMealId(TargetMeal targetMeal) {
        return jpaMealDishRepository.findAllByMealId(targetMeal.meadId()).stream()
                .map(MealDishEntity::toDomain)
                .toList();
    }
}
