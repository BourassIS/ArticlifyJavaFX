package com.ensa.srisearcher.models;

public class SearchResultItem {
    private String url;
    private String snippets;

    public SearchResultItem(String url, String snippets) {
        this.url = url;
        this.snippets = snippets;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSnippets() {
        return snippets;
    }

    public void setSnippets(String snippets) {
        this.snippets = snippets;
    }
}
