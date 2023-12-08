package com.ensa.srisearcher.algorithms;

import com.ensa.srisearcher.models.IndexedDocument;

import java.util.*;

public class DataStore {
    public static InvertedIndex invertedIndex=new InvertedIndex();
    public static Integer docId=0;
    public static Map<String, Set<Integer>> index=new HashMap<>();
    public static Map<Integer, String> mapsDocIdsToUrls = new HashMap<>();
    public static Map<String, Map<String, List<String>>> scrapedData = new HashMap<>();
    static {
        scrapedData.put("https://www.jumia.ma/catalog/?q=knives&sort=rating#catalog-listing", null);
    }
    public static Integer getDocId() {
        return docId;
    }

    public static void incrementDocId() {
        docId++;
    }

}
