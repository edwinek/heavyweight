package uk.edwinek.heavyweight.parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uk.edwinek.heavyweight.model.Reign;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserImpl implements Parser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Document doc;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    @Override
    public List<Reign> fromUrl(String url) {

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("There was as issue connecting to url: " + e.getMessage());
        }
        logger.info("Retrieved list of Reigns from URL {}.", url);
        return fromJsoupDocument(doc);
    }

    @Override
    public List<Reign> fromFile(String fileName) {

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        try {
            doc = Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("There was as issue parsing file: " + e.getMessage());
        }
        return fromJsoupDocument(doc);
    }

    private List<Reign> fromJsoupDocument(Document doc) {

        List<Reign> reigns = new ArrayList<>();
        Element table = doc.select(".wikitable").get(0);
        int i = 0;
        for (Element rowFromTable : table.select("tr")) {
            Elements columns = rowFromTable.select("td");
            if (columns.size() > 1) {
                i++;

                String nationality = extract(columns.get(1));
                String champion = extract(columns.get(0));
                String recognition = extract(columns.get(2));
                String reignBeganString = extract(columns.get(3));
                String reignEndedString = extract(columns.get(4));

                LocalDate reignBegan;
                LocalDate reignEnded;

                try {
                    reignBegan = formatter.parse(reignBeganString, LocalDate::from);
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Unable to parse reign began date, \"" + reignBeganString + "\": " + e.getMessage());
                }

                if (columns.get(4).text().equalsIgnoreCase("Present")) {
                    reignEnded = null;
                } else {
                    try {
                        reignEnded = formatter.parse(reignEndedString, LocalDate::from);
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("Unable to parse reign ended date, \"" + reignEndedString + "\": " + e.getMessage());
                    }
                }
                Reign reign = new Reign(champion, String.valueOf(i), nationality, recognition, reignBegan, reignEnded);
                reigns.add(reign);
            }

        }

        return reigns;

    }

    private String extract(Element element) {
        return StringUtils.removeEnd(element.text(), element.select("sup").text());
    }
}