package core.startup.mealtoktok.infra.jpa.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import core.startup.mealtoktok.common.dto.Cursor;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;

public class PagingUtil {

    public static PageRequest toPageRequest(Cursor cursor) {
        // 여러 정렬 기준을 처리할 Sort 객체 생성
        Sort sort =
                Sort.by(
                        cursor.sortOrders().stream()
                                .map(
                                        order ->
                                                new Sort.Order(
                                                        Sort.Direction.fromString(
                                                                order.direction().name()),
                                                        order.key()))
                                .toList());

        return PageRequest.of(cursor.page(), cursor.size(), sort);
    }

    public static OrderSpecifier<?> getOrderSpecifier(Sort sort, PathBuilder<?> entityPath) {
        if (sort.isEmpty()) {
            return null;
        }

        for (Sort.Order order : sort) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();

            // 필드명으로 동적 경로 생성
            PathBuilder<Object> path = entityPath.get(property);

            return new OrderSpecifier(direction, path);
        }

        return null;
    }
}
