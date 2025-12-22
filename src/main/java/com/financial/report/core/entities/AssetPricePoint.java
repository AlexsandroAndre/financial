package com.financial.report.core.entities;

import com.financial.report.core.enums.DataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetPricePoint {

    private Long id;
    private Long assetId;
    private LocalDate date;

    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double adjustedClose;
    private Long volume;

    private DataSource dataSource;
}
