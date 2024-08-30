package core.startup.mealtoktok.infra.dishstore.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDishImageEntity is a Querydsl query type for DishImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDishImageEntity extends EntityPathBase<DishImageEntity> {

    private static final long serialVersionUID = -762426455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDishImageEntity dishImageEntity = new QDishImageEntity("dishImageEntity");

    public final QDishImageId dishImageId;

    public QDishImageEntity(String variable) {
        this(DishImageEntity.class, forVariable(variable), INITS);
    }

    public QDishImageEntity(Path<? extends DishImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDishImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDishImageEntity(PathMetadata metadata, PathInits inits) {
        this(DishImageEntity.class, metadata, inits);
    }

    public QDishImageEntity(Class<? extends DishImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dishImageId = inits.isInitialized("dishImageId") ? new QDishImageId(forProperty("dishImageId")) : null;
    }

}

