package com.dh.catalogservice.domain.repositories;


import com.dh.catalogservice.domain.models.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {
    Optional<Catalog> findByGenre(String genre);
}
