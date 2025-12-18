package com.financial.report.infrastructure.presentation;

import com.financial.report.core.entities.Asset;
import com.financial.report.core.enums.Exchange;
import com.financial.report.core.helper.filter.AssetFilter;
import com.financial.report.core.helper.page.PageRequest;
import com.financial.report.core.usecases.CreateOrUpdateAssetUseCase;
import com.financial.report.core.usecases.FindAssetsUseCase;
import com.financial.report.core.usecases.FindBySymbolAndExchangeUseCase;
import com.financial.report.infrastructure.dto.asset.AssetFilterRequest;
import com.financial.report.infrastructure.dto.asset.AssetRequest;
import com.financial.report.infrastructure.dto.asset.AssetResponse;
import com.financial.report.infrastructure.dto.asset.PageResponse;
import com.financial.report.infrastructure.mapper.AssetMapper;
import com.financial.report.infrastructure.presentation.version.ApiVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiVersion("v1")
@RestController
@RequestMapping("/assets")
public class AssetControllerV1 extends BaseApiController{

    private static final Logger log = LoggerFactory.getLogger(AssetControllerV1.class);

    private final FindAssetsUseCase findAssetsUseCase;
    private final FindBySymbolAndExchangeUseCase findBySymbolAndExchangeUseCase;
    private final CreateOrUpdateAssetUseCase createOrUpdateAssetUseCase;
    private final AssetMapper mapper;

    public AssetControllerV1(FindAssetsUseCase findAssetsUseCase,
                             FindBySymbolAndExchangeUseCase findBySymbolAndExchangeUseCase,
                             CreateOrUpdateAssetUseCase createOrUpdateAssetUseCase,
                             AssetMapper assetMapper) {
        this.findAssetsUseCase = findAssetsUseCase;
        this.createOrUpdateAssetUseCase = createOrUpdateAssetUseCase;
        this.mapper = assetMapper;
        this.findBySymbolAndExchangeUseCase = findBySymbolAndExchangeUseCase;
    }

    @PostMapping
    public ResponseEntity<AssetResponse> createOrUpdate(@RequestBody AssetRequest body) {
        log.info("Received request to create or update asset: symbol={}, exchange={}, type={}",
                body.getSymbol(),
                body.getExchange(),
                body.getType());

        try{
            Asset asset = createOrUpdateAssetUseCase.execute(mapper.toAssetSave(body));

            log.info("Asset successfully created/updated: id={}, symbol={}, exchange={}, active={}",
                    asset.getId(),
                    asset.getSymbol(),
                    asset.getExchange(),
                    asset.isActive());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(AssetResponse.fromDomain(asset));
        }catch (Exception e){
            log.error("Error creating/updating asset: symbol={}, exchange={}, error={}",
                    body.getSymbol(),
                    body.getExchange(),
                    e.getMessage(),
                    e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<PageResponse<AssetResponse>> getAssets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") java.lang.String sortBy,
            @RequestParam(defaultValue = "DESC") java.lang.String direction,
            AssetFilterRequest filterRequest
    ) {
        log.info("Get Assets - page={}, size={}, sortBy={}, direction={}", page, size, sortBy, direction);

        try{

            var pageRequest = PageRequest.builder()
                    .page(page)
                    .size(size)
                    .sortBy(sortBy)
                    .build();

            var filter = AssetFilter.builder()
                    .symbol(filterRequest.getSymbol())
                    .name(filterRequest.getName())
                    .type(filterRequest.getType())
                    .exchange(filterRequest.getExchange())
                    .currency(filterRequest.getCurrency())
                    .country(filterRequest.getCountry())
                    .sector(filterRequest.getSector())
                    .industry(filterRequest.getIndustry())
                    .dataSource(filterRequest.getDataSource())
                    .active(filterRequest.getActive())
                    .build();

            var pageResult = findAssetsUseCase.execute(pageRequest, filter);

            var response = PageResponse.fromDomain(
                    pageResult,
                    AssetResponse::fromDomain
            );

            log.info("Assets successfully retrieved - totalElements={}, totalPages={}",
                    pageResult.getTotalElements(),
                    pageResult.getTotalPages());

            return ResponseEntity.ok(response);
        }catch (Exception e){
            log.error("Error ");
            throw e;
        }
    }

    @GetMapping("/{symbol}/exchange/{exchange}")
    public ResponseEntity<Asset> getSymbol(@PathVariable String symbol,
                                           @PathVariable Exchange exchange) {

        log.info("Get Symbol - symbol={}, exchange={}", symbol, exchange);

        Asset asset = findBySymbolAndExchangeUseCase.execute(symbol, exchange)
                .orElseThrow(() -> new RuntimeException("Asset not found for symbol: " + symbol + " and exchange: " + exchange));

        return ResponseEntity.ok(asset);
    }
}
