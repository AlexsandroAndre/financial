package com.financial.report.infrastructure.mapper;

import com.financial.report.core.dto.asset.CreateOrUpdateAssetRequest;
import com.financial.report.core.entities.Asset;
import com.financial.report.infrastructure.dto.asset.AssetRequest;
import com.financial.report.infrastructure.persistence.entity.AtivoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    private static final Logger logger = LoggerFactory.getLogger(AssetMapper.class);

    public Asset toAsset(AssetRequest request){
        logger.info("AssetMapper to Asset");

        return Asset.builder()
                .symbol(request.getSymbol())
                .name(request.getName())
                .type(request.getType())
                .exchange(request.getExchange())
                .currency(request.getCurrency())
                .country(request.getCountry())
                .sector(request.getSector())
                .industry(request.getIndustry())
                .isinCode(request.getIsinCode())
                .cusipCode(request.getCusipCode())
                .dataSource(request.getDataSource())
                .build();
    }

    public CreateOrUpdateAssetRequest toAssetSave(AssetRequest asset){
        logger.info("AssetMapper to toAssetSave");

        return new CreateOrUpdateAssetRequest(
                asset.getSymbol(),
                asset.getName(),
                asset.getType(),
                asset.getExchange(),
                asset.getCurrency(),
                asset.getCountry(),
                asset.getSector(),
                asset.getIndustry(),
                asset.getIsinCode(),
                asset.getCusipCode(),
                asset.getDataSource()
        );
    }

    public Asset toDomain(AtivoEntity entity){
        logger.info("AssetMapper to toDomain");

        return Asset.builder()
                .symbol(entity.getSymbol())
                .name(entity.getName())
                .type(entity.getType())
                .exchange(entity.getExchange())
                .currency(entity.getCurrency())
                .country(entity.getCountry())
                .sector(entity.getSector())
                .industry(entity.getIndustry())
                .isinCode(entity.getIsinCode())
                .cusipCode(entity.getCusipCode())
                .dataSource(entity.getDataSource())
                .build();
    }

    public AtivoEntity toEntity(Asset domain) {
        logger.info("AssetMapper to toEntity");

        if (domain == null) return null;

        return AtivoEntity.builder()
                .id(domain.getId())
                .symbol(domain.getSymbol() != null ? domain.getSymbol() : null)
                .name(domain.getName())
                .type(domain.getType())
                .exchange(domain.getExchange())
                .currency(domain.getCurrency())
                .country(domain.getCountry())
                .sector(domain.getSector())
                .industry(domain.getIndustry())
                .isinCode(domain.getIsinCode())
                .cusipCode(domain.getCusipCode())
                .dataSource(domain.getDataSource())
                .active(domain.isActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
