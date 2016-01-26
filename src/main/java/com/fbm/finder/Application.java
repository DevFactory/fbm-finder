package com.fbm.finder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fbm.finder.domain.FacebookMessage;
import com.fbm.finder.parse.DataExtractor;

public class Application {

  public static void main(String[] args) throws IOException {

    File input = new File("src/main/resources/messages.htm");

    if (input.exists()) {
      List<FacebookMessage> messages = DataExtractor.extractData(input);
      if (!messages.isEmpty()) {
        System.exit(1);
      }
    }

    System.out.println("Place messages.htm in fbm-finder/src/main/resources/");

  }

}
