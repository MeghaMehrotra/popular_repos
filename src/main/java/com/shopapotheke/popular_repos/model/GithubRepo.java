package com.shopapotheke.popular_repos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author mmehrotra
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepo {

    String name;
    String html_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
