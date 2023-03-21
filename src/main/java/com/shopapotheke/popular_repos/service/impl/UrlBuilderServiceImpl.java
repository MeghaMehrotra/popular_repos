package com.shopapotheke.popular_repos.service.impl;

import com.shopapotheke.popular_repos.service.UrlBuilderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author mmehrotra
 */

@Service
public class UrlBuilderServiceImpl implements UrlBuilderService {

    private final String githubApiBaseUrl = "https://api.github.com";

    @Override
    public String buildUrlFromParams(String language, LocalDate createdSince,String sort,String order,Integer perPage) {
        StringBuilder urlBuilder = new StringBuilder(githubApiBaseUrl);
        urlBuilder.append("/search/repositories?q=");
        urlBuilder.append("stars:>0");
        if (language != null) {
            urlBuilder.append("+language:");
            urlBuilder.append(language);
        }
        if (createdSince != null) {
            urlBuilder.append("+created:>");
            urlBuilder.append(createdSince.format(DateTimeFormatter.ISO_DATE));
        }
        urlBuilder.append("&sort=").append(sort);
        urlBuilder.append("&order=").append(order);
        urlBuilder.append("&per_page=").append(perPage);
        return urlBuilder.toString();
    }
}
