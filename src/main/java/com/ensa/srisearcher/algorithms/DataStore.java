package com.ensa.srisearcher.algorithms;

import com.ensa.srisearcher.models.IndexedDocument;
import com.ensa.srisearcher.utils.Converter;
import com.ensa.srisearcher.utils.serializables.SerializableHashMap;
import com.ensa.srisearcher.utils.serializables.SerializableHashSet;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
public class DataStore implements Serializable {
    @Serial
    private static final long serialVersionUID = 6302486780056322048L;

    public SerializableHashSet<String> urls = new SerializableHashSet<>();
    public Integer docId=0;
    public HashMap<String, SerializableHashSet<Integer>> index=new SerializableHashMap<>();
    public HashMap<Integer, String> mapsDocIdsToUrls = new SerializableHashMap<>();
    public HashMap<String, HashMap<String, List<String>>> scrapedData = new SerializableHashMap<>();

    public void incrementDocId() {
        DataStore store = Converter.getDataStore();
        store.docId++;
        System.out.println("Incremented doc id: " + store.docId);
        Converter.update(store);
    }

    @Override
    public String toString() {
        return "DataStore{" +
                "urls=" + urls +
                ", docId=" + docId +
                ", index=" + index +
                ", mapsDocIdsToUrls=" + mapsDocIdsToUrls +
                ", scrapedData=" + scrapedData +
                '}';
    }

    public static String getSnippetFromUrl(String url) {
        Map<String, List<String>> snippets = Converter.getDataStore().getScrapedData().getOrDefault(url, new HashMap<>());
        StringBuilder snippet = new StringBuilder();
        System.out.println(snippets);
        int counter=5;
        for (Map.Entry<String, List<String>> entry : snippets.entrySet()) {
            if(List.of("p", "span", "a").contains(entry.getKey()))continue;

            if(counter<=0)break;
            for(String s : entry.getValue()){
                if(counter<=0)break;
                snippet.append(s).append(" ");
                counter--;
            }
            counter--;
        }
        snippet.append("...");
        return snippet.toString();
    }
}
