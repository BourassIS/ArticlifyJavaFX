package com.ensa.srisearcher.algorithms;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.ensa.srisearcher.models.IndexedDocument;
import com.ensa.srisearcher.utils.Converter;
import com.ensa.srisearcher.utils.serializables.SerializableHashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperAlgo implements Serializable {


    public Map<String, List<String>> pageScraper(String url) {
        DataStore dataStore= Converter.getDataStore();

        SerializableHashMap<String, List<String>> result = new SerializableHashMap<>();

        try {
            // Send an HTTP request to the provided URL and parse the HTML content
            Document document = Jsoup.connect(url).get();

            // Extract text content from different HTML tags
            extractTagContent(document, "h1", result);
            extractTagContent(document, "h2", result);
            extractTagContent(document, "h3", result);
            extractTagContent(document, "h4", result);
            extractTagContent(document, "h5", result);
            extractTagContent(document, "h6", result);
            extractTagContent(document, "p", result);
            extractTagContent(document, "span", result);
            extractTagContent(document, "a", result);
            dataStore.scrapedData.put(url, result);
            List<String> concatenatedList = result.entrySet().stream()
                    .flatMap(entry -> entry.getValue().stream()
                            .flatMap(str -> Arrays.stream(str.split("\\s+")))) // split each string into words
                    .collect(Collectors.toList());
            DataStore st=Converter.addDocument(dataStore.getDocId(), concatenatedList);
            st.mapsDocIdsToUrls.put(dataStore.getDocId(), url);
            st.incrementDocId();

            Converter.update(st);
            return result;
        } catch (Exception e) {
            result.put("error", List.of("Error: " + e.getMessage()));
            return result;
        }
    }

    private void extractTagContent(Document document, String tagName, Map<String, List<String>> result) {
        Elements elements = document.getElementsByTag(tagName);
        List<String> contentList = new ArrayList<>();

        for (Element element : elements) {
            if(!element.text().isEmpty())contentList.add(element.text());
        }

        result.put(tagName, contentList);
    }
}
