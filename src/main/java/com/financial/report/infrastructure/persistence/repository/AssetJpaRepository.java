package com.financial.report.infrastructure.persistence.repository;

import com.financial.report.core.enums.Exchange;
import com.financial.report.infrastructure.persistence.entity.AtivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetJpaRepository extends JpaRepository<AtivoEntity, Long> {

    Optional<AtivoEntity> findBySymbolAndExchange(String string, Exchange exchange);
}
