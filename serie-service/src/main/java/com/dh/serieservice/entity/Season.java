package com.dh.serieservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Id
    private String id;
    private int seasonNumber;
    @DBRef
    private List<Chapter> chapters;
}
