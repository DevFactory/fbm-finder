package com.fbm.finder.lucene;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.fbm.finder.domain.FacebookMessage;

public class MessageIndex {

  private static StandardAnalyzer analyzer;
  private static Directory index;
  private static IndexWriterConfig config;
  private static IndexWriter writer;

  public static void configure(List<FacebookMessage> messages) throws IOException {
    analyzer = new StandardAnalyzer();
    index = new RAMDirectory();
    config = new IndexWriterConfig(analyzer);
    writer = new IndexWriter(index, config);
    addDocuments(writer, messages);
    writer.close();
  }

  private static void addDocuments(IndexWriter writer, List<FacebookMessage> messages)
      throws IOException {
    for (FacebookMessage facebookMessage : messages) {
      Document doc = new Document();
      doc.add(new TextField("message", facebookMessage.getMessage(), Field.Store.YES));
      doc.add(new TextField("user", facebookMessage.getUser(), Field.Store.YES));
      doc.add(new TextField("timestamp", facebookMessage.getMetadata(), Field.Store.YES));
      writer.addDocument(doc);
    }
  }

  public static void search(String term, String criteria, int limit)
      throws ParseException, IOException {
    Query qry = new QueryParser(term, analyzer).parse(criteria);
    IndexReader reader = DirectoryReader.open(index);
    IndexSearcher searcher = new IndexSearcher(reader);
    TopDocs docs = searcher.search(qry, limit);
    ScoreDoc[] hits = docs.scoreDocs;
    for (int i = 0; i < hits.length; ++i) {
      int docId = hits[i].doc;
      Document d = searcher.doc(docId);
      System.out.println((i + 1) + ":\t" + d.get("timestamp") + " " + d.get("user") + ": " + d.get("message"));
    }
    reader.close();
  }

}
