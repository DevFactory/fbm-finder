package com.fbm.finder.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fbm.finder.domain.FacebookMessage;

public class DataExtractor {

  private static List<Element> messages = new ArrayList<Element>();
  private static List<Element> users = new ArrayList<Element>();
  private static List<Element> metadata = new ArrayList<Element>();

  public static List<FacebookMessage> extractData(File messageArchive) throws IOException {
    Document html = Jsoup.parse(messageArchive, "UTF-8");
    extractHTMLElements(html);
    if (validExtraction()) {
      return messageCollection();
    }
    return Collections.emptyList();
  }

  private static List<FacebookMessage> messageCollection() {
    List<FacebookMessage> facebookMessages = new ArrayList<FacebookMessage>();
    for (int i = 0; i < messages.size(); i++) {
      facebookMessages.add(
          new FacebookMessage(users.get(i).text(), metadata.get(i).text(), messages.get(i).text()));
    }
    return facebookMessages;
  }

  private static void extractHTMLElements(Document html) {
    System.out.println("Extracting HTML elements with jsoup...");
    messages = html.getElementsByTag("p");
    users = html.getElementsByClass("user");
    metadata = html.getElementsByClass("meta");
  }

  private static boolean validExtraction() {
    return (messages.size() == users.size() && users.size() == metadata.size());
  }

}
