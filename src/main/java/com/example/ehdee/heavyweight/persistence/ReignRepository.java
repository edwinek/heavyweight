package com.example.ehdee.heavyweight.persistence;

import com.example.ehdee.heavyweight.model.Reign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReignRepository extends MongoRepository<Reign, String>, ReignRepositoryCustom {}
