package core.startup.mealtoktok.infra.meal.repository;

import java.util.List;
import java.util.stream.Collectors;

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
import core.startup.mealtoktok.infra.meal.entity.MealDishEntity;
import core.startup.mealtoktok.infra.meal.entity.MealEntity;
import core.startup.mealtoktok.infra.meal.entity.MealOwnerVO;
import core.startup.mealtoktok.infra.meal.exception.MealNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreMealRepository implements MealRepository {

    private final JpaMealDishRepository jpaMealDishRepository;
    private final JpaMealRepository jpaMealRepository;

    @Override
    public boolean exitsByMealName(String mealName) {
        return jpaMealRepository.existsByMealNameAndIsDeletedFalse(mealName);
    }

    @Override
    public boolean exitsByNameExcludingTargetMeal(Meal meal, String mealName) {
        return jpaMealRepository.existsByMealNameAndMealIdNot(mealName, meal.getMealId());
    }

    @Override
    public List<Meal> findAllByMealOwner(MealOwner mealOwner) {
        return jpaMealRepository.findAllByMealOwner(MealOwnerVO.from(mealOwner)).stream()
                .map(MealEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Meal findActiveMealById(TargetMeal targetMeal) {
        return jpaMealRepository.findByMealIdAndIsDeletedFalse(targetMeal.meadId())
                .map(MealEntity::toDomain)
                .orElseThrow(() -> MealNotFoundException.EXCEPTION);
    }

    @Override
    public void updateMealDish(MealDish mealDish, Long dishId) {
        jpaMealDishRepository.getReferenceById(mealDish.mealDishId()).update(dishId);
    }

    @Override
    public void save(MealAndDishIds mealAndDishIds) {
        MealEntity savedMeal = jpaMealRepository.save(MealEntity.from(mealAndDishIds.meal()));
        mealAndDishIds.dishIds().stream()
                .map(dishId -> MealDishEntity.of(savedMeal.getMealId(), dishId))
                .forEach(jpaMealDishRepository::save);
    }

    @Override
    public Meal findById(TargetMeal targetMeal) {
        return jpaMealRepository
                .findById(targetMeal.meadId())
                .map(MealEntity::toDomain)
                .orElseThrow(() -> MealNotFoundException.EXCEPTION);
    }

    @Override
    public void update(Meal meal, MealContent updatedMealContent) {
        jpaMealRepository.getReferenceById(meal.getMealId()).update(updatedMealContent.mealInfo());
    }

    @Override
    public void delete(Meal meal) {
        jpaMealRepository.getReferenceById(meal.getMealId()).delete();
    }

    @Override
    public List<MealDish> findAllMealDishByMealId(TargetMeal targetMeal) {
        return jpaMealDishRepository.findAllByMealId(targetMeal.meadId()).stream()
                .map(MealDishEntity::toDomain)
                .toList();
    }
}
