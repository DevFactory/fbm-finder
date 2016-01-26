package com.fbm.finder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.fbm.finder.domain.FacebookMessage;
import com.fbm.finder.lucene.MessageIndex;
import com.fbm.finder.parse.DataExtractor;

public class Application {

  public static void main(String[] args) throws IOException, ParseException {
    File input = new File("src/main/resources/messages.htm");
    if (input.exists()) {
      List<FacebookMessage> messages = DataExtractor.extractData(input);
      if (!messages.isEmpty()) {
        MessageIndex.configure(messages);
        //MessageIndex.search("message", "league of legends", 5000);
        //MessageIndex.search("timestamp", "2016", 100);
        MessageIndex.search("user", "caleb", 10000);
        System.exit(1);
      }
    }
    System.out.println("Place messages.htm in fbm-finder/src/main/resources/");
  }

}
