package core.startup.mealtoktok.domain.meal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishAndImage;
import core.startup.mealtoktok.domain.dishstore.DishImage;
import core.startup.mealtoktok.domain.dishstore.DishImageReader;
import core.startup.mealtoktok.domain.dishstore.DishReader;
import core.startup.mealtoktok.domain.dishstore.TargetDish;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.TargetImage;

@Service
@RequiredArgsConstructor
@Transactional
public class MealService {

    private final MealReader mealReader;
    private final MealUpdater mealUpdater;
    private final MealAppender mealAppender;
    private final MealRemover mealRemover;
    private final DishReader dishReader;
    private final MealDishReader mealDishReader;
    private final DishImageReader dishImageReader;
    private final ImageReader imageReader;

    public void createMeal(MealOwner mealOwner, MealContent newMealContent) {
        mealAppender.append(mealOwner, newMealContent);
    }

    public void updateMeal(
            MealOwner mealOwner, TargetMeal targetMeal, MealContent updatedMealContent) {
        Meal meal = mealReader.read(targetMeal);
        List<MealDish> mealDishes = mealDishReader.read(targetMeal);
        mealUpdater.update(mealOwner, meal, mealDishes, updatedMealContent);
    }

    public void deleteMeal(MealOwner mealOwner, TargetMeal targetMeal) {
        Meal meal = mealReader.read(targetMeal);
        List<MealDish> mealDishes = mealDishReader.read(targetMeal);
        mealRemover.remove(mealOwner, meal, mealDishes);
    }

    public MealAndDishes readMealAndDishes(TargetMeal targetMeal) {
        Meal meal = mealReader.read(targetMeal);
        List<DishAndImage> dishes = readDishesForMeal(meal);
        return MealAndDishes.of(meal, dishes);
    }

    public List<MealAndDishes> readMealAndDishes(MealOwner mealOwner) {
        return mealReader.read(mealOwner).stream()
                .map(meal -> MealAndDishes.of(meal, readDishesForMeal(meal)))
                .toList();
    }

    private List<DishAndImage> readDishesForMeal(Meal meal) {
        return mealDishReader.read(TargetMeal.from(meal.getMealId())).stream()
                .map(
                        mealDish -> {
                            Dish dish = dishReader.read(TargetDish.from(mealDish.dishId()));
                            DishImage dishImage =
                                    dishImageReader.read(TargetDish.from(dish.getDishId()));
                            Image image = imageReader.read(TargetImage.from(dishImage.imageId()));
                            return DishAndImage.of(dish, image);
                        })
                .collect(Collectors.toList());
    }
}
