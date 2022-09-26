package com.dh.movieservice.service;

import com.dh.movieservice.entity.Movie;
import com.dh.movieservice.repository.MovieRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private RabbitTemplate rabbitTemplate;
    @Value("${queue.movie.name}")
    private String movieQueue;

    @Autowired
    public MovieService(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Movie saveMovie(Movie movie){
        this.rabbitTemplate.convertAndSend(this.movieQueue, movie.getGenre());
        return this.movieRepository.save(movie);
    }

    public List<Movie> findByGenre(String genre){
        return this.movieRepository.findByGenre(genre);
    }

}
