package com.ensa.srisearcher.algorithms;

import com.ensa.srisearcher.models.IndexedDocument;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {


    public void addDocument(int documentId, List<String> words) {
        for (String word : words) {
            DataStore.index.putIfAbsent(word.toLowerCase(), new HashSet<>());
            DataStore.index.get(word.toLowerCase()).add(documentId);
        }
    }

    public Set<Integer> search(String query) {
        return DataStore.index.getOrDefault(query, new HashSet<>());
    }


}

