package com.dh.catalogservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDTO {
    private String id;
    private Integer seasonNumber;
    private List<ChapterDTO> chapters;
}
