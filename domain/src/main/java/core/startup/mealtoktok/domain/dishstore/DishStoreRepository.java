package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

public interface DishStoreRepository {

    DishStore findById(TargetDishStore targetStore);

    void save(DishStoreInfo dishStoreInfo);

    boolean existsByDishStoreName(String dishStoreName);

    void delete(TargetDishStore targetDishStore);

    void update(DishStore dishStore, DishStoreInfo dishStoreInfo);

    List<DishStore> findAll();

    boolean existsByNameExcludingTargetStore(DishStore dishStore, String dishStoreName);
}
