package uk.edwinek.heavyweight.parser;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.edwinek.heavyweight.TestBase;
import uk.edwinek.heavyweight.config.ParserTestConfig;
import uk.edwinek.heavyweight.model.Reign;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ParserTestConfig.class})
class ParserTest extends TestBase {

    private static final String REIGNS_URL = "https://en.wikipedia.org/w/index.php?title=List_of_world_heavyweight_boxing_champions&oldid=933454562";

    @Autowired
    private Parser parser;

    @Test
    void should_return_expected_reigns_from_file() {

        List<Reign> expectedReigns = new ArrayList<>();
        expectedReigns.add(
                new Reign
                        .Builder()
                        .withChampion("John L. Sullivan")
                        .withRecognition("Universal")
                        .withNationality("American")
                        .withId("1")
                        .withReignBegan(LocalDate.of(1885, 8, 29))
                        .withReignEnded(LocalDate.of(1892, 9, 7))
                        .build());
        expectedReigns.add(
                new Reign
                        .Builder()
                        .withChampion("James J. Corbett")
                        .withRecognition("Universal")
                        .withNationality("American")
                        .withId("2")
                        .withReignBegan(LocalDate.of(1892, 9, 7))
                        .withReignEnded(LocalDate.of(1897, 3, 17))
                        .build());

        assertThat("Check that expected reigns read from file.", parser.fromFile("TestList.html"),
                equalTo(expectedReigns));
    }

    @Test
    @Tag("Integration")
    void should_successfully_parse_from_web() {
        assertThat("Check that reigns have been imported from URL", parser.fromUrl(REIGNS_URL), is(not(empty())));
    }

}
