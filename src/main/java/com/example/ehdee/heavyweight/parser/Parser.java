package com.example.ehdee.heavyweight.parser;

import com.example.ehdee.heavyweight.model.Reign;
import java.util.List;

public interface Parser {

    List<Reign> fromUrl(String url);

    List<Reign> fromFile(String fileName);

}

