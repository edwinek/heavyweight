package uk.edwinek.heavyweight.persistence;

import uk.edwinek.heavyweight.model.Reign;

import java.util.Date;
import java.util.List;

public interface ReignRepositoryCustom {
    void loadDB(List<Reign> reigns);

    List<Reign> queryReignByDate(Date date);

    Date getEarliestReignDate();
}
