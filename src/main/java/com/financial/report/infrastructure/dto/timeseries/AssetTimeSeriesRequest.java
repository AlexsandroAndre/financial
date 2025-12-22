package com.financial.report.infrastructure.dto.timeseries;

import com.financial.report.core.enums.DataSource;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AssetTimeSeriesRequest {

    private String symbol;
    private DataSource dataSource;
    private List<PricePointRequest> points;

    @Data
    public static class PricePointRequest {
        private String symbol;
        private LocalDate date;
        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Double adjustedClose;
        private Long volume;
        private DataSource dataSource;
    }
}
