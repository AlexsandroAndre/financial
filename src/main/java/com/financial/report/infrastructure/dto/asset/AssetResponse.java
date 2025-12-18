package com.financial.report.infrastructure.dto.asset;

import com.financial.report.core.entities.Asset;
import com.financial.report.core.enums.*;
import lombok.*;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {

    private Long id;
    private String symbol;
    private String name;
    private AssetType type;
    private Exchange exchange;
    private Currency currency;
    private Country country;
    private Sector sector;
    private Industry industry;
    private String isinCode;
    private String cusipCode;
    private DataSource dataSource;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    public static AssetResponse fromDomain(Asset asset) {
        return AssetResponse.builder()
                .id(asset.getId())
                .symbol(asset.getSymbol())
                .name(asset.getName())
                .type(asset.getType())
                .exchange(asset.getExchange())
                .currency(asset.getCurrency())
                .country(asset.getCountry())
                .sector(asset.getSector())
                .industry(asset.getIndustry())
                .isinCode(asset.getIsinCode())
                .cusipCode(asset.getCusipCode())
                .dataSource(asset.getDataSource())
                .active(asset.isActive())
                .createdAt(asset.getCreatedAt())
                .updatedAt(asset.getUpdatedAt())
                .build();
    }
}
