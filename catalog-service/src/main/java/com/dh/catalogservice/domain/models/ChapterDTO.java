package com.dh.catalogservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;
}
