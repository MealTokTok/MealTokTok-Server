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
import core.startup.mealtoktok.infra.jpa.entity.ImageEntity;

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
            DishStore dishStore, DishCategory dishCategory, DishContent dishContent, Image image) {

        DishEntity dishEntity =
                jpaDishRepository.save(
                        DishEntity.of(
                                dishStore.getStoreId(), dishCategory.getCategoryId(), dishContent));

        jpaDishImageRepository.save(DishImageEntity.of(dishEntity.getDishId(), image.getId()));
    }

    @Override
    public void deleteDish(Dish dish) {
        jpaDishRepository.getReferenceById(dish.getDishId()).delete();
    }

    @Override
    public void updateDish(Dish dish, DishContent dishContent, Image image) {
        jpaDishRepository.getReferenceById(dish.getDishId()).update(dishContent);

        if (image != null) {
            jpaDishImageRepository.deleteByDishId(dish.getDishId());
            jpaDishImageRepository.save(DishImageEntity.of(dish.getDishId(), image.getId()));
        }
    }

    @Override
    public boolean existsByDishStoreIdAndDishName(DishStore dishStore, String dishName) {
        return jpaDishRepository.existsByDishStoreIdAndDishNameAndIsDeletedFalse(
                dishStore.getStoreId(), dishName);
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

    public List<Dish> findAllByKeyword(String keyword) {
        return jpaDishRepository.findByIsDeletedFalseAndDishNameContaining(keyword).stream()
                .map(this::toDishWithImage)
                .toList();
    }

    @Override
    public Dish findActiveDishById(TargetDish targetDish) {
        return jpaDishRepository
                .findByDishIdAndIsDeletedFalse(targetDish.dishId())
                .map(this::toDishWithImage)
                .orElseThrow(() -> DishNotFoundException.EXCEPTION);
    }

    @Override
    public void findAllActiveDishById(List<Long> dishIds) {
        jpaDishRepository.findAllByDishIdInAndIsDeletedFalse(dishIds).stream()
                .map(this::toDishWithImage)
                .toList();
    }

    public List<Dish> findAllByCategory(DishCategory dishCategory) {
        return jpaDishRepository
                .findByDishCategoryIdAndIsDeletedFalse(dishCategory.getCategoryId())
                .stream()
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

        DishImageEntity dishImageEntity =
                jpaDishImageRepository
                        .findByDishId(dishEntity.getDishId())
                        .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

        ImageEntity imageEntity =
                jpaImageRepository
                        .findById(dishImageEntity.getDishImageId().getImageId())
                        .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

        return dishEntity.toDomain(imageEntity);
    }
}
