package com.financial.report.core.entities;

import com.financial.report.core.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

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

    @Builder.Default
    private boolean active = true;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Builder.Default
    private Instant updatedAt = Instant.now();

    public Asset deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Asset updateName(String newName) {
        this.name = newName;
        this.updatedAt = Instant.now();
        return this;
    }
}