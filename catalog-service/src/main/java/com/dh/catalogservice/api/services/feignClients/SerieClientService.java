package com.dh.catalogservice.api.services.feignClients;

import com.dh.catalogservice.domain.models.SerieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SerieClientService {

    @GetMapping("/series/{genre}")
    List<SerieDTO> getMoviesByGenre(@PathVariable String genre);

}
