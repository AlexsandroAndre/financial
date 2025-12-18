package com.financial.report.infrastructure.dto.asset;

import com.financial.report.core.helper.page.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PageResponse", description = "Resposta paginada genérica")
public class PageResponse <T>{

    @Schema(description = "Conteúdo da página")
    private List<T> content;

    @Schema(description = "Número da página atual (zero-indexed)", example = "0")
    private int pageNumber;

    @Schema(description = "Tamanho da página", example = "20")
    private int pageSize;

    @Schema(description = "Total de elementos", example = "150")
    private long totalElements;

    @Schema(description = "Total de páginas", example = "8")
    private int totalPages;

    @Schema(description = "É a primeira página?", example = "true")
    private boolean first;

    @Schema(description = "É a última página?", example = "false")
    private boolean last;

    @Schema(description = "Está vazia?", example = "false")
    private boolean empty;

    /**
     * Converte Page<DOMAIN> → PageResponse<DTO>
     */
    public static <D, T> PageResponse<T> fromDomain(Page<D> page, Function<D, T> mapper) {
        List<T> mappedContent = page.getContent()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        return PageResponse.<T>builder()
                .content(mappedContent)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .build();
    }
}
