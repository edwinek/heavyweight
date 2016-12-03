package uk.edwinek.heavyweight;

import uk.edwinek.heavyweight.model.HeavyweightResponse;
import uk.edwinek.heavyweight.model.Reign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TestBase {
    protected Date buildDate(int year, int month, int day) {
        return new DateTime().withDate(year, month, day).withTimeAtStartOfDay().toLocalDateTime().toDate();
    }

    protected HeavyweightResponse buildValidHeavyweightResponse() {
        return new HeavyweightResponse.Builder().withReigns(buildValidReigns()).build();
    }

    protected List<Reign> buildValidReigns() {
        List<Reign> expectedReigns = new ArrayList<>();
        expectedReigns.add(new Reign.Builder().withChampion("Champ").build());
        return expectedReigns;
    }

    protected String buildValidHeavyweightResponseString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(buildValidHeavyweightResponse());
    }
}
