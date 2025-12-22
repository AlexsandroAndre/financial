package com.financial.report.core.gateway;

import com.financial.report.core.entities.AssetTimeSeries;

import java.time.LocalDate;
import java.util.Optional;

public interface AssetTimeSeriesGateway {

    void save(AssetTimeSeries timeSeries);
    Optional<AssetTimeSeries> findBySymbolAndDateRange(String symbol, LocalDate startDate, LocalDate endDate);
}
