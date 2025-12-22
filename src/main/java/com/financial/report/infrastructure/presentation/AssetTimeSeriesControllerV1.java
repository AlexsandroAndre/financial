package com.financial.report.infrastructure.presentation;

import com.financial.report.core.entities.AssetTimeSeries;
import com.financial.report.core.usecases.CreateOrUpdateAssetTimeSeriesUseCase;
import com.financial.report.infrastructure.dto.timeseries.AssetTimeSeriesRequest;
import com.financial.report.infrastructure.mapper.AssetTimeSeriesMapper;
import com.financial.report.infrastructure.presentation.version.ApiVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiVersion("v1")
@RestController
@RequestMapping("/assets")
public class AssetTimeSeriesControllerV1 extends BaseApiController{

    private static final Logger log = LoggerFactory.getLogger(AssetTimeSeriesControllerV1.class);

    private final CreateOrUpdateAssetTimeSeriesUseCase createOrUpdateAssetTimeSeriesUseCase;
    private final AssetTimeSeriesMapper mapper;

    public AssetTimeSeriesControllerV1(CreateOrUpdateAssetTimeSeriesUseCase createOrUpdateAssetTimeSeriesUseCase,
                                       AssetTimeSeriesMapper mapper) {
        this.createOrUpdateAssetTimeSeriesUseCase = createOrUpdateAssetTimeSeriesUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/{symbol}/timeseries")
    public ResponseEntity<Void> createOrUpdateTimeSeries(
            @PathVariable String symbol,
            @RequestBody AssetTimeSeriesRequest body
    ) {
        log.info("Received request to create/update time series: symbol={}", symbol);

        // opcional: validar consistência do símbolo
        if (body.getSymbol() != null && !body.getSymbol().equalsIgnoreCase(symbol)) {
            log.warn("Symbol in path ({}) is different from body ({}), using path", symbol, body.getSymbol());
        }

        AssetTimeSeries timeSeries = mapper.toDomain(body, symbol);

        createOrUpdateAssetTimeSeriesUseCase.execute(timeSeries);

        log.info("Time series successfully created/updated: symbol={}, points={}",
                symbol,
                timeSeries.getPoints() != null ? timeSeries.getPoints().size() : 0);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
