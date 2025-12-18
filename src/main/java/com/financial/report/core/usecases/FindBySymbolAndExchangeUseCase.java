package com.financial.report.core.usecases;

import com.financial.report.core.entities.Asset;
import com.financial.report.core.enums.Exchange;
import com.financial.report.core.gateway.AssetGateway;

import java.util.Optional;

public class FindBySymbolAndExchangeUseCase {

    private final AssetGateway assetGateway;

    public FindBySymbolAndExchangeUseCase(AssetGateway assetGateway){
        this.assetGateway = assetGateway;
    }

    public Optional<Asset> execute(String string, Exchange exchange) {
        return assetGateway.findBySymbolAndExchange(string, exchange);
    }
}
