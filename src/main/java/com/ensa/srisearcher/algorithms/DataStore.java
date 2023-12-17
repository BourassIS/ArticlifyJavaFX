package com.ensa.srisearcher.algorithms;

import com.ensa.srisearcher.models.IndexedDocument;
import com.ensa.srisearcher.utils.Converter;
import com.ensa.srisearcher.utils.serializables.SerializableHashMap;
import com.ensa.srisearcher.utils.serializables.SerializableHashSet;
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
        docId++;
        Converter.update(this);
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
}
