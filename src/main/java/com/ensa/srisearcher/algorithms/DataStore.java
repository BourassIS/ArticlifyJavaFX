package com.ensa.srisearcher.algorithms;

import com.ensa.srisearcher.models.IndexedDocument;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
public class DataStore implements Serializable {
    @Serial
    private static final long serialVersionUID = 6302486780056322048L;

    public Set<String> urls = new HashSet<>();
    public InvertedIndex invertedIndex=new InvertedIndex();
    public Integer docId=0;
    public Map<String, Set<Integer>> index=new HashMap<>();
    public Map<Integer, String> mapsDocIdsToUrls = new HashMap<>();
    public Map<String, Map<String, List<String>>> scrapedData = new HashMap<>();

    public void incrementDocId() {
        docId++;
    }

    @Override
    public String toString() {
        return "DataStore{" +
                "urls=" + urls +
                ", invertedIndex=" + invertedIndex +
                ", docId=" + docId +
                ", index=" + index +
                ", mapsDocIdsToUrls=" + mapsDocIdsToUrls +
                ", scrapedData=" + scrapedData +
                '}';
    }
}
