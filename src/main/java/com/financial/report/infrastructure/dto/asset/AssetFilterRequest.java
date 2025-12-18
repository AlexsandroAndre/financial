package com.financial.report.infrastructure.dto.asset;


import com.financial.report.core.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AssetFilterRequest", description = "Filtros opcionais para busca de ativos")
public class AssetFilterRequest {

    @Schema(description = "Símbolo do ativo", example = "AAPL")
    private String symbol;

    @Schema(description = "Nome do ativo (busca parcial)", example = "Apple")
    private String name;

    @Schema(description = "Tipo do ativo", example = "STOCK")
    private AssetType type;

    @Schema(description = "Bolsa de negociação", example = "NASDAQ")
    private Exchange exchange;

    @Schema(description = "Moeda", example = "USD")
    private Currency currency;

    @Schema(description = "País", example = "USA")
    private Country country;

    @Schema(description = "Setor econômico", example = "TECHNOLOGY")
    private Sector sector;

    @Schema(description = "Indústria", example = "SOFTWARE")
    private Industry industry;

    @Schema(description = "Fonte de dados", example = "ALPHA_VANTAGE")
    private DataSource dataSource;

    @Schema(description = "Ativo ou inativo", example = "true")
    private Boolean active;
}
