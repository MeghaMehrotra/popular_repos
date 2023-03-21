package com.shopapotheke.popular_repos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author mmehrotra
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubApiResponse {

    private List<GithubRepo> items;;


    public List<GithubRepo> getItems() {
        return items;
    }

    public void setItems(List<GithubRepo> items) {
        this.items = items;
    }
}
