package com.dh.catalogservice.api.services.feignClients;

import com.dh.catalogservice.domain.models.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClientService {

    @GetMapping("/movie/{genre}")
    List<MovieDTO> getMoviesByGenre(@PathVariable String genre);

}
