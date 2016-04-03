package com.example.ehdee.heavyweight.service;

import com.example.ehdee.heavyweight.model.HeavyweightResponse;

public interface Service {

    void performETL();

    HeavyweightResponse getByDate(String isoDateString);

}
