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
@Schema(name = "AssetRequest", description = "Payload para criação/atualização de ativo")
public class AssetRequest {
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
}
