package com.financial.report.infrastructure.config;

import com.financial.report.core.gateway.AssetGateway;
import com.financial.report.core.usecases.FindAssetsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssetUseCaseConfig {

    @Bean
    public FindAssetsUseCase findAssetsUseCase(AssetGateway assetGateway) {
        return new FindAssetsUseCase(assetGateway);
    }

}
