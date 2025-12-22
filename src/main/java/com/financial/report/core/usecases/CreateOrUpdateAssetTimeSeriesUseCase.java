package com.financial.report.core.usecases;

import com.financial.report.core.entities.AssetTimeSeries;
import com.financial.report.core.gateway.AssetTimeSeriesGateway;

public class CreateOrUpdateAssetTimeSeriesUseCase {

    private final AssetTimeSeriesGateway assetTimeSeriesGateway;

    public CreateOrUpdateAssetTimeSeriesUseCase(AssetTimeSeriesGateway assetTimeSeriesGateway){
        this.assetTimeSeriesGateway = assetTimeSeriesGateway;
    }

    public void execute(AssetTimeSeries timeSeries) {
        assetTimeSeriesGateway.save(timeSeries);
    }
}
