package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.dishstore.*;
import core.startup.mealtoktok.infra.dishstore.entity.DishCategoryEntity;
import core.startup.mealtoktok.infra.dishstore.entity.DishEntity;
import core.startup.mealtoktok.infra.dishstore.entity.DishImageEntity;
import core.startup.mealtoktok.infra.dishstore.exception.DishCategoryNotFoundException;
import core.startup.mealtoktok.infra.dishstore.exception.DishNotFoundException;
import core.startup.mealtoktok.infra.global.exception.ImageNotFoundException;
import core.startup.mealtoktok.infra.global.repository.JpaImageRepository;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreDishRepository implements DishRepository {

    private final JpaDishRepository jpaDishRepository;
    private final JpaDishCategoryRepository jpaDishCategoryRepository;
    private final JpaDishImageRepository jpaDishImageRepository;
    private final JpaImageRepository jpaImageRepository;

    @Override
    public void saveDish(
            DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo, Image image) {

        DishEntity dishEntity =
                jpaDishRepository.save(
                        DishEntity.of(
                                dishStore.getStoreId(), dishCategory.getCategoryId(), dishInfo));

        jpaDishImageRepository.save(DishImageEntity.of(dishEntity.getDishId(), image.getId()));
    }

    @Override
    public void deleteDish(Dish dish) {
        jpaDishRepository.deleteById(dish.getDishId());
        List<DishImage> dishImages = findAllDishImageByDishId(TargetDish.from(dish.getDishId()));
        deleteDishImages(dishImages);
    }

    @Override
    public void deleteDishImages(List<DishImage> dishImages) {
        dishImages.forEach(
                dishImage -> {
                    Long imageId = dishImage.imageId();
                    jpaImageRepository.deleteById(imageId);
                });

        dishImages.forEach(dishImage -> jpaDishImageRepository.deleteByDishId(dishImage.dishId()));
    }

    @Override
    public void updateDish(Dish dish, DishInfo dishInfo, Image image) {
        jpaDishRepository.getReferenceById(dish.getDishId()).update(dishInfo);

        if (image != null) {
            jpaDishImageRepository.deleteByDishId(dish.getDishImage().dishId());
            jpaDishImageRepository.save(DishImageEntity.of(dish.getDishId(), image.getId()));
        }
    }

    @Override
    public boolean existsByDishStoreIdAndDishName(DishStore dishStore, String dishName) {
        return jpaDishRepository.existsByDishStoreIdAndDishName(dishStore.getStoreId(), dishName);
    }

    @Override
    public boolean existsByNameExcludingTargetDish(
            DishStore dishStore, Dish dish, String dishName) {
        return jpaDishRepository.existsByDishStoreIdAndDishNameAndDishIdNot(
                dishStore.getStoreId(), dishName, dish.getDishId());
    }

    @Override
    public DishCategory findDishCategoryById(TargetDishCategory targetDishCategory) {
        return jpaDishCategoryRepository
                .findById(targetDishCategory.categoryId())
                .map(DishCategoryEntity::toDomain)
                .orElseThrow(() -> DishCategoryNotFoundException.EXCEPTION);
    }

    @Override
    public void saveDishCategory(DishCategoryInfo dishCategoryInfo) {
        jpaDishCategoryRepository.save(DishCategoryEntity.from(dishCategoryInfo));
    }

    @Override
    public boolean existsByDishCategoryName(String dishCategoryName) {
        return jpaDishCategoryRepository.existsByCategoryName(dishCategoryName);
    }

    @Override
    public boolean existsByNameExcludingTargetCategory(
            DishCategory dishCategory, String dishCategoryName) {
        return jpaDishCategoryRepository.existsByCategoryNameAndCategoryIdNot(
                dishCategoryName, dishCategory.getCategoryId());
    }

    @Override
    public void updateDishCategory(DishCategory dishCategory, DishCategoryInfo dishCategoryInfo) {
        DishCategoryEntity dishCategoryEntity =
                jpaDishCategoryRepository.getReferenceById(dishCategory.getCategoryId());
        dishCategoryEntity.update(dishCategoryInfo);
    }

    @Override
    public void deleteDishCategory(DishCategory dishCategory) {
        jpaDishCategoryRepository.deleteById(dishCategory.getCategoryId());
    }

    @Override
    public List<DishCategory> findAllCategories() {
        return jpaDishCategoryRepository.findAll().stream()
                .map(DishCategoryEntity::toDomain)
                .toList();
    }

    @Override
    public List<DishImage> findAllDishImageByDishId(TargetDish targetDish) {
        return jpaDishImageRepository.findAllByDishId(targetDish.dishId()).stream()
                .map(DishImageEntity::toDomain)
                .toList();
    }

    public List<Dish> findAllByKeyword(String keyword) {
        return jpaDishRepository.findByDishNameContaining(keyword).stream()
                .map(this::toDishWithImage)
                .toList();
    }

    public List<Dish> findAllByCategory(DishCategory dishCategory) {
        return jpaDishRepository.findByDishCategoryId(dishCategory.getCategoryId()).stream()
                .map(this::toDishWithImage)
                .toList();
    }

    public Dish findDishById(TargetDish targetDish) {
        DishEntity dishEntity =
                jpaDishRepository
                        .findById(targetDish.dishId())
                        .orElseThrow(() -> DishNotFoundException.EXCEPTION);

        return toDishWithImage(dishEntity);
    }

    private Dish toDishWithImage(DishEntity dishEntity) {
        Dish dish = dishEntity.toDomain();

        DishImage dishImage =
                jpaDishImageRepository
                        .findByDishId(dish.getDishId())
                        .map(DishImageEntity::toDomain)
                        .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

        dish.addDishImage(dishImage);
        return dish;
    }
}
