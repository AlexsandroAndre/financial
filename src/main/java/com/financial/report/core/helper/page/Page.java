package com.financial.report.core.helper.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    private List<T> content;

    private int pageNumber;
    private int pageSize;

    private long totalElements;
    private int totalPages;

    private boolean first;
    private boolean last;
    private boolean empty;

    public static <T> Page<T> of(List<T> content,
                                 int pageNumber,
                                 int pageSize,
                                 long totalElements) {

        int totalPages = pageSize == 0
                ? 0
                : (int) Math.ceil((double) totalElements / pageSize);

        return Page.<T>builder()
                .content(content)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .first(pageNumber <= 0)
                .last(totalPages == 0 || pageNumber >= totalPages - 1)
                .empty(content == null || content.isEmpty())
                .build();
    }
}
