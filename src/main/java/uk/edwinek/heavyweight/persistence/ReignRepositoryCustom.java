package uk.edwinek.heavyweight.persistence;

import uk.edwinek.heavyweight.model.Reign;

import java.time.LocalDate;
import java.util.List;

public interface ReignRepositoryCustom {
    void loadDB(List<Reign> reigns);

    List<Reign> queryReignByDate(LocalDate date);

    LocalDate getEarliestReignDate();
}
