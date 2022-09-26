package com.dh.serieservice.controller;

import com.dh.serieservice.entity.Serie;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("series")
public class SerieController {

    private SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("{genre}")
    public ResponseEntity<List<Serie>> findByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(this.serieService.findByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<?> saveSeries(@RequestBody Serie series) {
        return ResponseEntity.ok(serieService.saveSeries(series));
    }

}
