package com.dh.catalogservice.api.services;

import com.dh.catalogservice.api.services.feignClients.MovieClientService;
import com.dh.catalogservice.api.services.feignClients.SerieClientService;
import com.dh.catalogservice.domain.models.Catalog;
import com.dh.catalogservice.domain.repositories.CatalogRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.collection.Array;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private CatalogRepository catalogRepository;
    private MovieClientService movieClientService;
    private SerieClientService serieClientService;
    private String genre;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository, MovieClientService movieClientService, SerieClientService serieClientService){
        this.catalogRepository = catalogRepository;
        this.movieClientService = movieClientService;
        this.serieClientService = serieClientService;
    }

    @CircuitBreaker(name="catalog", fallbackMethod = "findCatalogByGenreFallback")
    public Catalog findCatalogByGenre(String genre) throws Exception {
        Optional<Catalog> foundCatalog = this.catalogRepository.findByGenre(genre);
        if(foundCatalog.isPresent())
            return foundCatalog.get();
        this.genre = genre;
        throw new Exception("Results not found");
    }

    public Catalog findCatalogByGenreFallback(CallNotPermittedException exception){
        System.out.println("CircuitBreaker opened");

        Catalog catalog = new Catalog();
        catalog.setId("Ups");
        catalog.setGenre(this.genre);
        catalog.setMovies(new ArrayList<>());
        catalog.setSeries(new ArrayList<>());
        return catalog;
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public void consumeMovieMessage(String message){
        System.out.println("Genre movie update: " + message);

        Optional<Catalog> foundCatalog = this.catalogRepository.findByGenre(message);

        if(foundCatalog.isPresent()){
            foundCatalog.get().setMovies(this.movieClientService.getMoviesByGenre(message));
            this.catalogRepository.save(foundCatalog.get());
        }else{
            Catalog catalog = new Catalog();
            catalog.setGenre(message);
            catalog.setMovies(this.movieClientService.getMoviesByGenre(message));
            this.catalogRepository.save(catalog);
        }
    }

    @RabbitListener(queues = "${queue.serie.name}")
    public void consumeSerieMessage(String message){
        System.out.println("Genre serie update: " + message);

        Optional<Catalog> foundCatalog = this.catalogRepository.findByGenre(message);

        if(foundCatalog.isPresent()){
            foundCatalog.get().setSeries(this.serieClientService.getMoviesByGenre(message));
            this.catalogRepository.save(foundCatalog.get());
        }else{
            Catalog catalog = new Catalog();
            catalog.setGenre(message);
            catalog.setSeries(this.serieClientService.getMoviesByGenre(message));
            this.catalogRepository.save(catalog);
        }
    }

}
