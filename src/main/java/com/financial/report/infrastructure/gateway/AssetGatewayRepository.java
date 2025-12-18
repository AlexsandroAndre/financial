package com.financial.report.infrastructure.gateway;

import com.financial.report.core.entities.Asset;
import com.financial.report.core.enums.Exchange;
import com.financial.report.core.gateway.AssetGateway;
import com.financial.report.core.helper.filter.AssetFilter;
import com.financial.report.core.helper.page.Page;
import com.financial.report.core.helper.page.PageRequest;
import com.financial.report.infrastructure.mapper.AssetMapper;
import com.financial.report.infrastructure.persistence.entity.AtivoEntity;
import com.financial.report.infrastructure.persistence.repository.AssetJpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssetGatewayRepository implements AssetGateway {

    private final AssetJpaRepository repository;
    private final AssetMapper mapper;

    public AssetGatewayRepository(AssetJpaRepository repository, AssetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Asset save(Asset asset) {
        var obj = repository.save(mapper.toEntity(asset));
        return mapper.toDomain(obj);
    }

    @Override
    public Optional<Asset> findBySymbolAndExchange(String string, Exchange exchange) {
        Optional<AtivoEntity> assetOptional = repository.findBySymbolAndExchange(string, exchange);

        return assetOptional.map(mapper::toDomain);
    }

    @Override
    public Page<Asset> findAll(PageRequest pageRequest, AssetFilter assetFilter) {
        var direction = pageRequest.getDirection() == PageRequest.SortDirection.ASC
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        var springPageRequest = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage(),
                pageRequest.getSize(),
                Sort.by(direction, pageRequest.getSortBy())
        );

        var springPage = repository.findAll(springPageRequest);

        List<Asset> content = springPage.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return Page.of(
                content,
                pageRequest.getPage(),
                pageRequest.getSize(),
                springPage.getTotalElements()
        );
    }

}
