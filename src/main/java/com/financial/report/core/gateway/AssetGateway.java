package com.financial.report.core.gateway;

import com.financial.report.core.entities.Asset;
import com.financial.report.core.enums.Exchange;
import com.financial.report.core.helper.filter.AssetFilter;
import com.financial.report.core.helper.page.Page;
import com.financial.report.core.helper.page.PageRequest;

import java.util.Optional;

public interface AssetGateway {

    Asset save(Asset asset);
    Optional<Asset> findBySymbolAndExchange(String string, Exchange exchange);
    Page<Asset> findAll(PageRequest pageRequest, AssetFilter assetFilter);
}
