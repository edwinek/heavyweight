package uk.edwinek.heavyweight.persistence;

import uk.edwinek.heavyweight.model.Reign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

public class ReignRepositoryImpl implements ReignRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Date getEarliestReignDate() {
        Query query = new Query().with(new Sort(Sort.Direction.ASC, "reignBegan")).limit(1);
        return mongoOperations.find(query, Reign.class).get(0).getReignBegan();
    }

    @Override
    public void loadDB(List<Reign> reigns) {
        mongoOperations.dropCollection("reign");

        for (Reign reign : reigns) {
            mongoOperations.save(reign);
        }
    }

    @Override
    public List<Reign> queryReignByDate(Date date) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("reignBegan").lte(date),
                new Criteria().orOperator(
                        Criteria.where("reignEnded").gt(date),
                        Criteria.where("reignEnded").is(null)));
        query.addCriteria(criteria);
        return mongoOperations.find(query, Reign.class);
    }

}
