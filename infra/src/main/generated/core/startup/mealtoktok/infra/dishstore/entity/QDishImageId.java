package core.startup.mealtoktok.infra.dishstore.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDishImageId is a Querydsl query type for DishImageId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDishImageId extends BeanPath<DishImageId> {

    private static final long serialVersionUID = 1463240673L;

    public static final QDishImageId dishImageId = new QDishImageId("dishImageId");

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    public QDishImageId(String variable) {
        super(DishImageId.class, forVariable(variable));
    }

    public QDishImageId(Path<? extends DishImageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDishImageId(PathMetadata metadata) {
        super(DishImageId.class, metadata);
    }

}

