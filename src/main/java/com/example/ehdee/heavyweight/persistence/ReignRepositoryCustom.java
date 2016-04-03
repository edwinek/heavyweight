package com.example.ehdee.heavyweight.persistence;

import com.example.ehdee.heavyweight.model.Reign;

import java.util.Date;
import java.util.List;

public interface ReignRepositoryCustom {
    void loadDB(List<Reign> reigns);
    List<Reign> queryReignByDate(Date date);
    Date getEarliestReignDate();
}
