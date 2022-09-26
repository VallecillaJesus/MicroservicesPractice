package com.dh.catalogservice.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Catalog {

    @Id
    private String id;

    private String genre;
    private List<MovieDTO> movies;
    private List<SerieDTO> series;
}
