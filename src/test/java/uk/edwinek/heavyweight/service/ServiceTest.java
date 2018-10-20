package uk.edwinek.heavyweight.service;

import org.hamcrest.CoreMatchers;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.edwinek.heavyweight.TestBase;
import uk.edwinek.heavyweight.config.ServiceTestConfig;
import uk.edwinek.heavyweight.exception.HeavyweightServiceDateException;
import uk.edwinek.heavyweight.persistence.ReignRepository;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
class ServiceTest extends TestBase {

    @Autowired
    private Service service;

    @Autowired
    private ReignRepository reignRepository;

    @BeforeEach
    void setup() {
        when(reignRepository.getEarliestReignDate()).thenReturn(buildDate(1885, 8, 29));
        service.performETL();
    }

    @Test
    void should_throw_exception_for_date_being_in_future() {

        boolean exceptionThrown = false;
        String tomorrowDateString = ISODateTimeFormat.date().print(new DateTime().plusDays(1).withTimeAtStartOfDay());

        try {
            service.getByDate(tomorrowDateString);
        } catch (HeavyweightServiceDateException e) {
            if (e.getMessage().contains(tomorrowDateString)) {
                exceptionThrown = true;
            }
        }

        assertThat("Check that exception thrown for date being in future.", exceptionThrown, equalTo(true));

        Mockito.verify(reignRepository, times(1)).getEarliestReignDate();
        Mockito.verify(reignRepository, times(0)).queryReignByDate(any(Date.class));

    }

    @Test
    void should_throw_exception_for_date_being_too_old() {

        boolean exceptionThrown = false;
        String queryDateString = "1885-8-28";

        try {
            service.getByDate(queryDateString);
        } catch (HeavyweightServiceDateException e) {
            if (e.getMessage().contains(queryDateString)) {
                exceptionThrown = true;
            }
        }

        assertThat("Check that exception thrown for date being in future.", exceptionThrown, equalTo(true));

        Mockito.verify(reignRepository, times(1)).getEarliestReignDate();
        Mockito.verify(reignRepository, times(0)).queryReignByDate(any(Date.class));
    }

    @Test
    void should_throw_exception_for_invalid_date() {

        boolean exceptionThrown = false;
        String queryDateString = "banana";

        try {
            service.getByDate(queryDateString);
        } catch (HeavyweightServiceDateException e) {
            if (e.getMessage().contains(queryDateString)) {
                exceptionThrown = true;
            }
        }

        assertThat("Check that exception thrown for invalid date.", exceptionThrown, equalTo(true));

        Mockito.verify(reignRepository, times(0)).queryReignByDate(any(Date.class));

    }

    @Test
    void should_return_expected_reigns_for_valid_date() {

        String queryDateString = "2015-1-1";
        Date date = ISODateTimeFormat.date().parseDateTime(queryDateString).toDate();

        Mockito.when(reignRepository.queryReignByDate(date)).thenReturn(buildValidReigns());

        assertThat("Expected reigns returns for valid date.", service.getByDate(queryDateString),
                equalTo(buildValidHeavyweightResponse()));

        Mockito.verify(reignRepository, times(1)).queryReignByDate(date);
    }

    @Test
    void should_return_expected_reigns_for_valid_minimum_boundary_date() {

        String queryDateString = "1885-8-29";
        Date date = ISODateTimeFormat.date().parseDateTime(queryDateString).toDate();

        Mockito.when(reignRepository.queryReignByDate(date)).thenReturn(buildValidReigns());

        assertThat("Expected reigns returns for valid earliest date.", service.getByDate(queryDateString),
                equalTo(buildValidHeavyweightResponse()));

        Mockito.verify(reignRepository, times(1)).queryReignByDate(date);
    }

    @Test
    void should_return_expected_reigns_for_valid_today_date() {

        DateTime todayDateTime = new DateTime().withTimeAtStartOfDay();
        String todayDateTimeString = ISODateTimeFormat.date().print(todayDateTime);

        Mockito.when(reignRepository.queryReignByDate(todayDateTime.toDate())).thenReturn(buildValidReigns());

        assertThat("Expected reigns returns for valid today date.", service.getByDate(todayDateTimeString),
                CoreMatchers.equalTo(buildValidHeavyweightResponse()));

        Mockito.verify(reignRepository, times(1)).queryReignByDate(todayDateTime.toDate());
    }

    @AfterEach
    void tearDown() {
        Mockito.verify(reignRepository, times(1)).getEarliestReignDate();
        reset(reignRepository);
    }


}
