package uk.edwinek.heavyweight.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.edwinek.heavyweight.model.HeavyweightResponse;
import uk.edwinek.heavyweight.service.Service;

import javax.annotation.PostConstruct;

@RestController
public class HomeController {

    @Autowired
    private Service service;

    @PostConstruct
    public void init() {
        service.performETL();
    }

    @RequestMapping(value = "update", method = RequestMethod.GET, produces = "application/json")
    public String update() {
        service.performETL();
        return "Database updated successfully.";
    }

    @RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
    public HeavyweightResponse get(@RequestParam(value = "date") String date) {
        return service.getByDate(date);
    }

}
