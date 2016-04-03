package com.example.ehdee.heavyweight.service;

import com.example.ehdee.heavyweight.exception.HeavyweightServiceDateException;
import com.example.ehdee.heavyweight.model.Reign;
import com.example.ehdee.heavyweight.parser.Parser;
import com.example.ehdee.heavyweight.persistence.ReignRepository;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private Parser parser;

    @Autowired
    private ReignRepository reignRepository;

    private Date earliestReign;

    private static final String REIGNS_URL = "https://en.wikipedia.org/wiki/List_of_heavyweight_boxing_champions";

    @Override
    public void performETL() {
        reignRepository.loadDB(parser.fromUrl(REIGNS_URL));
        earliestReign = reignRepository.getEarliestReignDate();
    }

    @Override
    public List<Reign> getByDate(String isoDateString) {

        Date date;

        try {
            date = ISODateTimeFormat.date().parseDateTime(isoDateString).toDate();
        } catch(IllegalArgumentException e) {
            throw new HeavyweightServiceDateException("Invalid date specified: [" + isoDateString + "].");
        }

        if (date.after(new Date())){
            throw new HeavyweightServiceDateException("Future date specified: [" + isoDateString + "].");
        }

        if (date.before(earliestReign)) {
            throw new HeavyweightServiceDateException("Date specified pre-dates all reigns: [" + isoDateString + "].");
        }

        return reignRepository.queryReignByDate(date);
    }

}
