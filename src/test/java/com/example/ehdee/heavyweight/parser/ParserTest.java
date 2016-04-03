package com.example.ehdee.heavyweight.parser;

import com.example.ehdee.heavyweight.TestBase;
import com.example.ehdee.heavyweight.config.ParserTestConfig;
import com.example.ehdee.heavyweight.model.Reign;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ParserTestConfig.class})
public class ParserTest extends TestBase {

    @Autowired
    private Parser parser;

    @Test
    public void should_return_expected_reigns_from_file() {

        List<Reign> expectedReigns = new ArrayList<>();
        expectedReigns.add(
                new Reign
                        .Builder()
                        .withChampion("John L. Sullivan")
                        .withRecognition("Universal")
                        .withNationality("American")
                        .withId("1")
                        .withReignBegan(buildDate(1885, 8, 29))
                        .withReignEnded(buildDate(1892, 9, 7))
                        .build());
        expectedReigns.add(
                new Reign
                        .Builder()
                        .withChampion("James J. Corbett")
                        .withRecognition("Universal")
                        .withNationality("American")
                        .withId("2")
                        .withReignBegan(buildDate(1892, 9, 7))
                        .withReignEnded(buildDate(1897, 3, 17))
                        .build());


        List<Reign> reigns = parser.fromFile("TestList.html");

        Assert.assertThat("Check that expected reigns read from file.", parser.fromFile("TestList.html"), equalTo(expectedReigns));

    }

}
