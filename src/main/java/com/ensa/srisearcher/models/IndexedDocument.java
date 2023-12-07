package com.ensa.srisearcher.models;

import java.util.List;
import java.util.Map;

public class IndexedDocument {
    private String url;
    private Map<String, List<String>> content;

    public IndexedDocument(String url, Map<String, List<String>> content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, List<String>> getContent() {
        return content;
    }

    public void setContent(Map<String, List<String>> content) {
        this.content = content;
    }
}
