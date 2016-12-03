package uk.edwinek.heavyweight.service;


import uk.edwinek.heavyweight.model.HeavyweightResponse;

public interface Service {

    void performETL();

    HeavyweightResponse getByDate(String isoDateString);

}
