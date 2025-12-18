package com.financial.report.core.dto.asset;

import com.financial.report.core.enums.*;

public record CreateOrUpdateAssetRequest(
        String symbol,
        String name,
        AssetType type,
        Exchange exchange,
        Currency currency,
        Country country,
        Sector sector,
        Industry industry,
        String isinCode,
        String cusipCode,
        DataSource dataSource
) {
}
