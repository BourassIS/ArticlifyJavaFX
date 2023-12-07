package com.ensa.srisearcher.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperAlgo {

    private final Map<String, Map<String, List<String>>> scrapedData = new HashMap<>();
    private final Map<String, String> keywordsToUrls=new HashMap<>();

    public Map<String, List<String>> scrapePage(String url) {
        Map<String, List<String>> result = new HashMap<>();

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
            scrapedData.put(url, result);

            // Do the indexing here

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
