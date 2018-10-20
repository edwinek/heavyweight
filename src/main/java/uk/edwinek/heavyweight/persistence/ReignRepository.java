package uk.edwinek.heavyweight.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.edwinek.heavyweight.model.Reign;

public interface ReignRepository extends MongoRepository<Reign, String>, ReignRepositoryCustom {
}
