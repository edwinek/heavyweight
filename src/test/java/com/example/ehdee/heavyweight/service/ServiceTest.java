package com.example.ehdee.heavyweight.service;


import com.example.ehdee.heavyweight.TestBase;
import com.example.ehdee.heavyweight.config.ServiceTestConfig;
import com.example.ehdee.heavyweight.exception.HeavyweightServiceDateException;
import com.example.ehdee.heavyweight.model.HeavyweightResponse;
import com.example.ehdee.heavyweight.persistence.ReignRepository;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
public class ServiceTest extends TestBase {

    @Autowired
    private Service service;

    @Autowired
    private ReignRepository reignRepository;

    @Before
    public void setup() {
        when(reignRepository.getEarliestReignDate()).thenReturn(buildDate(1885, 8, 29));
        service.performETL();
    }

    @Test
    public void should_throw_exception_for_date_being_in_future() {

        boolean exceptionThrown = false;
        String tomorrowDateString = ISODateTimeFormat.date().print(new DateTime().plusDays(1).withTimeAtStartOfDay());

        try {
            service.getByDate(tomorrowDateString);
        } catch (HeavyweightServiceDateException e) {
            if (e.getMessage().contains(tomorrowDateString)) {
                exceptionThrown = true;
            }
        }

        Assert.assertThat("Check that exception thrown for date being in future.", exceptionThrown, equalTo(true));

        Mockito.verify(reignRepository, times(1)).getEarliestReignDate();
        Mockito.verify(reignRepository, times(0)).queryReignByDate(any(Date.class));

    }

    @Test
    public void should_throw_exception_for_date_being_too_old() {

        boolean exceptionThrown = false;
        String queryDateString = "1885-8-28";

        try {
            service.getByDate(queryDateString);
        } catch (HeavyweightServiceDateException e) {
            if (e.getMessage().contains(queryDateString)) {
                exceptionThrown = true;
            }
        }

        Assert.assertThat("Check that exception thrown for date being in future.", exceptionThrown, equalTo(true));

        Mockito.verify(reignRepository, times(1)).getEarliestReignDate();
        Mockito.verify(reignRepository, times(0)).queryReignByDate(any(Date.class));
    }

    @Test
    public void should_throw_exception_for_invalid_date() {

        boolean exceptionThrown = false;
        String queryDateString = "banana";

        try {
            service.getByDate(queryDateString);
        } catch (HeavyweightServiceDateException e) {
            if (e.getMessage().contains(queryDateString)) {
                exceptionThrown = true;
            }
        }

        Assert.assertThat("Check that exception thrown for invalid date.", exceptionThrown, equalTo(true));

        Mockito.verify(reignRepository, times(0)).queryReignByDate(any(Date.class));

    }

    @Test
    public void should_return_expected_reigns_for_valid_date() {

        String queryDateString = "2015-1-1";
        Date date = ISODateTimeFormat.date().parseDateTime(queryDateString).toDate();

        Mockito.when(reignRepository.queryReignByDate(date)).thenReturn(buildValidReigns());

        Assert.assertThat("Expected reigns returns for valid date.", service.getByDate(queryDateString), equalTo(buildValidHeavyweightResponse()));

        Mockito.verify(reignRepository, times(1)).queryReignByDate(date);
    }

    @Test
    public void should_return_expected_reigns_for_valid_minimum_boundary_date() {

        String queryDateString = "1885-8-29";
        Date date = ISODateTimeFormat.date().parseDateTime(queryDateString).toDate();

        Mockito.when(reignRepository.queryReignByDate(date)).thenReturn(buildValidReigns());

        Assert.assertThat("Expected reigns returns for valid earliest date.", service.getByDate(queryDateString), equalTo(buildValidHeavyweightResponse()));

        Mockito.verify(reignRepository, times(1)).queryReignByDate(date);
    }

    @Test
    public void should_return_expected_reigns_for_valid_today_date() {

        DateTime todayDateTime = new DateTime().withTimeAtStartOfDay();
        String todayDateTimeString = ISODateTimeFormat.date().print(todayDateTime);

        Mockito.when(reignRepository.queryReignByDate(todayDateTime.toDate())).thenReturn(buildValidReigns());

        Assert.assertThat("Expected reigns returns for valid today date.", service.getByDate(todayDateTimeString), equalTo(buildValidHeavyweightResponse()));

        Mockito.verify(reignRepository, times(1)).queryReignByDate(todayDateTime.toDate());
    }

    @After
    public void tearDown() {
        Mockito.verify(reignRepository, times(1)).getEarliestReignDate();
        reset(reignRepository);
    }



}
