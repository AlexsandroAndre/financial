package com.financial.report.core.entities;

import com.financial.report.core.enums.DataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetTimeSeries {

    private Long assetId;
    private String symbol;
    private DataSource dataSource;
    private List<AssetPricePoint> points;
}
