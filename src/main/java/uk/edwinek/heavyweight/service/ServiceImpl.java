package uk.edwinek.heavyweight.service;

import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.edwinek.heavyweight.exception.HeavyweightServiceDateException;
import uk.edwinek.heavyweight.model.HeavyweightResponse;
import uk.edwinek.heavyweight.model.Reign;
import uk.edwinek.heavyweight.parser.Parser;
import uk.edwinek.heavyweight.persistence.ReignRepository;

import java.util.Date;
import java.util.List;

@Component
public class ServiceImpl implements Service {

    private static final String REIGNS_URL = "https://en.wikipedia.org/wiki/List_of_heavyweight_boxing_champions";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Parser parser;
    @Autowired
    private ReignRepository reignRepository;
    private Date earliestReign;

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
        } catch (IllegalArgumentException e) {
            throw new HeavyweightServiceDateException("Invalid date specified: [" + isoDateString + "].");
        }

        if (date.after(new Date())) {
            throw new HeavyweightServiceDateException("Future date specified: [" + isoDateString + "].");
        }

        if (date.before(earliestReign)) {
            throw new HeavyweightServiceDateException("Date specified pre-dates all reigns: [" + isoDateString + "].");
        }
        List<Reign> retrievedReigns = reignRepository.queryReignByDate(date);

        logger.info("Successfully retrieved Reigns for date {}, {}:", isoDateString, retrievedReigns);
        return new HeavyweightResponse.Builder().withReigns(retrievedReigns).build();
    }

}
