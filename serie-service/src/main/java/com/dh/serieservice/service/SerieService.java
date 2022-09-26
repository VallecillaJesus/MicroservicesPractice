package com.dh.serieservice.service;

import com.dh.serieservice.entity.Chapter;
import com.dh.serieservice.entity.Season;
import com.dh.serieservice.entity.Serie;
import com.dh.serieservice.repository.ChapterRepository;
import com.dh.serieservice.repository.SeasonRepository;
import com.dh.serieservice.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class SerieService {

    @Value("${queue.serie.name}")
    private String serieQueue;
    private SerieRepository serieRepository;
    private SeasonRepository seasonRepository;
    private ChapterRepository chapterRepository;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SerieService(
            SerieRepository serieRepository,
            SeasonRepository seasonRepository,
            ChapterRepository chapterRepository,
            RabbitTemplate rabbitTemplate
    ) {
        this.serieRepository = serieRepository;
        this.seasonRepository = seasonRepository;
        this.chapterRepository = chapterRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Serie> findByGenre(String genre){
        log.info("Bienvenido");
        return this.serieRepository.findByGenre(genre);
    }

    public Serie saveSeries(Serie serie) {
        for(Season season : serie.getSeasons()){
            season.setChapters(
                    chapterRepository.saveAll(season.getChapters())
            );
        }

        serie.setSeasons(
                seasonRepository.saveAll(serie.getSeasons())
        );

        this.rabbitTemplate.convertAndSend(this.serieQueue, serie.getGenre());
        return serieRepository.save(serie);
    }

}
