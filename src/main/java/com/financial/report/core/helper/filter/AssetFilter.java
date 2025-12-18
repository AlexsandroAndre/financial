package com.financial.report.core.helper.filter;

import com.financial.report.core.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetFilter {

    private String symbol;
    private String name;

    private AssetType type;
    private Exchange exchange;
    private Currency currency;
    private Country country;
    private Sector sector;
    private Industry industry;
    private DataSource dataSource;

    private Boolean active;
}
