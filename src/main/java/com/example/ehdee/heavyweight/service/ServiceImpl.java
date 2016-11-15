package com.example.ehdee.heavyweight.service;

import com.example.ehdee.heavyweight.exception.HeavyweightServiceDateException;
import com.example.ehdee.heavyweight.model.HeavyweightResponse;
import com.example.ehdee.heavyweight.parser.Parser;
import com.example.ehdee.heavyweight.persistence.ReignRepository;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ServiceImpl implements Service {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public HeavyweightResponse getByDate(String isoDateString) {

        logger.info("Retrieving Reigns for date {}.", isoDateString);

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
        return new HeavyweightResponse.Builder().withReigns(reignRepository.queryReignByDate(date)).build();
    }

}
