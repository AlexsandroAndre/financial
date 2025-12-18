package com.financial.report.core.usecases;

import com.financial.report.core.entities.Asset;
import com.financial.report.core.gateway.AssetGateway;
import com.financial.report.core.helper.filter.AssetFilter;
import com.financial.report.core.helper.page.Page;
import com.financial.report.core.helper.page.PageRequest;

public class FindAssetsUseCase {

    private final AssetGateway assetGateway;

    public FindAssetsUseCase(AssetGateway assetGateway){
        this.assetGateway = assetGateway;
    }

    public Page<Asset> execute(PageRequest pageRequest, AssetFilter filter) {
        return assetGateway.findAll(pageRequest, filter);
    }
}
