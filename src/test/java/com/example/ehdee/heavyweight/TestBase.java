package com.example.ehdee.heavyweight;

import com.example.ehdee.heavyweight.model.Reign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TestBase {
    protected Date buildDate(int year, int month, int day) {
        return new DateTime().withDate(year, month, day).withTimeAtStartOfDay().toDate();
    }

    protected List<Reign> buildValidReigns() {
        List<Reign> expectedReigns = new ArrayList<>();
        expectedReigns.add(new Reign.Builder().withChampion("Champ").build());
        return expectedReigns;
    }

    protected String buildValidReignsString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(buildValidReigns());
    }
}
