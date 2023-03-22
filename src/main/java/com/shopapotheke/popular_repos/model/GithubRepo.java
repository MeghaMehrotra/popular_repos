package com.shopapotheke.popular_repos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

/**
 * @author mmehrotra
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepo {

    String name;
    String html_url;

    public GithubRepo() {
    }

    public GithubRepo(String name, String html_url) {
        this.name = name;
        this.html_url = html_url;
    }

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
