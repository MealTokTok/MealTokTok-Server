package core.startup.mealtoktok.infra.order.repository;

import static core.startup.mealtoktok.infra.jpa.util.PagingUtil.*;
import static core.startup.mealtoktok.infra.order.entity.QOrderEntity.orderEntity;
import static core.startup.mealtoktok.infra.user.entity.QUserEntity.userEntity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.OrderSearchCond;
import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.order.entity.OrderEntity;
import core.startup.mealtoktok.infra.order.entity.QOrderEntity;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepositoryImpl implements OrderJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<OrderEntity> search(Orderer orderer, OrderSearchCond cond, Pageable pageable) {

        List<OrderEntity> contents =
                queryFactory
                        .selectFrom(orderEntity)
                        .leftJoin(userEntity)
                        .on(orderEntity.orderer.userId.eq(userEntity.userId))
                        .where(
                                orderEntity.orderer.userId.eq(orderer.userId()),
                                eqOrderState(cond.orderState()))
                        .orderBy(getSort(pageable))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<Long> count = queryFactory.select(orderEntity.count()).from(orderEntity);

        return PageableExecutionUtils.getPage(contents, pageable, count::fetchOne);
    }

    public BooleanExpression eqOrderState(OrderState orderState) {
        return orderState == null ? null : orderEntity.orderState.eq(orderState);
    }

    private static OrderSpecifier<?> getSort(Pageable pageable) {
        return getOrderSpecifier(
                pageable.getSort(), new PathBuilder<>(QOrderEntity.class, "orderEntity"));
    }
}
