package uk.edwinek.heavyweight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.edwinek.heavyweight.model.HeavyweightResponse;
import uk.edwinek.heavyweight.model.Reign;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBase {
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
