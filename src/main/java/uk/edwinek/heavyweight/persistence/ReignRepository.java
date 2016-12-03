package uk.edwinek.heavyweight.persistence;

import uk.edwinek.heavyweight.model.Reign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReignRepository extends MongoRepository<Reign, String>, ReignRepositoryCustom {}
