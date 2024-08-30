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
            DishStore dishStore, DishCategory dishCategory, List<Image> images, DishInfo dishInfo) {

        DishEntity dishEntity =
                jpaDishRepository.save(
                        DishEntity.of(
                                dishStore.getStoreId(), dishCategory.getCategoryId(), dishInfo));

        saveDishImages(dishEntity.toDomain(), images);
    }

    @Override
    public void saveDishImages(Dish dish, List<Image> images) {
        List<ImageEntity> savedImages =
                jpaImageRepository.saveAll(images.stream().map(ImageEntity::from).toList());

        List<DishImageEntity> dishImageEntities =
                savedImages.stream()
                        .map(image -> DishImageEntity.of(DishEntity.from(dish), image))
                        .toList();

        jpaDishImageRepository.saveAll(dishImageEntities);
    }

    @Override
    public Dish findDishById(TargetDish targetDish) {
        return jpaDishRepository
                .findById(targetDish.dishId())
                .map(DishEntity::toDomain)
                .orElseThrow(() -> DishNotFoundException.EXCEPTION);
    }

    @Override
    public void deleteDish(Dish dish, List<DishImage> dishImages) {
        jpaDishRepository.deleteById(dish.getDishId());
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
    public List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory) {
        return jpaDishRepository
                .findAllByStoreAndCategory(dishStore.getStoreId(), dishCategory.getCategoryId())
                .stream()
                .map(DishEntity::toDomain)
                .toList();
    }

    @Override
    public DishImage findDishImageByDishId(TargetDish targetDish) {
        return jpaDishImageRepository
                .findByDishId(targetDish.dishId())
                .map(DishImageEntity::toDomain)
                .orElseThrow(() -> ImageNotFoundException.EXCEPTION);
    }

    @Override
    public void updateDish(Dish dish, DishInfo dishInfo) {
        jpaDishRepository.getReferenceById(dish.getDishId()).update(dishInfo);
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
        DishCategoryEntity dishCategoryEntity =
                jpaDishCategoryRepository.getReferenceById(dishCategory.getCategoryId());
        jpaDishCategoryRepository.delete(dishCategoryEntity);
    }

    @Override
    public List<DishCategory> findAllCategories() {
        return jpaDishCategoryRepository.findAll().stream()
                .map(DishCategoryEntity::toDomain)
                .toList();
    }

    @Override
    public List<Dish> findAllByStoreAndKeyword(DishStore dishStore, String keyword) {
        return jpaDishRepository.findByStoreIdAndDishName(dishStore.getStoreId(), keyword).stream()
                .map(DishEntity::toDomain)
                .toList();
    }

    @Override
    public List<DishImage> findAllDishImageByDishId(TargetDish targetDish) {
        return jpaDishImageRepository.findAllByDishId(targetDish.dishId()).stream()
                .map(DishImageEntity::toDomain)
                .toList();
    }
}
