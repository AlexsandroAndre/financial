package com.financial.report.core.config;

import com.financial.report.core.gateway.AssetGateway;
import com.financial.report.core.usecases.CreateOrUpdateAssetUseCase;
import com.financial.report.core.usecases.FindBySymbolAndExchangeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateOrUpdateAssetUseCase createOrUpdateAssetUseCase(AssetGateway assetGateway) {
        return new CreateOrUpdateAssetUseCase(assetGateway);
    }

    @Bean
    public FindBySymbolAndExchangeUseCase findBySymbolAndExchangeUseCase(AssetGateway assetGateway) {
        return new FindBySymbolAndExchangeUseCase(assetGateway);
    }
}
