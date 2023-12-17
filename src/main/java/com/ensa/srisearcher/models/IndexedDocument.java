package com.ensa.srisearcher.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class IndexedDocument implements Serializable {
    private String url;
    private Map<String, List<String>> content;

    public IndexedDocument(String url, Map<String, List<String>> content) {
        this.url = url;
        this.content = content;
    }
}
