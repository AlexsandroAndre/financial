package com.financial.report.core.usecases;

import com.financial.report.core.dto.asset.CreateOrUpdateAssetRequest;
import com.financial.report.core.entities.Asset;
import com.financial.report.core.gateway.AssetGateway;

import java.time.Instant;
import java.util.Optional;

public class CreateOrUpdateAssetUseCase {

    private final AssetGateway assetGateway;

    public CreateOrUpdateAssetUseCase(AssetGateway assetGateway) {
        this.assetGateway = assetGateway;
    }

    public Asset execute(CreateOrUpdateAssetRequest request) {
        Optional<Asset> existingOpt =
                assetGateway.findBySymbolAndExchange(request.symbol(), request.exchange());

        if (existingOpt.isPresent()) {

            Asset updated = Asset.builder()
                    .name(request.name())
                    .type(request.type())
                    .currency(request.currency())
                    .country(request.country())
                    .sector(request.sector())
                    .industry(request.industry())
                    .isinCode(request.isinCode())
                    .cusipCode(request.cusipCode())
                    .dataSource(request.dataSource())
                    .active(true)
                    .updatedAt(Instant.now())
                    .build();

            return assetGateway.save(updated);
        }

        Asset newAsset = Asset.builder()
                .symbol(request.symbol())
                .name(request.name())
                .type(request.type())
                .currency(request.currency())
                .country(request.country())
                .sector(request.sector())
                .industry(request.industry())
                .isinCode(request.isinCode())
                .cusipCode(request.cusipCode())
                .dataSource(request.dataSource())
                .active(true)
                .updatedAt(Instant.now())
                .exchange(request.exchange())
                .build();


        return assetGateway.save(newAsset);
    }
}
