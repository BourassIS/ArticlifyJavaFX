package com.ensa.srisearcher.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {

    private Map<String, Set<Integer>> index;

    public InvertedIndex() {
        index = new HashMap<>();
    }

    public void addDocument(int documentId, List<String> words) {
        for (String word : words) {
            index.putIfAbsent(word, new HashSet<>());
            index.get(word).add(documentId);
        }
    }

    public Set<Integer> search(String query) {
        return index.getOrDefault(query, new HashSet<>());
    }

    public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();

        // Sample documents
        List<String> document1 = List.of("apple", "banana", "orange");
        List<String> document2 = List.of("apple", "pear", "grape");
        List<String> document3 = List.of("banana", "orange", "grape");

        // Add documents to the inverted index
        invertedIndex.addDocument(1, document1);
        invertedIndex.addDocument(2, document2);
        invertedIndex.addDocument(3, document3);

        // Search for terms in the inverted index
        System.out.println("Documents containing 'apple': " + invertedIndex.search("apple"));
        System.out.println("Documents containing 'orange': " + invertedIndex.search("orange"));
        System.out.println("Documents containing 'pear': " + invertedIndex.search("pear"));
    }
}

