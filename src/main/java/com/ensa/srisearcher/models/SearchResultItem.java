package com.ensa.srisearcher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class SearchResultItem implements Serializable {
    private String url;
    private String snippets;
}
