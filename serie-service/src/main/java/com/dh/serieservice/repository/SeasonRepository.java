package com.dh.serieservice.repository;

import com.dh.serieservice.entity.Season;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends MongoRepository<Season, String> {
}
