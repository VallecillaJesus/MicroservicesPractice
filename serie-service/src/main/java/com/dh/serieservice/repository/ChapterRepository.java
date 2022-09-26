package com.dh.serieservice.repository;

import com.dh.serieservice.entity.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends MongoRepository<Chapter,String> {
}
