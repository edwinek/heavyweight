package uk.edwinek.heavyweight.parser;

import uk.edwinek.heavyweight.model.Reign;

import java.util.List;

public interface Parser {

    List<Reign> fromUrl(String url);

    List<Reign> fromFile(String fileName);

}

