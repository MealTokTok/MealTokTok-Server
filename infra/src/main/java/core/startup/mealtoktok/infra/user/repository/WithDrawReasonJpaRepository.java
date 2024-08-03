package core.startup.mealtoktok.infra.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.user.entity.WithDrawReasonEntity;

public interface WithDrawReasonJpaRepository extends JpaRepository<WithDrawReasonEntity, Long> {}
