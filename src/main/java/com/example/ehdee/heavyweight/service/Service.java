package com.example.ehdee.heavyweight.service;

import com.example.ehdee.heavyweight.model.Reign;

import java.util.List;

public interface Service {

    void performETL();

    List<Reign> getByDate(String isoDateString);

}
