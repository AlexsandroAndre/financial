package com.financial.report.infrastructure.mapper;

import com.financial.report.core.entities.AssetPricePoint;
import com.financial.report.core.entities.AssetTimeSeries;
import com.financial.report.infrastructure.dto.timeseries.AssetTimeSeriesRequest;
import org.springframework.stereotype.Component;

@Component
public class AssetTimeSeriesMapper {

    public AssetTimeSeries toDomain(AssetTimeSeriesRequest dto, String symbolFromPath) {

        String effectiveSymbol = symbolFromPath != null ? symbolFromPath : dto.getSymbol();

        return AssetTimeSeries.builder()
                .assetId(null) // será resolvido/associado na camada de persistência
                .symbol(effectiveSymbol)
                .dataSource(dto.getDataSource())
                .points(dto.getPoints() != null
                        ? dto.getPoints().stream()
                        .map(p -> AssetPricePoint.builder()
                                .id(null)
                                .assetId(null)
                                .date(p.getDate())
                                .open(p.getOpen())
                                .high(p.getHigh())
                                .low(p.getLow())
                                .close(p.getClose())
                                .adjustedClose(p.getAdjustedClose())
                                .volume(p.getVolume())
                                .dataSource(p.getDataSource())
                                .build()
                        )
                        .toList()
                        : null
                )
                .build();
    }
}
