package com.ensa.srisearcher.algorithms;

import com.ensa.srisearcher.models.IndexedDocument;
import com.ensa.srisearcher.utils.Converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvertedIndex implements Serializable {


    public void addDocument(int documentId, List<String> words) {
        DataStore dataStore= Converter.getDataStore();
        for (String word : words) {
            dataStore.index.putIfAbsent(word.toLowerCase(), new HashSet<>());
            dataStore.index.get(word.toLowerCase()).add(documentId);
        }
        Converter.update(dataStore);
    }

    public Set<Integer> search(String query) {
        DataStore dataStore= Converter.getDataStore();
        return dataStore.index.getOrDefault(query, new HashSet<>());
    }


}

