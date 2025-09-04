package com.rest.dto.response;

import com.rest.entity.Document;
import lombok.*;

import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsResponse {

    private Long id;
    private String name;
    private Long userId;

    public DocumentsResponse(@NotNull Document document) {
        this.id = document.getId();
        this.name = document.getName();
        this.userId = document.getUser().getId();
    }
}
