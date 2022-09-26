package com.dh.catalogservice.api.controllers;


import com.dh.catalogservice.api.services.CatalogService;
import com.dh.catalogservice.domain.models.Catalog;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<Catalog> findCatalogByGenre(@PathVariable("genre") String genre) throws Exception {
        return ResponseEntity.ok(this.catalogService.findCatalogByGenre(genre));
    }

}
