package com.example.ehdee.heavyweight.web;

import com.example.ehdee.heavyweight.model.Reign;
import com.example.ehdee.heavyweight.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private Service service;

    @PostConstruct
    public void init() {
        service.performETL();
    }

    @RequestMapping(value = "update", method = RequestMethod.GET, produces = "application/json")
    public RestResponse update() {
        service.performETL();
        return new RestResponse(HttpStatus.OK, "Database updated successfully.");
    }

    @RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
    public List<Reign> get(@RequestParam(value="date") String date) {
        return service.getByDate(date);
    }

}
